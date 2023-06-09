package com.example.appleitour;

public class ModelBook {

    private String name;
    private String author;
    private String editora;
    private String idioma;
    private String date;

    private final String nameStart = "Nome: ";
    private final String authorStart = "Autor: ";
    private final String editoraStart = "Editora: ";
    private final String idiomaStart = "Idioma: ";
    private final String dateStart = "Data: ";

    public ModelBook(String name, String author, String editora, String idioma, String date) {
        this.name = name;
        this.author = author;
        this.editora = editora;
        this.idioma = idioma;
        this.date = date;
    }

    public String getName() {
        return this.nameStart + name;
    }
    public String getAuthor() {
        return this.authorStart + author;
    }

    public String getEditora() {
        return this.editoraStart + editora;
    }

    public String getIdioma() {
        return this.idiomaStart + idioma;
    }

    public String getDate() {
        return this.dateStart + date;
    }
}
