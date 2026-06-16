package br.com.screen.ScreenMatch.repository;

import br.com.screen.ScreenMatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);
}



//No 1° parametro da JpaRepository passar a entidade que estou manipulando, 2° o ID