package com.EduTech_vm.cl.EduTech_vm.Controller;

// Importar la clase modelo "usuario" desde el paquete correspondiente
import com.EduTech_vm.cl.EduTech_vm.Model.usuario;

// Importar el servicio asociado al modelo "usuario"
import com.EduTech_vm.cl.EduTech_vm.Service.usuarioService;

// Importar ObjectMapper, útil para convertir objetos Java a JSON y viceversa
import com.fasterxml.jackson.databind.ObjectMapper;

// Importar la anotación @Test para definir métodos de prueba
import org.junit.jupiter.api.Test; 

// Permite la inyección automática de dependencias
import org.springframework.beans.factory.annotation.Autowired; 

// Anotación para pruebas unitarias enfocadas solo en la capa de controlador web (Spring MVC)
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; 

// Anotación para crear mocks de beans (como servicios) para pruebas
import org.springframework.boot.test.mock.mockito.MockBean;

// Importar MediaType, que especifica el tipo de contenido (como application/json) en las solicitudes/respuestas
import org.springframework.http.MediaType;

// Herramienta de pruebas para realizar peticiones HTTP simuladas al controlador
import org.springframework.test.web.servlet.MockMvc; 

// Importar métodos para construir solicitudes HTTP simuladas (GET, POST, PUT, DELETE, etc.)
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// Importar métodos para verificar el resultado de la respuesta (como estado, contenido, etc.)
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Importar "any", que permite simular llamadas a métodos con cualquier argumento en pruebas con Mockito
import static org.mockito.ArgumentMatchers.any;

// Importar "when", usado para definir el comportamiento de los mocks (qué deben hacer cuando se les llama)
import static org.mockito.Mockito.when;

// Clase contenedor que puede o no contener un valor no nulo, útil para evitar null
import java.util.Optional; 


//usa anotacion WebMVCtest para controlador web de una clase especifica 
@WebMvcTest(usuarioController.class)
public class usuarioControllerIntegrationTest {
   @Autowired
   private MockMvc mockMvc;
   
   @MockBean
   private usuarioService usuarioService;

   //usar objectMapper para combertir los objetos en json
   @Autowired
   private ObjectMapper objectMapper;

   //tet simular creacion nuevo usuario
    @Test
    void registrarUsuario_ReturnGuardado()throws Exception{
        usuario newUser = new usuario();//usar tabla ususario y crear una variable para crear un usuario simulado
        newUser.setNombre("Alex");//nombre usuario simulado
        newUser.setEmail("alex@gmail.com");//mail usuario simulado
        newUser.setPassword("1234");//Password usuario simulado 
        
        //simula que el usuario existe
        when(usuarioService.registrar(any(usuario.class))).thenReturn(newUser);
    
        //realiza peticion post

        mockMvc.perform(post("/api/v2/usuarios/registrar") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newUser)))
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
        mockMvc.perform(post("/api/v2/usuarios/login")
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
        mockMvc.perform(post("/api/v2/usuarios/login") // Usar el endpoint de login
                .contentType(MediaType.APPLICATION_JSON) // Establecer el tipo de contenido a JSON
                .content(objectMapper.writeValueAsString(usuarioInexistente)))//
                .andExpect(status().isOk()) // Verificar que la respuesta tenga un estado 200 OK
                .andExpect(jsonPath("$.result").value("ERROR")); // Verificar que el resultado sea "Error"
    }
}




