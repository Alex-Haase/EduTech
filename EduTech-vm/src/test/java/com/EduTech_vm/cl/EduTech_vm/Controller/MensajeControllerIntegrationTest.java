package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.MensajeContacto;
import com.EduTech_vm.cl.EduTech_vm.Repository.MensajeRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MensajeController.class)
public class MensajeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MensajeRepository mensajeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void guardarMensajeContacto_debeRetornarMensajeGuardado() throws Exception {
        MensajeContacto mensaje = new MensajeContacto();
        mensaje.setNombre("Juan");
        mensaje.setEmail("juan@example.com");
        mensaje.setMensaje("Hola, este es un mensaje de prueba.");

        when(mensajeRepository.save(any(MensajeContacto.class))).thenReturn(mensaje);

        mockMvc.perform(post("/api/contacto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mensaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.email").value("juan@example.com"))
                .andExpect(jsonPath("$.mensaje").value("Hola, este es un mensaje de prueba."));
    }

    @Test
    void ping_debeResponderPong() throws Exception {
        mockMvc.perform(get("/api/contacto/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("pong"));
    }
}
