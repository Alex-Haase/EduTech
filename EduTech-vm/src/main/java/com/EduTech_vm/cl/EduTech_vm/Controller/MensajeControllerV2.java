package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.MensajeContacto;
import com.EduTech_vm.cl.EduTech_vm.Repository.MensajeRepository;
import com.EduTech_vm.cl.EduTech_vm.Assemblers.MensajeContactoModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/contacto")
@CrossOrigin
@Tag(name = "Contacto", description = "Operaciones relacionadas con mensajes de contacto")
public class MensajeControllerV2 {

    @Autowired
    private MensajeRepository repository;

    @Autowired
    private MensajeContactoModelAssembler assembler;

    @Operation(summary = "Registrar un nuevo mensaje de contacto")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MensajeContacto>> guardarMensajeContacto(@RequestBody MensajeContacto mensaje) {
        MensajeContacto guardado = repository.save(mensaje);
        return ResponseEntity
                .created(linkTo(methodOn(MensajeControllerV2.class).obtenerMensajePorId(guardado.getId())).toUri())
                .body(assembler.toModel(guardado));
    }

    @Operation(summary = "Obtener mensaje por ID")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<MensajeContacto> obtenerMensajePorId(@PathVariable Long id) {
        MensajeContacto mensaje = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con ID: " + id));
        return assembler.toModel(mensaje);
    }

    @Operation(summary = "Listar todos los mensajes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<MensajeContacto>> listarMensajes() {
        List<EntityModel<MensajeContacto>> mensajes = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(mensajes,
            linkTo(methodOn(MensajeControllerV2.class).listarMensajes()).withSelfRel());
    }

    @Operation(summary = "Eliminar mensaje de contacto")
    @DeleteMapping("/{id}")
    public String eliminarMensaje(@PathVariable Long id) {
        repository.deleteById(id);
        return "Mensaje eliminado con ID: " + id;
    }
}
