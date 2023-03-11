package com.example.wordgames_api.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="jugador_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name="juego_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Juego juego;
    private String palabra;
    private Integer puntos;
    private Date created_at;
    private Date updated_at;

}