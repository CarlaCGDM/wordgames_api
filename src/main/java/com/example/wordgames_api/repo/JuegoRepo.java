package com.example.wordgames_api.repo;

import com.example.wordgames_api.modelo.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepo extends JpaRepository<Juego,Long> {
}
