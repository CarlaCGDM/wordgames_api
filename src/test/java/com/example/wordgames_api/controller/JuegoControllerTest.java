package com.example.wordgames_api.controller;

import com.example.wordgames_api.modelo.Equipo;
import com.example.wordgames_api.modelo.Juego;
import com.example.wordgames_api.service.JuegoService;
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
public class JuegoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JuegoService juegoService;

    @Test
    public void testGetAllJuegos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/juegos/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testGetOneEquipo() throws Exception {

        List<Juego> result = juegoService.findAll();
        Juego masNuevo = result.get(result.size() - 1);

        mockMvc.perform(MockMvcRequestBuilders.get("/juego/" + masNuevo.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));;

        mockMvc.perform(MockMvcRequestBuilders.get("/juego/123456789"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}