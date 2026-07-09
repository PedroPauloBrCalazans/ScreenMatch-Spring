package br.com.screen.ScreenMatch.dto;

import br.com.screen.ScreenMatch.model.Categoria;

public record SerieDTO(
         Long id,
         String titulo,
         Integer totalTemp,
         Double avaliacao,
         Categoria genero,
         String atores,
         String poster,
         String sinopse
) {
}
