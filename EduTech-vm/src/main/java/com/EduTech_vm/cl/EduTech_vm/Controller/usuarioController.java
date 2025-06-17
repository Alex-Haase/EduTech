package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import com.EduTech_vm.cl.EduTech_vm.Service.usuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/usuarios")
@CrossOrigin //controlador para manejar las peticiones que realiza los usuarios
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class usuarioController {//clase maneja las peticiones REST (GET,PUT,POST,DELETE,)
    @Autowired
    private usuarioService serv;

    //Metodo para crear usuarios
    @Operation(summary = "Registrar un nuevo usuario", description = "Permite registrar un nuevo usuario en la plataforma")

    @PostMapping("/registrar")
    public usuario registrar(@RequestBody usuario u) {//crear un usuario en la tabla usuario       
        return serv.registrar(u);//llamar la funcion registrar del usuarioService 
    }

    //Metodo para autenticar los usuarios en la base de datos
    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario iniciar sesión en la plataforma")

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody com.EduTech_vm.cl.EduTech_vm.Model.usuario u) {      
        Optional <com.EduTech_vm.cl.EduTech_vm.Model.usuario> user = serv.autenticar(u.getEmail(), u.getPassword()); // auntenticar al usuario con el email y el password
        Map<String,String> respuesta = new HashMap<>();// crea un mapa para almacenar la respuesta de lo anterior
        if (user.isPresent()){
            respuesta.put("result","OK");
            respuesta.put("nombre", user.get().getNombre());
            respuesta.put("email", user.get().getEmail());
        }else{
            respuesta.put("result", "ERROR");
        }
        return respuesta;
    }
    
}
