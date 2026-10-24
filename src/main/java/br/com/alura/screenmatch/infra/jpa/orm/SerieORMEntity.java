package br.com.alura.screenmatch.infra.jpa.orm;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.CategoriaEnumConverter;
import br.com.alura.screenmatch.model.Serie;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "series")
public class SerieORMEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "titulo", unique = true, nullable = false)
    private String titulo;
    private Integer totalDeTemporadas;
    private String sinopse;
    private String anoDeLancamento;
    private String autor;
    private Double avaliacao;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "serie_generos", joinColumns = @JoinColumn(name = "serie_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"serie_id", "genero"}))
    @Column(name = "genero")
    @Convert(converter = CategoriaEnumConverter.class)
    private List<Categoria> generos;
    private String atores;
    private String poster;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EpisodioORMEntity> episodios;

    public SerieORMEntity(String titulo, Integer totalDeTemporadas, String sinopse, String anoDeLancamento, String autor, Double avaliacao, List<Categoria> generos, String atores, String poster) {
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

    public SerieORMEntity(Serie serie) {
        this.titulo = serie.getTitulo();
        this.totalDeTemporadas = serie.getTotalDeTemporadas();
        this.sinopse = serie.getSinopse();
        this.anoDeLancamento = serie.getAnoDeLancamento();
        this.autor = serie.getAutor();
        this.avaliacao = serie.getAvaliacao();
        this.generos = serie.getGeneros();
        this.atores = serie.getAtores();
        this.poster = serie.getPoster();
    }

    public SerieORMEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(String anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Categoria> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Categoria> generos) {
        this.generos = generos;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<EpisodioORMEntity> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<EpisodioORMEntity> episodios) {
        this.episodios = episodios;
    }

    public Serie toSerie() {
        return new Serie(this.titulo, this.totalDeTemporadas, this.sinopse, this.anoDeLancamento, this.autor, this.avaliacao, this.generos, this.atores, this.poster);
    }
}
