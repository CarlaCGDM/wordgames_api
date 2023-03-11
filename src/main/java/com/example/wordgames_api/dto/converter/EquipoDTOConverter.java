package com.example.wordgames_api.dto.converter;

import com.example.wordgames_api.dto.EquipoDTO;
import com.example.wordgames_api.modelo.Equipo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipoDTOConverter {
    private final ModelMapper modelMapper;
    public EquipoDTO convertToDto(Equipo equipo) {
        return modelMapper.map(equipo, EquipoDTO.class);

    }
}
