package com.example.Libreria.Repository;

import com.example.Libreria.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryLibro extends JpaRepository<Libro,Long> {

    @Query("SELECT l FROM Libro l WHERE l.idiomas = :idioma")
    List<Libro> buscarLibroPorIdioma(String idioma);
}
