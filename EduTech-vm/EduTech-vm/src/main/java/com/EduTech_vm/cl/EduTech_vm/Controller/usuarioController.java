package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import com.EduTech_vm.cl.EduTech_vm.Service.usuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/usuarios")
@CrossOrigin
public class usuarioController {
    @Autowired
    private usuarioService serv;

    
    @PostMapping("/registrar")
    public usuario registrar(@RequestBody usuario u) {//crear un usuario en la tabla usuario       
        return serv.registrar(u);//llamar la funcion registrar del usuarioService 
    }
    //Metodo para autenticar los usuarios en la base de datos
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody com.EduTech_vm.cl.EduTech_vm.Model.usuario u) {      
        Optional <com.EduTech_vm.cl.EduTech_vm.Model.usuario> user = serv.autenticar(u.getEmail(), u.getPassword()); // auntenticar al usuario con el email y el password
        Map<String,String> respuesta = new HashMap<>();// crea un mapa para almacenar la respuesta de lo anterior
        if (user.isPresent()){
            respuesta.put("Result","Ok");
            respuesta.put("Nombre", user.get().getNombre());
        }else{
            respuesta.put("Result", "ERROR");
        }
        return respuesta;
    }
}
