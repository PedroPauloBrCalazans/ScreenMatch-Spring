package br.com.screen.ScreenMatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime");

    private String categoriaOmdb;

    private String categoriasPortugues;

    Categoria(String categoriaOmdb, String categoriasPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriasPortugues = categoriasPortugues;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    } //se o texto que está vindo e igual a categoria do omdb, atribui a nossa categoria

    public static Categoria fromStringPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriasPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}

//O enum tem um construtor que permite atribuir um valor a este atributo.