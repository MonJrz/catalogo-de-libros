package com.catalogolibros.biblioteca.principal;

import com.catalogolibros.biblioteca.model.*;
import com.catalogolibros.biblioteca.repository.LibroRepositorio;
import com.catalogolibros.biblioteca.service.AutorServicio;
import com.catalogolibros.biblioteca.service.ConsumoAPI;
import com.catalogolibros.biblioteca.service.ConvierteDatos;
import com.catalogolibros.biblioteca.service.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {
    private Scanner input = new Scanner(System.in);
    @Autowired
    private ConsumoAPI consumoAPI;
    @Autowired
    private ConvierteDatos conversor;
    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;

    private final String URL_BASE = "https://gutendex.com/books/";
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private List<Libro> libros;
    private Optional<Libro> libroBuscado;
    private LibroRepositorio repositorio;




    public Principal(LibroRepositorio repository) {
        this.repositorio = repository;
    }


    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ----------------------------------------
                    1. Buscar libro por título
                    2. Mostrar libros registrados
                    3. Mostrar autores registrados
                    4. Mostrar autores vivos en determinado año
                    5. Mostrar libros por idiomas
                    0. Salir
                    ----------------------------------------
                    """;

            System.out.println(menu);
            // Validar que la entrada es un número entero
            if (input.hasNextInt()) {
                opcion = input.nextInt();
                input.nextLine(); // Consumir el salto de línea después del número
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                input.nextLine(); // Consumir la entrada inválida
                continue; // Reinicia el bucle
            }

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                   listarAutores();
                    break;
                case 4:
                    mostrarAutoresVivosEnDeterminadoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo de la Biblioteca Virtual");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Selecciona algún número mostrado en el menú");
            }

        }

    }
    private void buscarLibroPorTitulo() {
     System.out.println("Escribe el título del libro que quieres buscar: ");
    String busqueda = input.nextLine();
    try{
        String encodedTitulo = URLEncoder.encode(busqueda, StandardCharsets.UTF_8);
        String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + encodedTitulo);
        RespuestaDatosLibro respuestaDatosLibro = conversor.obtenerDatos(json, RespuestaDatosLibro.class);
        List<DatosLibro> datosLibroList = respuestaDatosLibro.getLibros();
        if (datosLibroList.isEmpty()){
            System.out.println("No se encontró ningún libro con ese nombre.");
        } else {
            boolean libroRegistrado = false;
            for (DatosLibro datosLibro : datosLibroList){
                if (datosLibro.getTitulo().equalsIgnoreCase(busqueda)){
                    Optional<Libro> libroExistente = libroServicio.obtenerLibroPorTitulo(busqueda);
                    if (libroExistente.isPresent()){
                        System.out.println("El libro " + busqueda + " ya está registrado");
                        libroRegistrado=true;
                        break;
                    } else {
                        Libro libro=new Libro();
                        libro.setTitulo(datosLibro.getTitulo());
                        libro.setIdioma(datosLibro.getIdiomas().get(0));

                        //Buscar o crear autor
                        DatosAutor datosPrimerAutor = datosLibro.autores().get(0);
                        Autor autor = autorServicio.obtenerAutorPorNombre(datosPrimerAutor.getNombre())
                                .orElseGet(() -> {
                                    Autor nuevoAutor = new Autor();
                                    nuevoAutor.setNombre(datosPrimerAutor.getNombre());
                                    nuevoAutor.setFechaNacimiento(datosPrimerAutor.getfechaNacimiento());
                                    nuevoAutor.setFechaMuerte(datosPrimerAutor.getfechaMuerte());
                                    return autorServicio.crearAutor(nuevoAutor);
                                });

                        //Asociar Autor a Libro
                        libro.setAutor(autor);

                        //Guardar libro en DB
                        libroServicio.crearLibro(libro);
                        System.out.println("Libro registrado: " + libro.getTitulo());
                        mostrarDetallesLibro(datosLibro);
                        libroRegistrado=true;
                        break;
                    }
                }
            }
            if (!libroRegistrado){
                System.out.println("No se encontró un libro con ese título");
            }
        }

    }
    catch (Exception e) {
        System.out.println("Error al obtener datos: " + e.getMessage());
    }


}

    public void listarLibros(){
        libroServicio.listarLibros().forEach(libro -> {
            System.out.println("***************************");
            System.out.println("Detalles del libro");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + (libro.getAutor() != null ? libro.getAutor() : "Autor desconocido"));
            System.out.println("Idioma: " + libro.getIdioma())
            ;;
        });

    }

    public void listarAutores(){
    autorServicio.listarAutores().forEach(autor -> {
        System.out.println("***************************");
        System.out.println("Detalles de autor");
        System.out.println("Nombre: " + autor.getNombre());
        System.out.println("Fecha de nacimiento: " + autor.getFechaNacimiento());
        System.out.println("Fecha de muerte: " + autor.getFechaMuerte());
        String libros = autor.getLibros().stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));
        System.out.println(("Libros escritos: " + libros));
    });


    }

    public void mostrarAutoresVivosEnDeterminadoAnio(){
        System.out.println("Ingresa el año en el que deseas buscar: ");
        int anio = input.nextInt();
        input.nextLine();
        List<Autor> autoresVivos = autorServicio.mostrarAutoresVivosEnDeterminadoAnio(anio);
        if (autoresVivos.isEmpty()){
            System.out.println("No se encontraron autores vivos en el año seleccionado");
        } else {
            autoresVivos.forEach(autor -> {
                System.out.println("***************************");
                System.out.println("Detalles de autor");
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println("Fecha de muerte: " + autor.getFechaMuerte());
                System.out.println(("Libros escritos: " + libros));
            });


        }

    }

    public void listarLibrosPorIdioma(){
        System.out.println("Escribe el idioma a buscar: ");
        System.out.println("es");
        System.out.println("en");
        System.out.println("fr");
        System.out.println("pt");
        System.out.println("zh");
        System.out.println("hn");
        String idioma = input.nextLine();
        if ("es".equalsIgnoreCase(idioma)
            || "en".equalsIgnoreCase(idioma)
            || "fr".equalsIgnoreCase(idioma)
            || "pt".equalsIgnoreCase(idioma)
            || "zh".equalsIgnoreCase(idioma)
            || "hn".equalsIgnoreCase(idioma)){
            libroServicio.listarLibrosPorIdioma(idioma).forEach(libro -> {
                System.out.println("***************************");
                System.out.println("Detalles del libro");
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Idioma: " + libro.getIdioma());
            });
            if(libroServicio.listarLibrosPorIdioma(idioma).isEmpty()){
                System.out.println("No se encontró un libro en el idioma seleccionado");
            }
        } else {
            System.out.println("Idioma no válido. Por favor, selecciona alguno de los idiomas disponibles.");
        }

    }

    public void mostrarDetallesLibro(DatosLibro datosLibro){
        System.out.println("***************************");
        System.out.println("Detalles del libro");
        System.out.println("Titulo: " + datosLibro.getTitulo());
        System.out.println("Autor: " + (datosLibro.getAutores().isEmpty() ? "Desconocido" : datosLibro.getAutores()));
        System.out.println("Idioma: " + datosLibro.getIdiomas().get(0));
    }
}