package com.example.wordgames_api.controller;

import com.example.wordgames_api.dto.*;
import com.example.wordgames_api.dto.converter.JuegoDTOConverter;
import com.example.wordgames_api.dto.converter.PartidaDTOConverter;
import com.example.wordgames_api.modelo.Juego;
import com.example.wordgames_api.modelo.Jugador;
import com.example.wordgames_api.modelo.Partida;
import com.example.wordgames_api.repo.PartidaRepo;
import com.example.wordgames_api.service.JuegoService;
import com.example.wordgames_api.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaService partidaService;
    private final PartidaDTOConverter partidaDTOConverter;
    private final PartidaRepo partidaRepo;

    /**
     * Obtenemos todas las partidas
     * @return 404 si no hay partidas, 200 y lista de partidas si hay uno o más
     */
    @GetMapping("/partidas")
    public ResponseEntity<?> getAllPartidas() {
        List<Partida> result = partidaService.findAll();

        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<PartidaDTO> dtoList = result.stream().map(partidaDTOConverter::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtengo una partida por su id
     * @param id
     * @return 404 si no encuentra la partida, si lo encuentra devuelve 200 y la partida
     */
    @GetMapping("/partida/{id}")
    public ResponseEntity<?> getOnePartida(@PathVariable Long id) {
        Partida result = partidaService.findById(id).orElse(null);

        return (result == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(result);
    }

    /**
     * Obtengo todas las partidas de un jugador concreto por su id
     * @param jugador_id
     * @return 404 si no encuentra partidas, si las encuentra devuelve 200 y las partidas
     */
    @GetMapping("/partidas/{jugador_id}")
    public ResponseEntity<?> getAllPartidasByJugador(@PathVariable Long jugador_id) {
        List<Partida> partidas = partidaRepo.findByJugadorId(jugador_id);

        List<PartidaDTO> dtoList = partidas.stream().map(partidaDTOConverter::convertToDto)
                .collect(Collectors.toList());

        return (dtoList == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(dtoList);
    }

    /**
     * Crea nueva partida
     * Las partidas se crean con 0 puntos al empezar y se actualizan con la puntuación cuando el jugador ha terminado
     * @param newPartidaData datos de la nueva partida a crear
     * @return 201 y el nuevo jugador creado
     */
    @PostMapping("/partidas")
    public ResponseEntity<?> newPartida(@RequestBody CreatePartidaDTO newPartidaData){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(partidaService.newPartida(newPartidaData));
    }

    /**
     * Actualizar la puntuación de una partida
     * Las partidas se crean con 0 puntos al empezar y se actualizan con la puntuación cuando el jugador ha terminado
     * Cuando se actualiza la puntuación de una partida hay que actualizar también los puntos totales del jugador y del equpo
     * @param newData datos de la nueva partida a actualizar
     * @param id de la partida a modificar
     * @return 200 Ok si la edición tiene éxito, 404 si no se encuentra la partida
     */

    @PutMapping("/partida/{id}")
    public ResponseEntity<?> updatePartida (@RequestBody UpdatePartidaDTO newData, @PathVariable Long id){

        Partida updatedPartida = partidaService.updatePartida(newData, id);

        if (updatedPartida == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedPartida);
    }

    /**
     * Borra un jugador
     * @param id del jugador a borrar
     * @return Código 204 sin contenido o 404 si el jugador no existe
     */
    @DeleteMapping("/partida/{id}")
    public ResponseEntity<?> deletePartida (@PathVariable Long id){
        Partida partida = partidaService.findById(id).orElse(null);
        if(partida == null)
            return ResponseEntity.notFound().build();

        partidaService.delete(partida);
        return ResponseEntity.noContent().build();

    }
}