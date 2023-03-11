package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UnJugadorDTO {
    private Long id;
    private Long equipoId; //saca el campo nombre en lugar de los datos completos del equipo
    private String nick;
    private Integer puntos;
    private String avatar;
    private Date created_at;
    private Date updated_at;

}