package com.catalogolibros.biblioteca.service;

import com.catalogolibros.biblioteca.model.Autor;
import com.catalogolibros.biblioteca.repository.AutorRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public List<Autor> listarAutores(){
        return  autorRepositorio.findAllConLibros();
    }

    public List<Autor> mostrarAutoresVivosEnDeterminadoAnio (int anio){
        return autorRepositorio.autoresVivosPorFecha(anio);
    }

    public Autor crearAutor(Autor autor){
        return  autorRepositorio.save(autor);
    }

    public Optional<Autor> obtenerAutorPorId(Long id){
        return autorRepositorio.findById(id);
    }

    public Optional<Autor> obtenerAutorPorNombre(String nombre)
    {
        return autorRepositorio.findByNombre(nombre);
    }

    public Autor actualizarAutor(Long id, Autor datosAutor ){
        Autor autor = autorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        autor.setNombre(datosAutor.getNombre());
        autor.setFechaNacimiento(datosAutor.getFechaNacimiento());
        autor.setFechaMuerte(datosAutor.getFechaMuerte());
        return autorRepositorio.save(autor);
    }

    public void eliminarAutor(Long id){
         autorRepositorio.deleteById(id);
    }
}
