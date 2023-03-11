package com.example.wordgames_api.controller;

import com.example.wordgames_api.modelo.Juego;
import com.example.wordgames_api.modelo.Jugador;
import com.example.wordgames_api.modelo.Partida;
import com.example.wordgames_api.service.JuegoService;
import com.example.wordgames_api.service.JugadorService;
import com.example.wordgames_api.service.PartidaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PartidaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PartidaService partidaService;
    @Autowired
    private JugadorService jugadorService;

    @Test
    public void testGetAllPartidas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/partidas/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testGetOnePartida() throws Exception {

        List<Partida> result = partidaService.findAll();
        Partida masNuevo = result.get(result.size() - 1);

        mockMvc.perform(MockMvcRequestBuilders.get("/partida/" + masNuevo.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.get("/partida/123456789"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAllPartidasByJugador() throws Exception {
        List<Jugador> result = jugadorService.findAll();
        Jugador masNuevo = result.get(result.size() - 1);

        mockMvc.perform(MockMvcRequestBuilders.get("/partidas/" + masNuevo.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));;
    }

    @Test
    public void testCreatePartida() throws Exception {

        // Simulación de cuerpo de la request

        String testJugador= "{\n" +
                "    \"jugador_id\": 1,\n" +
                "    \"juego_id\": 1,\n" +
                "    \"puntos\": 0,\n" +
                "    \"palabra\": \"tejado\",\n" +
                "    \"created_at\": \"2023-03-10T18:15:35.000+00:00\",\n" +
                "    \"updated_at\": \"2023-03-10T18:15:35.000+00:00\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/partidas/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testJugador))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeletePartida() throws Exception {

        // Vemos cual es el último que se ha añadido

        List<Partida> result = partidaService.findAll();
        Partida masNuevo = result.get(result.size() - 1);

        // Probamos a borrarlo

        mockMvc.perform(MockMvcRequestBuilders.delete("/partida/" + masNuevo.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
