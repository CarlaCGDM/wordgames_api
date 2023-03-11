package com.example.wordgames_api.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="equipo_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Equipo equipo;
    private String nick;
    private String clave;
    private Integer puntos;
    private String avatar;
    private Date created_at;
    private Date updated_at;

}
