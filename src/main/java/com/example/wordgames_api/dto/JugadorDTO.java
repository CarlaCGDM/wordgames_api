package com.example.wordgames_api.dto;

import com.example.wordgames_api.modelo.Equipo;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class JugadorDTO {
    private Long id;

    private Long equipoId; //saca el campo nombre en lugar de los datos completos del equipo
    private String nick;
    private Integer puntos;
    private String avatar;

}