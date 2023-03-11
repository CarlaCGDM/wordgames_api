package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EquipoDTO {

    private Long id;
    private String nombre;
    private Integer puntos;
    private String logo;
    //Añadir: integrantes del equipo


}