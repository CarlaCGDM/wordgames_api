package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJugadorDTO {
    private String nick;
    private String avatar;
    private String clave;
    private Long equipo_id;
    private Integer puntos;
}
