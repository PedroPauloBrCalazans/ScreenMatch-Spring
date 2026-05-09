package br.com.screen.ScreenMatch.principal;

import br.com.screen.ScreenMatch.Service.ConsumoAPI;
import br.com.screen.ScreenMatch.Service.ConverteDados;
import br.com.screen.ScreenMatch.model.DadosEpisodio;
import br.com.screen.ScreenMatch.model.DadosSerie;
import br.com.screen.ScreenMatch.model.DadosTemporada;
import br.com.screen.ScreenMatch.model.Episodio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

        System.out.println("\nTop 10 episódios");
        dadosEpisodioList.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A")) //tirando todos que for N/A
                .peek(e -> System.out.println("Primeiro filtro(N/A) " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()) //ordem decresente
                .peek(e -> System.out.println("Ordenação " + e))
                .limit(10)
                .peek(e -> System.out.println("Limite " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Mapeamento " + e))
                .forEach(System.out::println);

        System.out.println("\nEpisódios por temporadas");
        List<Episodio> episodiosList = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numeroTemporada(), d))
                ).collect(Collectors.toList());

        episodiosList.forEach(System.out::println);

        System.out.println("\nDigite um trecho do título do episódio");
        var trechoTitulo = leitura.nextLine();
        Optional<Episodio> episodioBusca = episodiosList.stream()
                .filter(t -> t.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst(); //pega a 1° referencia encontrada.

        if (episodioBusca.isPresent()) {
            System.out.println("Episódio encontrado");
            System.out.println("Temporada: " + episodioBusca.get().getTemporada());
        } else {
            System.out.println("Episódio não encontrado!");
        }

//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//        var ano = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodiosList.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episódio: " + e.getTitulo() +
//                                " Data lançamento: " + e.getDataLancamento().format(formatador)
//                ));

        Map<Integer, Double> avaliacoesPorTemporada = episodiosList.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacoesPorTemporada);
    }
}

//filtro para retorna temporada
//avaliações por temporadas

//.flatMap = dentro de uma lista vou ter outra lista, e vou puxar todas essas listas juntas..
//.toList() vai dar uma lista imutavel
//.collect(Collectors.toList()) consigo acresentar coisas novas na lista
//findFirst() mais precisão na busca, ele tem um retorno
//optional é um objeto contêiner que pode ou não conter um valor não nulo, caso na busca não encontrar nada, a gente teria uma referencia nula e pra não ter uma referencia nula usa o optinal.