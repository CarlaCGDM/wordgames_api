package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CreateEquipoDTO {
    private Long id;
    private String nombre;
    private Integer puntos;
    private String logo;
    private Date created_at;
    private Date updated_at;

    //Añadir: integrantes del equipo

}