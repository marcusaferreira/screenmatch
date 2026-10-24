package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.ConsultaChatGPT;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Serie {
    private String titulo;
    private Integer totalDeTemporadas;
    private String sinopse;
    private String anoDeLancamento;
    private String autor;
    private Double avaliacao;
    private List<Categoria> generos;
    private String atores;
    private String poster;

    public Serie() {
    }

    public Serie(String titulo, Integer totalDeTemporadas, String sinopse, String anoDeLancamento, String autor, Double avaliacao, List<Categoria> generos, String atores, String poster) {
        this.titulo = titulo;
        this.totalDeTemporadas = totalDeTemporadas;
        this.sinopse = sinopse;
        this.anoDeLancamento = anoDeLancamento;
        this.autor = autor;
        this.avaliacao = avaliacao;
        this.generos = generos;
        this.atores = atores;
        this.poster = poster;
    }

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.title();
        this.totalDeTemporadas = dadosSerie.totalSeasons();
        this.sinopse = ConsultaChatGPT.obterTraducao(dadosSerie.plot());
        this.anoDeLancamento = dadosSerie.year();
        this.autor = dadosSerie.writer();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(dadosSerie.imdbRating())).orElse(0.0);
        this.generos = Arrays.stream(dadosSerie.genero().split(",\\s*")).map(Categoria::fromString).toList();
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public String getAutor() {
        return autor;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public List<Categoria> getGeneros() {
        return generos;
    }

    public String getAtores() {
        return atores;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "titulo='" + getTitulo() + '\'' +
                ", totalDeTemporadas=" + getTotalDeTemporadas() +
                ", sinopse='" + getSinopse() + '\'' +
                ", anoDeLancamento='" + getAnoDeLancamento() + '\'' +
                ", autor='" + getAutor() + '\'' +
                ", avaliacao='" + getAvaliacao() + '\'' +
                ", generos=" + getGeneros() +
                ", atores='" + getAtores() + '\'' +
                ", poster='" + getPoster() + '\'' +
                '}';
    }
}
