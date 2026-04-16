package br.com.screen.ScreenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemp,
                         @JsonAlias("imdbRating") String avaliacao) {
}
