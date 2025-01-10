package com.catalogolibros.biblioteca.repository;

import com.catalogolibros.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllConLibros();


    Optional<Autor> findByNombre(String nombre);

    @Query(value = "SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND fechaMuerte > :anio")
            List<Autor> autoresVivosPorFecha(int anio);
}
