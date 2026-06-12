package br.com.screen.ScreenMatch.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private Integer totalTemp;

    private Double avaliacao;

    @Enumerated(EnumType.STRING)
    private Categoria genero;

    private String atores;

    private String poster;

    private String sinopse;

    @OneToMany(mappedBy = "serie")
    private List<Episodio> episodios = new ArrayList<>();

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemp() {
        return totalTemp;
    }

    public void setTotalTemp(Integer totalTemp) {
        this.totalTemp = totalTemp;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
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

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return
                "genero=" + genero +
                ", titulo='" + titulo + '\'' +
                ", totalTemp=" + totalTemp +
                ", avaliacao=" + avaliacao +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", sinopse='" + sinopse + '\'';
    }

    protected Serie() {
        //O Hibernate/JPA precisa de um construtor sem argumentos para criar objetos da entidade quando lê dados do banco.
    }

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.totalTemp = dadosSerie.totalTemp();
        this.avaliacao = Optional.ofNullable(dadosSerie.avaliacao())
                .filter(a -> !a.equals("N/A"))
                .map(Double::valueOf)
                .orElse(0.0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();
    }
}
