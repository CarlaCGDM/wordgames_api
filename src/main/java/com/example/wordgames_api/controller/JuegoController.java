package com.example.wordgames_api.controller;

import com.example.wordgames_api.dto.JuegoDTO;
import com.example.wordgames_api.dto.converter.JuegoDTOConverter;
import com.example.wordgames_api.modelo.Juego;
import com.example.wordgames_api.service.JuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JuegoController {

    private final JuegoService juegoService;
    private final JuegoDTOConverter juegoDTOConverter;

    /**
     * Obtenemos todos los juegos
     * @return 404 si no hay juegos, 200 y lista de juegos si hay uno o más
     */
    @GetMapping("/juegos")
    public ResponseEntity<?> getAllJuegos() {
        List<Juego> result = juegoService.findAll();

        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<JuegoDTO> dtoList = result.stream().map(juegoDTOConverter::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtengo un juego por su id
     * @param id
     * @return 404 si no encuentra el juego, si lo encuentra devuelve 200 y el juego
     */
    @GetMapping("/juego/{id}")
    public ResponseEntity<?> getOneJuego(@PathVariable Long id) {
        Juego result = juegoService.findById(id).orElse(null);

        return (result == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(result);
    }

}
