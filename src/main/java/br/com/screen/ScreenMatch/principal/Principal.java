package br.com.screen.ScreenMatch.principal;

import br.com.screen.ScreenMatch.Service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=3e6a71fe";


    public void exibeMenu() {
        System.out.println("Digite o nome da Série para busca!");
        var nomeSerie = leitura.nextLine();
        var json = consumoAPI.obterDados( ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);



    }
}
