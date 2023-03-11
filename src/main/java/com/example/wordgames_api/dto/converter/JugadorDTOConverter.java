package com.example.wordgames_api.dto.converter;

import com.example.wordgames_api.dto.JugadorDTO;
import com.example.wordgames_api.modelo.Jugador;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JugadorDTOConverter {
    private final ModelMapper modelMapper;
    public JugadorDTO convertToDto(Jugador jugador) {
        return modelMapper.map(jugador, JugadorDTO.class);

    }
}
