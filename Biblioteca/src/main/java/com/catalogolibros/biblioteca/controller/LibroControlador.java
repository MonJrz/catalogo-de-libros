
package com.catalogolibros.biblioteca.controller;


import com.catalogolibros.biblioteca.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibroControlador {
    @Autowired
    private LibroRepositorio libroRepositorio;

    @GetMapping("/hola")
    public String mostrarMensaje() {
        return "Holi";
    }
}

/*
    @GetMapping("/libros")
    public List<Libro> listarLibros() {
        return libroRepositorio.findAll();
    }
}

@RequestMapping("/catalogo")
public class LibroControlador {
    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private ConsumoAPI consumoAPI;


        @GetMapping("/buscar")
        public ResponseEntity<?> buscarLibroPorTitulo(@RequestParam String titulo) {
            List<Libro> libros = libroRepositorio.findByTituloContaining(titulo);

            if (libros.isEmpty()) {
                Libro libroAPI = consumoAPI.buscarLibro(titulo);
                if (libroAPI == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
                }
                libroRepositorio.save(libroAPI);
                return ResponseEntity.ok(libroAPI);
            }
            return ResponseEntity.ok(libros);
        }

    @GetMapping("/libros")
    public List<Libro> listarLibros() {
        return libroRepositorio.findAll();
    }

    @GetMapping("/autores")
    public List<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    @GetMapping("/autores/vivos")
    public List<Autor> listarAutoresVivos(@RequestParam int anio) {
        return autorRepositorio.findByVivoAndFechaNacimientoBefore(true, anio);
    }

    @GetMapping("/libros/idioma")
    public List<Libro> listarLibrosPorIdioma(@RequestParam String idioma) {
        return libroRepositorio.findByIdioma(idioma);
    }

    */


