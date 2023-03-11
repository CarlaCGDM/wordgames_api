package com.example.wordgames_api.service;

import com.example.wordgames_api.modelo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PalabraService  {

    // El fichero con la lista de palabras
    final static File palabrasFile = new File("src/main/resources/palabras/0_palabras_todas.txt");

    // Cargamos la lista de palabras
    public List<Palabra> cargarPalabras () throws FileNotFoundException, IOException {

        List<Palabra> diccionario = new ArrayList<>();
        Scanner myReader = new Scanner(palabrasFile);
        int sum = 0;

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            Palabra palabra = new Palabra(line.trim());
            diccionario.add(palabra);
        }

        myReader.close();
        return diccionario;
    }

    public List<Palabra> filtrarPalabras (Long cantidad,
                                          Long longitud,
                                          String empiezaPorCadena,
                                          String terminaPorCadena,
                                          String contieneCadena ) throws IOException, FileNotFoundException{

        // Palabras que devolveremos al final (comenzamos con todas las palabras):

        List<Palabra> resultado = cargarPalabras();

        // Uno por uno, vamos viendo si existen los filtros y los aplicamos

        // ¿Se ha especificado una longitud de la palabra?

        if (longitud != null) {
            resultado = filtroLongitud(resultado,longitud);
        }

        // ¿Se ha pedido que la palabra empiece por una cadena concreta?

        if (empiezaPorCadena != null) {
            resultado = filtroEmpiezaPorCadena(resultado,empiezaPorCadena);
        }

        // ¿Se ha pedido que la palabra termine por una cadena concreta?

        if (terminaPorCadena != null) {
            resultado = filtroTerminaPorCadena(resultado,terminaPorCadena);
        }

        // ¿Se ha pedido que la palabra contenga una cadena concreta?

        if (contieneCadena != null) {
            resultado = filtroContieneCadena(resultado, contieneCadena);
        }

        // ¿Se ha pedido una cantidad determinada de palabras?
        // Si no se ha pedido un número concreto, asumimos que lo que se está pidiendo es una sola palabra.

        Long cantidadPalabras = 1L;
        if (cantidad != null) {
            cantidadPalabras = cantidad;
        }

        // Si se han pedido más palabras de las que tenemos disponibles, no devolvemos nada
        // Con esto nos aseguramos de que el juego no piense que tiene lo que ha pedido y luego le falten palabras

        if(cantidadPalabras.intValue() > resultado.size()) {
            return Collections.<Palabra>emptyList();
        }

        // Mezclamos para no devolver siempre las mismas

        Collections.shuffle(resultado);

        // Cogemos el numero que se ha pedido
        resultado = resultado.subList(0,  cantidadPalabras.intValue());

        // Devolvemos la lista
        return resultado;
    }

    public List<Palabra> filtroLongitud (List<Palabra> palabras, Long longitud) throws IOException, FileNotFoundException{

        List<Palabra> resultado = new ArrayList<>();

        for (Palabra palabra : palabras) {

            if (palabra.getPalabra().length() == longitud) {
                resultado.add(palabra);
            }
        }

        return resultado;
    }
    public List<Palabra> filtroEmpiezaPorCadena (List<Palabra> palabras, String cadena) throws IOException, FileNotFoundException{

        List<Palabra> resultado = new ArrayList<>();

        for (Palabra palabra : palabras) {

            if (palabra.getPalabra().startsWith(cadena)) {
                resultado.add(palabra);
            }
        }
        return resultado;
    }
    public List<Palabra> filtroTerminaPorCadena(List<Palabra> palabras, String cadena) throws IOException, FileNotFoundException{


        List<Palabra> resultado = new ArrayList<>();

        for (Palabra palabra : palabras) {

            if (palabra.getPalabra().endsWith(cadena)) {
                resultado.add(palabra);
            }
        }
        return resultado;
    }
    public List<Palabra> filtroContieneCadena(List<Palabra> palabras, String cadena) throws IOException, FileNotFoundException{

        List<Palabra> resultado = new ArrayList<>();

        for (Palabra palabra : palabras) {
            // Comprueba si la palabra empieza por la cadena especificada
            if (palabra.getPalabra().contains(cadena)) {
                resultado.add(palabra);
            }
        }
        return resultado;
    }

}
