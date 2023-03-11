package com.example.wordgames_api.controller;

import com.example.wordgames_api.dto.CreateEquipoDTO;
import com.example.wordgames_api.dto.EquipoDTO;
import com.example.wordgames_api.dto.UnEquipoDTO;
import com.example.wordgames_api.dto.UpdateEquipoDTO;
import com.example.wordgames_api.dto.converter.EquipoDTOConverter;
import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.modelo.Jugador;
import com.example.wordgames_api.repo.EquipoRepo;
import com.example.wordgames_api.repo.JugadorRepo;
import com.example.wordgames_api.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoService equipoService;
    private final EquipoDTOConverter equipoDTOConverter;

    private final JugadorRepo jugadorRepo;
    private final ModelMapper modelMapper;
    /**
     * Obtenemos todos los equipos
     * @return 404 si no hay equipos, 200 y lista de equipos si hay uno o más
     */
    @GetMapping("/equipos")
    public ResponseEntity<?> getAllEquipos() {
        List<Equipo> result = equipoService.findAll();

        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<EquipoDTO> dtoList = result.stream().map(equipoDTOConverter::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtengo un equipo por su id
     * @param id
     * @return 404 si no encuentra el equipo, si lo encuentra devuelve 200 y el equipo
     */
    @GetMapping("/equipo/{id}")
    public ResponseEntity<?> getOneEquipo(@PathVariable Long id) {
        Equipo result = equipoService.findById(id).orElse(null);

        List<Long> integrantes = jugadorRepo.findByEquipoId(id).stream()
                .map(Jugador::getId)
                .collect(Collectors.toList());

        UnEquipoDTO unEquipoDTO = modelMapper.map(result, UnEquipoDTO.class);
        unEquipoDTO.setIntegrantes(integrantes);

        return (unEquipoDTO == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(unEquipoDTO);
    }

    /**
     * Crea nuevo equipo
     * @param newEquipoData datos del nuevo equipo a crear
     * @return 201 y el nuevo equipo creado
     */
    @PostMapping("/equipos")
    public ResponseEntity<?> newEquipo(@RequestBody CreateEquipoDTO newEquipoData){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipoService.newEquipo(newEquipoData));
    }

    /**
     * Actualiza el nombre, los puntos y/o el logo del equipo.
     * @param newData datos nuevos
     * @param id del equipo a modificar
     * @return 200 Ok si la edición tiene éxito, 404 si no se encuentra el equipo
     */
    @PutMapping("/equipo/{id}")
    public ResponseEntity<?> updateEquipo (@RequestBody UpdateEquipoDTO newData, @PathVariable Long id){

        Equipo updatedEquipo = equipoService.updateEquipo(newData, id);

        if (updatedEquipo == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedEquipo);
    }

    /**
     * Borra un equipo
     * @param id del equipo a borrar
     * @return Código 204 sin contenido o 404 si el equipo no existe
     */
    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<?> deleteEquipo (@PathVariable Long id){
        Equipo equipo = equipoService.findById(id).orElse(null);
        if(equipo == null)
            return ResponseEntity.notFound().build();

        equipoService.delete(equipo);
        return ResponseEntity.noContent().build();

    }



}
