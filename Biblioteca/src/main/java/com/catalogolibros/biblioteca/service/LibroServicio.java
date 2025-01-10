package com.catalogolibros.biblioteca.service;

import com.catalogolibros.biblioteca.model.Libro;
import com.catalogolibros.biblioteca.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    public List<Libro> listarLibros(){
        return libroRepositorio.findAll();
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepositorio.findByIdioma(idioma);
    }

    public Libro crearLibro(Libro libro){
        return  libroRepositorio.save(libro);
    }

    public Optional<Libro> obtenerLibroPorID(Long id){
        return libroRepositorio.findById(id);
    }

    public  Optional<Libro> obtenerLibroPorTitulo(String titulo){
        return  libroRepositorio.findByTituloContainsIgnoreCase(titulo);
    }

    public  Libro actualizarLibro(Long id, Libro datosLibro){
        Libro libro = libroRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libro.setTitulo(datosLibro.getTitulo());
        libro.setIdioma(datosLibro.getIdioma());
        libro.setAutor(datosLibro.getAutor());
        return libroRepositorio.save(libro);
    }

    public void eliminarLibro(Long id){
        libroRepositorio.deleteById(id);
    }
}
