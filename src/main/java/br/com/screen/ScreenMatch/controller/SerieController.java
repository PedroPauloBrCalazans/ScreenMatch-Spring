package br.com.screen.ScreenMatch.controller;

import br.com.screen.ScreenMatch.Service.SerieService;
import br.com.screen.ScreenMatch.dto.SerieDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping("/series")
    public List<SerieDTO> obterSeries() {
        return serieService.obterTodasSeries();
    }
}
