package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JuegoDTO {

    private Long id;
    private String nombre;
    private Integer dificultad;
    private String instrucciones;
    //Añadir: integrantes del equipo


}