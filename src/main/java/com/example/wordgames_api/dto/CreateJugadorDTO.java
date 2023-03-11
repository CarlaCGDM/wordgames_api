package com.example.wordgames_api.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CreateJugadorDTO {
    private Long id;

    private Long equipo_id;
    private String nick;
    private String clave;
    private Integer puntos;
    private String avatar;
    private Date created_at;
    private Date updated_at;

    //Añadir: integrantes del equipo

}