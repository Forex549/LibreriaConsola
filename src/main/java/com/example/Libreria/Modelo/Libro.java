package com.example.Libreria.Modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Libro")
public class Libro {

    @Id
    private Long id;

    private String titulo;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Persona autor;

    @Column(columnDefinition = "TEXT")
    private String resumen;


    @ElementCollection(fetch = FetchType.EAGER) //ocurre cuando se hace relacion con un tipo de dato "simple", basicamente creamos tablas auxiliares
    @CollectionTable(name = "Temas", joinColumns = @JoinColumn(name = "libro_id")) //indicamos que columna de la tabla auxiliar tendra la referencia a libro
    @Column(name = "tema") //indicamos el nombre que tendra la columna que almacenara cada elemento de la list
    private List<String> temas;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Generos", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "genero")
    private List<String> generos;

    private String idiomas;

    private Integer cantidadDescargas;

    public Libro(Long id, String titulo, Persona autor, String resumen, List<String> temas, List<String> generos, String idiomas, int cantidadDescargas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.resumen = resumen;
        this.temas = temas;
        this.generos = generos;
        this.idiomas = idiomas;
        this.cantidadDescargas = cantidadDescargas;
    }

    public Libro(){

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Persona getAutor() {
        return autor;
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public List<String> getTemas() {
        return temas;
    }

    public String getResumen() {
        return resumen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutores(List<Persona> autores) {
        this.autor = autor;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public void setCantidadDescargas(int cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- LIBRO -----\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autor: ").append(autor).append("\n");
        sb.append("Idioma: ").append(idiomas).append("\n");
        sb.append("Número de descargas: ").append(cantidadDescargas).append("\n");

        // Campos opcionales (solo si tienen valor)
        if (resumen != null && !resumen.isEmpty()) {
            sb.append("Resumen: ").append(resumen).append("\n");
        }
        if (temas != null && !temas.isEmpty()) {
            sb.append("Temas: ").append(temas).append("\n");
        }
        if (generos != null && !generos.isEmpty()) {
            sb.append("Géneros: ").append(generos).append("\n");
        }

        sb.append("-----------------");
        return sb.toString();
    }
}
