package com.example.wordgames_api.service;

import com.example.wordgames_api.modelo.Juego;
import com.example.wordgames_api.repo.JuegoRepo;
import com.example.wordgames_api.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JuegoService extends BaseService<Juego, Long, JuegoRepo> {

    private final JuegoRepo juegoRepo;

}