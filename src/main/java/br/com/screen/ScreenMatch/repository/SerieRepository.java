package br.com.screen.ScreenMatch.repository;

import br.com.screen.ScreenMatch.model.Categoria;
import br.com.screen.ScreenMatch.model.Episodio;
import br.com.screen.ScreenMatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTempLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporada, Double avaliacao);

    //@Query(value = "select * from series WHERE series.total_temp <= 5 and series.avaliacao >= 7.5", nativeQuery = true) usando query nativas, se usa quando não troca de BD
    @Query("select s from Serie s WHERE s.totalTemp <= :totalTemporada and s.avaliacao >= :avaliacao") // funciona independente do BD
    List<Serie> seriesPorTemporadasEAvaliacao(int totalTemporadas, double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);
}

//No 1° parametro da JpaRepository passar a entidade que estou manipulando, 2° o ID

//Ilike = registros que contem alguma coisa, mesma coisa de chamar o ignoreCase