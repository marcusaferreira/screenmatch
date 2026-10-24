package br.com.alura.screenmatch.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum Categoria {

    COMEDIA("Comedy"),
    DRAMA("Drama"),
    AVENTURA("Adventure"),
    FANTASIA("Fantasy"),
    TERROR("Horror"),
    ACAO("Action"),
    ROMANCE("Romance"),
    SUSPENSE("Thriller"),
    ANIMACAO("Animation"),
    DOCUMENTARIO("Documentary"),
    MUSICAL("Musical"),
    FICCAO_CIENTIFICA("Science Fiction"),
    GUERRA("War"),
    BIOGRAFIA("Biography"),
    MISTERIO("Mystery"),
    CRIME("Crime"),
    HISTORICO("History"),
    WESTERN("Western"),
    FAMILIA("Family"),
    MUSICAL_DANCA("Musical & Dance"),
    FILME_NOIR("Film-Noir"),
    ESPIONAGEM("Spy"),
    ESPORTE("Sport"),
    COMEDIA_ROMANTICA("Romantic Comedy"),
    COMEDIA_DRAMATICA("Dramedy"),
    COMEDIA_DE_ACAO("Action Comedy"),
    COMEDIA_DE_AVENTURA("Adventure Comedy"),
    COMEDIA_DE_FICCAO_CIENTIFICA("Science Fiction Comedy"),
    COMEDIA_DE_TERROR("Horror Comedy"),
    COMEDIA_DE_MISTERIO("Mystery Comedy"),
    COMEDIA_DE_GUERRA("War Comedy"),
    COMEDIA_DE_MUSICAL("Musical Comedy"),
    COMEDIA_DE_ANIMACAO("Animation Comedy");

    final String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}


