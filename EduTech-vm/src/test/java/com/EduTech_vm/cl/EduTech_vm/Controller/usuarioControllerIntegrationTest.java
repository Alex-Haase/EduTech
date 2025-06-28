package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import com.EduTech_vm.cl.EduTech_vm.Service.usuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; 
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Optional; 

@WebMvcTest(usuarioController.class)
public class usuarioControllerIntegrationTest {
   @Autowired
   private MockMvc mockMvc;
   @MockBean
   private usuarioService usuarioService;

   @Autowired
   private ObjectMapper objectMapper;

    @Test
    void registrarUsuario_ReturnGuardado()throws Exception{
        usuario nuevoUsuario = new usuario();//usar tabla ususario y crear una variable para crear un usuario simulado
        nuevoUsuario.setNombre("Alex");//nombre usuario simulado
        nuevoUsuario.setEmail("alex@gmail.com");//mail usuario simulado
        nuevoUsuario.setPassword("1234");//Password usuario simulado 
        
        //simula que el usuario existe
        when(usuarioService.registrar(any(usuario.class))).thenReturn(nuevoUsuario);
        //realiza peticion post

        mockMvc.perform(post("/api/v1/usuarios/registrar") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(nuevoUsuario)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Alex"))
            .andExpect(jsonPath("$.email").value("alex@gmail.com"))
            .andExpect(jsonPath("$.password").value("1234"));  
    }
    // Test para el caso de inicio de sesión con un usuario existente
    @Test
    void loginUsuario_ReturOK()throws Exception{
        usuario usuarioExistente = new usuario();
        usuarioExistente.setNombre("Alex");
        usuarioExistente.setEmail("alex@gmail.com");
        usuarioExistente.setPassword("1234");

        // Simular que el usuario existe
        when(usuarioService.autenticar("alex@gmail.com", "1234"))
            .thenReturn(Optional.of(usuarioExistente));

        //realizar la peticion post para iniciar sesion 
        mockMvc.perform(post("/api/v1/usuarios/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(usuarioExistente)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("OK"))
            .andExpect(jsonPath("$.nombre").value("Alex"))
            .andExpect(jsonPath("$.email").value("alex@gmail.com"));
            //.andExpect(jsonPath("$.password").value("1234"));
    }

    //test simulacion inicio sesion de usuario inexistente
    @Test
    void loginUsuario_ReturError() throws Exception{
        usuario usuarioInexistente = new usuario();
        usuarioInexistente.setEmail("noexiste@gmail.com");
        usuarioInexistente.setPassword("1234");

        //simula comportamiento login con usuario no registrado 

        when(usuarioService.autenticar("noexiste@gmail.com", "1234"))
            .thenReturn(Optional.empty());

            // Realizar la petición POST para iniciar sesión
        mockMvc.perform(post("/api/v1/usuarios/login") // Usar el endpoint de login
                .contentType(MediaType.APPLICATION_JSON) // Establecer el tipo de contenido a JSON
                .content(objectMapper.writeValueAsString(usuarioInexistente)))//
                .andExpect(status().isOk()) // Verificar que la respuesta tenga un estado 200 OK
                .andExpect(jsonPath("$.result").value("ERROR")); // Verificar que el resultado sea "Error"
    }
}




