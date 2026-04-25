package br.com.screen.ScreenMatch.principal;

import br.com.screen.ScreenMatch.Service.ConsumoAPI;
import br.com.screen.ScreenMatch.Service.ConverteDados;
import br.com.screen.ScreenMatch.model.DadosEpisodio;
import br.com.screen.ScreenMatch.model.DadosSerie;
import br.com.screen.ScreenMatch.model.DadosTemporada;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String TEMPORADA = "&season=";
    private final String API_KEY = "&apikey=3e6a71fe";


    public void exibeMenu() {
        System.out.println("Digite o nome da Série para busca!");
        var nomeSerie = leitura.nextLine();
        var json = consumoAPI.obterDados( ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i<=dadosSerie.totalTemp(); i++) {
            json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + TEMPORADA + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

//        for(int i = 0; i < dadosSerie.totalTemp(); i++) {
//            List<DadosEpisodio> episodiosPorTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j < episodiosPorTemporada.size(); j++) {
//                System.out.println(episodiosPorTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo()))); // somente interação

        List<DadosEpisodio> dadosEpisodioList = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList()); // tenho todos os episodios de todas as temporadas.

        System.out.println("\nTop 5 episódios");
        dadosEpisodioList.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A")) //tirando todos que for N/A
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()) //ordem decresente
                .limit(5)
                .forEach(System.out::println);



        //.flatMap = dentro de uma lista vou ter outra lista, e vou puxar todas essas listas juntas..
        //.toList() vai dar uma lista imutavel
        //.collect(Collectors.toList()) consigo acresentar coisas novas na lista
    }
}
