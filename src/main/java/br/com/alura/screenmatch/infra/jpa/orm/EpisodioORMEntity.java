package br.com.alura.screenmatch.infra.jpa.orm;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "episodios")
public class EpisodioORMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    private SerieORMEntity serie;
    private Integer temporada;
    private String titulo;
    private Integer numero;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public EpisodioORMEntity() {
    }

    public EpisodioORMEntity(SerieORMEntity serie, Integer temporada, String titulo, Integer numero, Double avaliacao, LocalDate dataLancamento) {
        this.serie = serie;
        this.temporada = temporada;
        this.titulo = titulo;
        this.numero = numero;
        this.avaliacao = avaliacao;
        this.dataLancamento = dataLancamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SerieORMEntity getSerie() {
        return serie;
    }

    public void setSerie(SerieORMEntity serie) {
        this.serie = serie;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
