package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.MensajeContacto;
import com.EduTech_vm.cl.EduTech_vm.Repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "*")// Permitir llamadas desde tu HTML si está en otro origen
public class MensajeController {
    @Autowired
    private MensajeRepository repository;
 
    @PostMapping
    public MensajeContacto guardarMensajeContacto(@RequestBody MensajeContacto mensaje) {
    System.out.println("Mensaje recibido: " + mensaje);
    return repository.save(mensaje);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
