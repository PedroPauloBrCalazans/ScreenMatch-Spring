package br.com.screen.ScreenMatch.repository;

import br.com.screen.ScreenMatch.model.Categoria;
import br.com.screen.ScreenMatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTempLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporada, Double avaliacao);
}

//No 1° parametro da JpaRepository passar a entidade que estou manipulando, 2° o ID