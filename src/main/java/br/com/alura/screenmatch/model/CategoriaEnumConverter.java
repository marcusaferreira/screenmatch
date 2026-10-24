package br.com.alura.screenmatch.model;

import jakarta.persistence.AttributeConverter;

public class CategoriaEnumConverter implements AttributeConverter<Categoria, String> {


    @Override
    public String convertToDatabaseColumn(Categoria attribute) {
        return attribute.categoriaOmdb;
    }

    @Override
    public Categoria convertToEntityAttribute(String dbData) {
        return Categoria.fromString(dbData);
    }
}
