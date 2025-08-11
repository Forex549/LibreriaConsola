package com.example.Libreria.Repository;

import com.example.Libreria.Modelo.Libro;
import com.example.Libreria.Modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryAutor extends JpaRepository<Persona,Long> {

    @Query("SELECT p FROM Persona p WHERE p.nombre = :nombre AND p.anioNacimiento = :anioNacimiento AND p.anioMuerte = :anioMuerte")
    Optional<Persona> buscarAutorPorTodosDatos(String nombre, Integer anioNacimiento, Integer anioMuerte);

    @Query("SELECT p FROM Persona p")
    List<Persona> listarTodosAutores();

    @Query("SELECT p FROM Persona p WHERE :anio >= p.anioNacimiento AND :anio < p.anioMuerte")
    Optional<List<Persona>> buscarAutorVivosPorAnio(Integer anio);


}
