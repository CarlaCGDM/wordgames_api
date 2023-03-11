package com.example.wordgames_api.controller;

import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.modelo.Jugador;
import com.example.wordgames_api.service.JugadorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.aspectj.bridge.MessageUtil.fail;

@SpringBootTest
@AutoConfigureMockMvc
public class JugadorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JugadorService jugadorService;

    @Test
    public void testGetAllJugadores() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/jugadores/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testGetOneJugador() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/jugador/12/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));;
    }

    @Test
    public void testCreateJugador() throws Exception {

        // Simulación de cuerpo de la request

        String testJugador= "{\n" +
                "    \"nick\": \"JugadorTest8\",\n" +
                "    \"clave\": \"pestillo\",\n" +
                "    \"equipo_id\": 1,\n" +
                "    \"created_at\": \"2023-03-10T18:15:35.000+00:00\",\n" +
                "    \"updated_at\": \"2023-03-10T18:15:35.000+00:00\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/jugadores/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testJugador))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateJugador() throws Exception {

        // Vemos cual es el último que se ha añadido
        List<Jugador> result = jugadorService.findAll();
        Jugador masNuevo = result.get(result.size() - 1);

        // Probamos a ponerle 100 puntos en lugar de 0

        String testNewData = "{\"puntos\":1}";

        mockMvc.perform(MockMvcRequestBuilders.put("/jugador/" + masNuevo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testNewData))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteJugador() throws Exception {

        // Vemos cual es el último que se ha añadido

        List<Jugador> result = jugadorService.findAll();
        Jugador masNuevo = result.get(result.size() - 1);

        // Probamos a borrarlo

        mockMvc.perform(MockMvcRequestBuilders.delete("/jugador/" + masNuevo.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}