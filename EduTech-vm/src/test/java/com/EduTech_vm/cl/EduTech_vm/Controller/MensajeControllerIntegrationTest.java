package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Service.cursoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(cursoController.class)
public class MensajeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private cursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarCursos_debeRetornarListaJson() throws Exception {
        List<Curso> cursos = List.of(
                new Curso(1, "informatica", "Introduccion a la informatica","20-04-2005" , "20-05-2005",30 ,"Alan Gajardo",50000),
                new Curso(2, "cocina", "Introduccion a la cocina","20-05-2005" , "20-06-2005",25 ,"Sofia Mezas",40000));

        when(cursoService.getCursos()).thenReturn(cursos);
        //ver q las rutas esten bn definidas
        mockMvc.perform(get("/api/v1/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("Curso1"));
    }

    @Test
    void agregarCurso_debeGuardarYRetornarCurso() throws Exception {
        Curso curso = new Curso(0, "aplicaciones moviles", "introduccion a aplicaiones moviles", "20-05-2025", "20-06-2025", 30, "Henrique Morales",12000);

        when(cursoService.saveCurso(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(post("/api/v1/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Nuevo curso"));
    }

    @Test
    void buscarCurso_porId_existente() throws Exception {
        Curso curso = new Curso(1, "informatica", "Introduccion a la informatica","20-04-2005" , "20-05-2005",30 ,"Alan Gajardo",50000);

        when(cursoService.getCursoId(5)).thenReturn(curso);

        mockMvc.perform(get("/api/v1/cursos/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Buscado"));
    }

    @Test
    void eliminarCurso_existente() throws Exception {
        when(cursoService.deleteCurso(3)).thenReturn("producto eliminado");

        mockMvc.perform(delete("/api/v1/cursos/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("producto eliminado"));
    }

    @Test
    void totalCursosV2_debeRetornarCantidad() throws Exception {
        when(cursoService.totalCursosV2()).thenReturn(10);

        mockMvc.perform(get("/api/v1/cursos/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }
}
