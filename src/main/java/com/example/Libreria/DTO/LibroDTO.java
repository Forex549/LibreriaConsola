package com.example.Libreria.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(

        @JsonAlias("id")
        Long id,

        @JsonAlias("title")
        String titulo,

        @JsonAlias("authors")
        List<PersonaDTO> autores,

        @JsonAlias("summaries")
        List<String> resumenes,


        @JsonAlias("subjects")
        List<String> temas,

        @JsonAlias("bookshelves")
        List<String> generos,

        @JsonAlias("languages")
        List<String> idiomas,


        @JsonAlias("download_count")
        Integer cantidadDescargas

) {
}
