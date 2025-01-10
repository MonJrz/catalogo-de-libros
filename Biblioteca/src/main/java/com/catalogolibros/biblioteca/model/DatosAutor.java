package com.catalogolibros.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonProperty("birth_year") int fechaNacimiento,
        @JsonProperty("death_year") int fechaMuerte,
        @JsonProperty("name") String nombre
        ) {
        public String getNombre(){
                return nombre;
        }

        public int getfechaNacimiento(){
                return fechaNacimiento;
        }

        public int getfechaMuerte(){
                return fechaMuerte;
        }

}
