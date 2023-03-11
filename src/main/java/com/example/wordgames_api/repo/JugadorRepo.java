package com.example.wordgames_api.repo;

import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.modelo.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepo extends JpaRepository<Jugador,Long> {

    List<Jugador> findByEquipoId(Long equipo_id);

}
