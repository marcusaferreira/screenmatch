package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConverteDados conversor = new ConverteDados();

    private static final String OMDB_API_URL = "https://www.omdbapi.com/";
    private static final Logger logger = Logger.getLogger(Principal.class.getName());

    Serie serie = null;

    public void exibeMenu(String omdbapiKey) {
        IO.println("Bem-vindo ao ScreenMatch!");

        List<Serie> series = new ArrayList<>();
        List<DadosTemporada> temporadas = new ArrayList<>();
        List<Episodio> episodios = new ArrayList<>();

        var itemMenu = 0;
        do {
            IO.println("");
            IO.println("Menu:");
            IO.println("1 - Buscar Série");
            IO.println("2 - Listar Séries");
            IO.println("3 - Selecionar Série");
            IO.println("4 - Exibir episódios");
            IO.println("5 - Exibir episódios com melhor avaliação");
            IO.println("6 - Exibir episódios a partir de uma data");
            IO.println("7 - Exibir episódio por título");
            IO.println("8 - Media de avaliações por temporada");
            IO.println("9 - Exibir estatísticas da série");
            IO.println("0 - Sair");
            try {
                itemMenu = leitura.nextInt();
            } catch (InputMismatchException _) {
                logger.warning("Entrada inválida. Por favor, digite um número.");
                leitura.nextLine(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }
            leitura.nextLine(); // Limpa o buffer do scanner

            switch (itemMenu) {
                case 1 -> buscarDadosSeriesNaWeb(omdbapiKey, series);
                case 2 -> listarSeries(series);
                case 3 -> selecionarSerie(series, temporadas, episodios, omdbapiKey);
                case 4 -> exibirEpisodios(episodios);
                case 5 -> exibirTop5EpisodiosPorAvaliacao(episodios);
                case 6 -> exibirEpisodiosAPartirDaDataInformada(episodios);
                case 7 -> exibirEpisodioPorTitulo(episodios);
                case 8 -> calcularMediaDasAvaliacoesDeCadaTemporada(temporadas);
                case 9 -> exibirEstatisticas(episodios);
                case 0 -> IO.println("Saindo...");
                default -> logger.warning("Opção inválida. Tente novamente.");
            }
        } while (itemMenu != 0);

    }

    private void selecionarSerie(List<Serie> series, List<DadosTemporada> temporadas, List<Episodio> episodios, String omdbapiKey) {
        if (series.isEmpty()) {
            logger.warning("Nenhuma série cadastrada. Por favor, busque uma série primeiro.");
            return;
        }

        IO.println("Selecione a série desejada:");
        for (int i = 0; i < series.size(); i++) {
            IO.println((i + 1) + " - " + series.get(i));
        }

        int opcaoSerie = leitura.nextInt();
        leitura.nextLine(); // Limpa o buffer do scanner

        if (opcaoSerie < 1 || opcaoSerie > series.size()) {
            logger.warning("Opção inválida. Retornando ao menu principal.");
            return;
        }

        serie = series.get(opcaoSerie - 1);
        IO.println("Série selecionada: " + serie.getTitulo());

        String json;
        for (int i = 1; i <= serie.getTotalDeTemporadas(); i++) {
            json = consumoAPI.obterDados(OMDB_API_URL + "?t=" + serie.getTitulo().replace(" ", "+") + "&Season=" + i + "&apikey=" + omdbapiKey);
            var temporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(temporada);
        }

        episodios.clear();
        episodios.addAll(temporadas.stream()
                .map(dadosTemporada -> dadosTemporada.episodios().stream()
                        .map(dadosEpisodio -> new Episodio(dadosTemporada.numero(), dadosEpisodio)).toList())
                .flatMap(List::stream).toList());
    }

    private static void listarSeries(List<Serie> series) {
        IO.println("");
        IO.println("Séries buscadas:");
        series.forEach(serie -> IO.println(serie.getTitulo() + " (" + serie.getAnoDeLancamento() + ")"));
    }

    private void buscarDadosSeriesNaWeb(String omdbapiKey, List<Serie> series) {
        IO.println("Digite o nome da série para buscar informações sobre ela:");
        String nomeSerie = leitura.nextLine();

        var json = consumoAPI.obterDados(OMDB_API_URL + "?t=" + nomeSerie.replace(" ", "+") + "&apikey=" + omdbapiKey);
        IO.println(json);

        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        Serie serieEncontrada = new Serie(dadosSerie);

        IO.println(" Série encontrada: ".concat(serieEncontrada.toString()));

        series.add(serieEncontrada);
    }

    private static void exibirEstatisticas(List<Episodio> episodios) {
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        IO.println("");
        IO.println("Estatísticas da série:");
        IO.println("Média de avaliação: " + est.getAverage());
        IO.println("Maior avaliação: " + est.getMax());
        IO.println("Menor avaliação: " + est.getMin());
        IO.println("Total de episódios: " + est.getCount());
    }

    private static void calcularMediaDasAvaliacoesDeCadaTemporada(List<DadosTemporada> temporadas) {
        IO.println("");
        IO.println("Média de avaliações por temporada:");
        Map<Integer, Double> mediaAvaliacoesPorTemporada = new HashMap<>();
        for (DadosTemporada temporada : temporadas) {
            double media = temporada.episodios().stream()
                    .mapToDouble(dadosEpisodio -> {
                        try {
                            return Double.parseDouble(dadosEpisodio.avaliacao());
                        } catch (NumberFormatException _) {
                            return 0.0; // Caso a avaliação não seja um número válido
                        }
                    })
                    .average()
                    .orElse(0.0);
            mediaAvaliacoesPorTemporada.put(temporada.numero(), media);
        }
        mediaAvaliacoesPorTemporada.forEach((numero, media) ->
                IO.println("Temporada " + numero + ": " + String.format("%.2f", media)));
    }

    private void exibirEpisodioPorTitulo(List<Episodio> episodios) {
        IO.println("");
        IO.println("Digite o título do episódio que deseja buscar:");
        String titulo = leitura.nextLine();
        var resultadoBusca = episodios.stream()
                .filter(episodio -> episodio.getTitulo() != null && episodio.getTitulo().toUpperCase().contains(titulo.toUpperCase()))
                .findFirst();

        if (resultadoBusca.isPresent()) {
            IO.println(resultadoBusca.get().toString());
        } else {
            logger.warning("Episódio não encontrado.");
        }
    }

    private void exibirEpisodiosAPartirDaDataInformada(List<Episodio> episodios) {
        IO.println("");
        IO.println("Digite uma data (no formato YYYY-MM-DD) para exibir os episódios lançados a partir dela:");
        String dataInput = leitura.nextLine();
        episodios.stream()
                .filter(episodio -> episodio.getDataLancamento() != null && episodio.getDataLancamento().isAfter(java.time.LocalDate.parse(dataInput)))
                .sorted(Comparator.comparing(Episodio::getDataLancamento))
                .forEach(IO::println);
    }

    private static void exibirTop5EpisodiosPorAvaliacao(List<Episodio> episodios) {
        IO.println("");
        IO.println("Top 5 episódios com melhor avaliação:");
        episodios.stream()
                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
                .limit(5).forEach(episodio -> IO.println(episodio.toString()));
    }

    private static void exibirEpisodios(List<Episodio> episodios) {
        IO.println("");
        IO.println("Episódios da série:");
        episodios.forEach(episodio -> IO.println(episodio.toString()));
    }
}
