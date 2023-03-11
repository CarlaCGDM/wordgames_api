package com.example.wordgames_api.service;

import com.example.wordgames_api.dto.CreateEquipoDTO;
import com.example.wordgames_api.dto.UpdateEquipoDTO;
import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.repo.EquipoRepo;
import com.example.wordgames_api.repo.JugadorRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.wordgames_api.service.base.BaseService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EquipoService extends BaseService<Equipo, Long, EquipoRepo> {

    private final EquipoRepo equipoRepo;
    private final JugadorRepo jugadorRepo;
    private final ModelMapper modelMapper;

    /* Necesitamos servicios para el create y el update de equipo, pero no para el get
    y el delete porque son muy sencillos.
     */

    public Equipo newEquipo (CreateEquipoDTO newEquipoData){

        Equipo equipo = new Equipo();
        Date now = new Date();

        equipo.setNombre(newEquipoData.getNombre());
        equipo.setCreated_at(now);
        equipo.setUpdated_at(now);
        equipo.setPuntos(0);

        return save(equipo);
    }

    public Equipo updateEquipo(UpdateEquipoDTO newData, Long id){

        Equipo equipo = equipoRepo.findById(id).orElse(null);
        if(equipo == null)
            return null;

        Date now = new Date();

        if (newData.getNombre() != null)
            equipo.setNombre(newData.getNombre());
        if (newData.getLogo() != null)
            equipo.setLogo(newData.getLogo());
        if (newData.getPuntos() != null)
            equipo.setPuntos(newData.getPuntos());
        equipo.setUpdated_at(now);

        return save(equipo);
    }

}

