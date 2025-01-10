# Literalura

_Literalura_ es una aplicación que utiliza la API _Gutendex_ para facilitar la consulta de libros y autores. Esta herramienta permite buscar información detallada sobre libros y autores en la base de datos de _Gutendex_.
Funcionalidades

Literalura ofrece las siguientes opciones:
    
- Buscar libro por título: Permite encontrar libros en la base de datos por su título.

- Mostrar libros registrados: Muestra una lista de los libros buscados.

- Mostrar autores registrados: Presenta una lista de autores que coinciden con los libros buscados.

- Mostrar autores vivos en determinado año: Filtra a los autores buscados que estaban vivos en un año específico.

- Mostrar libros por idiomas: Filtra libros registrados en un idioma específico.

- Salir: Finaliza la ejecución de la aplicación.

## Requisitos

Para ejecutar Literalura, necesitas:\
    - Java JDK 8 o superior\
    - Conexión a internet (para acceder a la API Gutendex)

## Instalación

   Descagra el repositorio en tu dispositivo.

## Ejecuta la aplicación:

   Abre la aplicaión en tu entorno de desarrollo.
   Ve a application.properties y conecta la aplicación a la base de datos colocando tu usuario y contraseña de PostgreSQL.

## Uso

Al iniciar la aplicación, se te presentará un menú con las opciones disponibles. Simplemente selecciona la opción que deseas utilizar ingresando el número correspondiente.
Ejemplo de Interacción:

Bienvenido a Literalura  
Seleccione una opción:  
1. Buscar libro por título  
2. Mostrar libros registrados  
3. Mostrar autores registrados  
4. Mostrar autores vivos en determinado año  
5. Mostrar libros por idiomas  
0. Salir  

Ejemplo:

Opción: 1  
Ingrese el título del libro: _Alice in Wonderland_  
Resultado:  
- Título: _Alice's Adventures in Wonderland_  
- Autor: Lewis Carroll  
- Idioma: Inglés  
