package com.example.wordgames_api.repo;

import com.example.wordgames_api.modelo.Jugador;
import com.example.wordgames_api.modelo.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepo extends JpaRepository<Partida,Long> {

    List<Partida> findByJugadorId(Long jugador_id);

}
