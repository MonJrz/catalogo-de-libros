package com.catalogolibros.biblioteca.model;

import jakarta.persistence.*;

@Entity(name = "Libro")
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String titulo;
    @Column(name = "idioma")
    private String idioma;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;


    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }



    @Override
    public String toString() {
        return
                "titulo= " + titulo + '\'' +
                "idioma= " + idioma + '\'' +
                "autor= " + autor;
    }

}


