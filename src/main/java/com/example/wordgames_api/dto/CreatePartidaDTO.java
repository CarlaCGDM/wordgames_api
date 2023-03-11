package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePartidaDTO {
    private Long id;
    private Long jugador_id;
    private Long juego_id;
    private String palabra;

}
