package com.catalogolibros.biblioteca.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
