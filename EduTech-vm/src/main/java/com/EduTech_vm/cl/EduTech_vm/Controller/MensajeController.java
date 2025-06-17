package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.MensajeContacto;
import com.EduTech_vm.cl.EduTech_vm.Repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "*")// Permitir llamadas desde tu HTML si está en otro origen
@Tag(name = "Contacto", description = "Operaciones relacionadas con el contacto")
public class MensajeController {
    @Autowired
    private MensajeRepository repository;
 
    @Operation(summary = "Guardar mensaje de contacto", description = "Permite guardar un mensaje de contacto enviado por el usuario")
    @PostMapping
    public MensajeContacto guardarMensajeContacto(@RequestBody MensajeContacto mensaje) {
    System.out.println("Mensaje recibido: " + mensaje);
    return repository.save(mensaje);
    }

    @Operation(summary = "Verificar conexión", description = "Endpoint para verificar que el servidor está funcionando")
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
