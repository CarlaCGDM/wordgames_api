package com.example.wordgames_api.dto.converter;

import com.example.wordgames_api.dto.EquipoDTO;
import com.example.wordgames_api.dto.JuegoDTO;
import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.modelo.Juego;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JuegoDTOConverter {

    private final ModelMapper modelMapper;
    public JuegoDTO convertToDto(Juego juego) {
        return modelMapper.map(juego, JuegoDTO.class);

    }
}
