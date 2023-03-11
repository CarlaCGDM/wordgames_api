package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEquipoDTO {
    private String nombre;
    private String logo;
    private Integer puntos;
}
