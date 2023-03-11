package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartidaDTO {
    private Long id;
    private String jugadorNick;
    private String juegoNombre;
    private String palabra;
    private Integer puntos;

}