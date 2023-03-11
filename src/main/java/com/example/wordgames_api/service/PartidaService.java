package com.example.wordgames_api.service;

import com.example.wordgames_api.dto.CreateJugadorDTO;
import com.example.wordgames_api.dto.CreatePartidaDTO;
import com.example.wordgames_api.dto.UpdateJugadorDTO;
import com.example.wordgames_api.dto.UpdatePartidaDTO;
import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.modelo.Juego;
import com.example.wordgames_api.modelo.Jugador;
import com.example.wordgames_api.modelo.Partida;
import com.example.wordgames_api.repo.EquipoRepo;
import com.example.wordgames_api.repo.JuegoRepo;
import com.example.wordgames_api.repo.JugadorRepo;
import com.example.wordgames_api.repo.PartidaRepo;
import com.example.wordgames_api.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PartidaService extends BaseService<Partida, Long, PartidaRepo> {

    private final PartidaRepo partidaRepo;
    private final JugadorRepo jugadorRepo;
    private final EquipoRepo equipoRepo;
    private final JuegoRepo juegoRepo;

    public Partida newPartida (CreatePartidaDTO newPartidaData){

        Partida partida = new Partida();
        Date now = new Date();
        Jugador jugador = jugadorRepo.findById(newPartidaData.getJugador_id()).orElse(null);
        Juego juego = juegoRepo.findById(newPartidaData.getJuego_id()).orElse(null);

        partida.setJugador(jugador);
        partida.setJuego(juego);

        partida.setPalabra(newPartidaData.getPalabra());

        partida.setPuntos(0);

        partida.setCreated_at(now);
        partida.setUpdated_at(now);

        return save(partida);
    }

    public Partida updatePartida(UpdatePartidaDTO newData, Long id){

        Partida partida = partidaRepo.findById(id).orElse(null);
        Date now = new Date();

        //Puntos de la partida
        partida.setPuntos(newData.getPuntos());
        partida.setUpdated_at(now);

        //Sumar puntos al total de puntos del jugador
        Jugador jugador = jugadorRepo.findById(partida.getJugador().getId()).orElse(null);
        jugador.setPuntos(jugador.getPuntos() + newData.getPuntos());
        jugador.setUpdated_at(now);

        //Sumar puntos al total de puntos del equipo
        Equipo equipo = equipoRepo.findById(jugador.getEquipo().getId()).orElse(null);
        equipo.setPuntos(equipo.getPuntos() + newData.getPuntos());
        equipo.setUpdated_at(now);

        return save(partida);
    }

}