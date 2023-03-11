package com.example.wordgames_api.dto.converter;

import com.example.wordgames_api.dto.JugadorDTO;
import com.example.wordgames_api.dto.PartidaDTO;
import com.example.wordgames_api.modelo.Partida;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartidaDTOConverter {
    private final ModelMapper modelMapper;
    public PartidaDTO convertToDto(Partida partida) {
        return modelMapper.map(partida, PartidaDTO.class);

    }
}