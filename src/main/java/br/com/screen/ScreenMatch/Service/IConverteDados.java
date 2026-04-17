package br.com.screen.ScreenMatch.Service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}


//Generics <T> T => devolve alguma coisa que não sabemos