package com.example.wordgames_api.controller;

import com.example.wordgames_api.modelo.Palabra;
import com.example.wordgames_api.service.JugadorService;
import com.example.wordgames_api.service.PalabraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PalabraController {

    private final PalabraService palabraService;

    /**
     *
     * @param cantidad
     * @param longitud
     * @param empiezaPorCadena
     * @param terminaPorCadena
     * @param contieneCadena
     * @return 404 si no encuentra palabras, si no 200 + lista de palabras
     * @throws Exception
     */
    @GetMapping("/palabras")
    public ResponseEntity<?> getAllPalabras(

            @RequestParam(required = false, name="cantidad") Long cantidad,
            @RequestParam(required = false, name="longitud") Long longitud,
            @RequestParam(required = false, name="empiezaPorCadena") String empiezaPorCadena,
            @RequestParam(required = false, name="terminaPorCadena") String terminaPorCadena,
            @RequestParam(required = false, name="contieneCadena") String contieneCadena

    ) throws Exception {

        List<Palabra> listaPalabras = palabraService.filtrarPalabras(
                cantidad,
                longitud,
                empiezaPorCadena,
                terminaPorCadena,
                contieneCadena);

        if(listaPalabras.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(listaPalabras);
        }
    }

}
