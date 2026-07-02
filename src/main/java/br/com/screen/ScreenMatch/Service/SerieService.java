package br.com.screen.ScreenMatch.Service;

import br.com.screen.ScreenMatch.dto.SerieDTO;
import br.com.screen.ScreenMatch.model.Serie;
import br.com.screen.ScreenMatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obterTodasSeries() {
        return converteDados(repository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());

    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(repository.findTop5ByOrderByEpisodiosDataLancamentoDesc());
    }

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(),
                        s.getTotalTemp(), s.getAvaliacao(), s.getGenero(),
                        s.getAtores(), s.getPoster(), s.getSinopse())).toList();
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
           return new SerieDTO(s.getId(), s.getTitulo(),
                   s.getTotalTemp(), s.getAvaliacao(), s.getGenero(),
                   s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;
    }
}
