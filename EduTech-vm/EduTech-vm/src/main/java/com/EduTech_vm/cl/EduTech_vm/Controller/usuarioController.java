package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import com.EduTech_vm.cl.EduTech_vm.Service.usuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.HashMap; // Importar la clase para crear un mapa del objeto 
import java.util.Map; // Importar la clase Map para manejar los pares clave:valor

@RestController
@RequestMapping("/api/v2/usuarios")
public class usuarioController {
    @Autowired 
    private usuarioService serv;

    //Metodo para agregar usuarios a la database
    @PostMapping("/registrar")
    public usuario registrar(@RequestBody usuario u) { 
        
        return serv.registrar(u); //Llamar la funcion Registrar del Service
    }
    
    //Metodo para autenticar los usuarios logueados
    @PostMapping("/login")
    public Map<String, String> Login(@RequestBody usuario u) {
        Optional<usuario> user = serv.autenticar(u.getEmail(), u.getPassword()); //Autenticar el usuario con el email y password
        Map<String, String> respuesta = new HashMap<>(); //Crear un nuevo mapa para alamcenar las respuestas de la funcion Autenticar
        if (user.isPresent()) {
            respuesta.put("result", "OK");
            respuesta.put("nombre", user.get().getNombre());
        } else {
            respuesta.put("result", "ERROR");
        }

        return respuesta;
    }
}
