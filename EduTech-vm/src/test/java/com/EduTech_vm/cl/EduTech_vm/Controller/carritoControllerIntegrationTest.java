package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Repository.cursoRepository;
import com.EduTech_vm.cl.EduTech_vm.Service.carritoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(carritoController.class)
public class carritoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private cursoRepository cursoRepository; // Simulamos el repositorio

    @MockBean
    private carritoService carritoService; // Necesario para inyectar el bean en el controlador

    private Curso cursoEjemplo;

    @BeforeEach
    void setUp() {
        cursoEjemplo = new Curso(1, "Java Básico", "Curso de Java para principiantes", "2025-06-10", "2025-07-10", 20, "Juan Pérez", 50000);
    }

    @Test
    void agregarCurso_alCarrito_debeResponderConfirmacion() throws Exception {
        when(carritoService.agregar(1)).thenReturn(cursoEjemplo);

        mockMvc.perform(post("/api/v1/carrito/agregar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Curso agregado al carrito: Java Básico"));
    }

    @Test
    void verCarrito_debeMostrarCursosAgregados() throws Exception {
        when(carritoService.listar()).thenReturn(List.of(cursoEjemplo));

        mockMvc.perform(get("/api/v1/carrito"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Java Básico"));
    }

    @Test
    void eliminarCurso_delCarrito_debeEliminarCorrectamente() throws Exception {
        when(carritoService.eliminar(1)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/carrito/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Curso eliminado del carrito"));
    }

    @Test
    void vaciarCarrito_debeResponderCorrectamente() throws Exception {
        mockMvc.perform(delete("/api/v1/carrito/vaciar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Carrito vaciado"));
    }

    @Test
    void totalLibrosCarrito_debeRetornarCantidad() throws Exception {
        when(carritoService.total()).thenReturn(1);

        mockMvc.perform(get("/api/v1/carrito/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
