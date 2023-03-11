package com.example.wordgames_api.controller;

import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class EquipoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EquipoService equipoService;

    @Test
    public void testGetAllEquipos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/equipos/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testGetOneEquipo() throws Exception {

        List<Equipo> result = equipoService.findAll();
        Equipo masNuevo = result.get(result.size() - 1);

        mockMvc.perform(MockMvcRequestBuilders.get("/equipo/" + masNuevo.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));;

        mockMvc.perform(MockMvcRequestBuilders.get("/equipo/123456789"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateEquipo() throws Exception {

        // Simulación de cuerpo de la request

        String testEquipo = "{\n" +
                "    \"nombre\": \"EquipoTest4\",\n" +
                "    \"created_at\": \"2023-03-10T18:15:35.000+00:00\",\n" +
                "    \"updated_at\": \"2023-03-10T18:15:35.000+00:00\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/equipos/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testEquipo))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateEquipo() throws Exception {

        // Vemos cual es el último que se ha añadido
        List<Equipo> result = equipoService.findAll();
        Equipo masNuevo = result.get(result.size() - 1);

        // Probamos a ponerle 100 puntos en lugar de 0

        String testNewData = "{\"puntos\":100}";

        mockMvc.perform(MockMvcRequestBuilders.put("/equipo/" + masNuevo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(testNewData))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteEquipo() throws Exception {

        // Vemos cual es el último que se ha añadido

        List<Equipo> result = equipoService.findAll();
        Equipo masNuevo = result.get(result.size() - 1);

        // Probamos a borrarlo

        mockMvc.perform(MockMvcRequestBuilders.delete("/equipo/" + masNuevo.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}