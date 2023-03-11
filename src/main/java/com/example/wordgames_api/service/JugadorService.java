package com.example.wordgames_api.service;

import com.example.wordgames_api.dto.CreateEquipoDTO;
import com.example.wordgames_api.dto.CreateJugadorDTO;
import com.example.wordgames_api.dto.UpdateEquipoDTO;
import com.example.wordgames_api.dto.UpdateJugadorDTO;
import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.modelo.Jugador;
import com.example.wordgames_api.repo.EquipoRepo;
import com.example.wordgames_api.repo.JugadorRepo;
import com.example.wordgames_api.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JugadorService extends BaseService<Jugador, Long, JugadorRepo> {

    private final JugadorRepo jugadorRepo;
    private final EquipoRepo equipoRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /* Necesitamos servicios para el create y el update de equipo, pero no para el get
    y el delete porque son muy sencillos. */


    public Jugador newJugador (CreateJugadorDTO newJugadorData){

        Jugador jugador = new Jugador();
        Date now = new Date();
        Equipo equipo = equipoRepo.findById(newJugadorData.getEquipo_id()).orElse(null);

        jugador.setClave(passwordEncoder.encode(newJugadorData.getClave()));
        jugador.setNick(newJugadorData.getNick());
        jugador.setEquipo(equipo);
        jugador.setCreated_at(now);
        jugador.setUpdated_at(now);
        jugador.setPuntos(0);
        return save(jugador);
    }

    public Jugador updateJugador(UpdateJugadorDTO newData, Long id){

        Jugador jugador = jugadorRepo.findById(id).orElse(null);
        if(jugador == null)
            return null;

        if (newData.getEquipo_id() != null) {
            Equipo equipo = equipoRepo.findById(newData.getEquipo_id()).orElse(null);
            if (equipo != null)
                jugador.setEquipo(equipo);
        }

        Date now = new Date();

        if (newData.getNick() != null)
            jugador.setNick(newData.getNick());
        if (newData.getClave() != null)
            jugador.setClave(passwordEncoder.encode(newData.getClave()));
        if (newData.getPuntos() != null)
            jugador.setPuntos(newData.getPuntos());
        if (newData.getAvatar() != null)
            jugador.setAvatar(newData.getAvatar());
        jugador.setUpdated_at(now);

        return save(jugador);
    }

}