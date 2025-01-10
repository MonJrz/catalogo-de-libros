package com.catalogolibros.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonProperty("title") String titulo,
        @JsonProperty("authors") List<DatosAutor> autores,
        @JsonProperty("languages") List<String> idiomas
        )
        {

                public String getTitulo() {
                        return titulo;
                }


                public List<DatosAutor> getAutores() {
                        return autores;
                }


                public List<String> getIdiomas() {
                        return idiomas;
                }



        }
