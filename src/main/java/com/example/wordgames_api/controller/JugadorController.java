package com.example.wordgames_api.controller;

import com.example.wordgames_api.dto.*;
import com.example.wordgames_api.dto.converter.JugadorDTOConverter;
import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.modelo.Jugador;
import com.example.wordgames_api.service.JugadorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class JugadorController {
    private final JugadorService jugadorService;
    private final JugadorDTOConverter jugadorDTOConverter;

    private final ModelMapper modelMapper;


    /**
     * Obtenemos todos los jugadores
     * @return 404 si no hay jugadores, 200 y lista de jugadores si hay uno o más
     */
    @GetMapping("/jugadores")
    public ResponseEntity<?> getAllJugadores() {
        List<Jugador> result = jugadorService.findAll();

        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<JugadorDTO> dtoList = result.stream().map(jugadorDTOConverter::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtengo un jugador por su id
     * @param id
     * @return 404 si no encuentra el jugador, si lo encuentra devuelve 200 y el jugador
     */
    @GetMapping("/jugador/{id}")
    public ResponseEntity<?> getOneJugador(@PathVariable Long id) {
        Jugador result = jugadorService.findById(id).orElse(null);

        UnJugadorDTO unJugadorDTO = modelMapper.map(result, UnJugadorDTO.class);

        return (unJugadorDTO == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(unJugadorDTO);
    }

    /**
     * Crea nuevo jugador
     * @param newJugadorData datos del nuevo jugador a crear
     * @return 201 y el nuevo jugador creado
     */

    @PostMapping("/jugadores")
    public ResponseEntity<?> newJugador(@RequestBody CreateJugadorDTO newJugadorData){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jugadorService.newJugador(newJugadorData));
    }

    /**
     * Actualiza el nick, los puntos, la clave, el equipo y/o el avatar del jugador.
     * @param newData datos nuevos
     * @param id del jugador a modificar
     * @return 200 Ok si la edición tiene éxito, 404 si no se encuentra el jugador
     */
    @PutMapping("/jugador/{id}")
    public ResponseEntity<?> updateJugador (@RequestBody UpdateJugadorDTO newData, @PathVariable Long id){

        Jugador updatedJugador = jugadorService.updateJugador(newData, id);

        if (updatedJugador == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedJugador);
    }
    /**
     * Borra un jugador
     * @param id del jugador a borrar
     * @return Código 204 sin contenido o 404 si el jugador no existe
     */
    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<?> deleteJugador (@PathVariable Long id){
        Jugador jugador = jugadorService.findById(id).orElse(null);
        if(jugador == null)
            return ResponseEntity.notFound().build();

        jugadorService.delete(jugador);
        return ResponseEntity.noContent().build();

    }


}