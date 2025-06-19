package com.EduTech_vm.cl.EduTech_vm.Assemblers;

import com.EduTech_vm.cl.EduTech_vm.Model.MensajeContacto;
import com.EduTech_vm.cl.EduTech_vm.Controller.MensajeControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class MensajeContactoModelAssembler implements RepresentationModelAssembler<MensajeContacto, EntityModel<MensajeContacto>> {

    @Override
    public @NonNull EntityModel<MensajeContacto> toModel(MensajeContacto mensaje) {
        return EntityModel.of(mensaje,

            // Enlace al recurso individual
            linkTo(methodOn(MensajeControllerV2.class).obtenerMensajePorId(mensaje.getId()))
                .withSelfRel()
                .withTitle("Ver detalle del mensaje de contacto"),

            // Enlace para listar todos los mensajes
            linkTo(methodOn(MensajeControllerV2.class).listarMensajes())
                .withRel("mensajes")
                .withTitle("Ver todos los mensajes de contacto"),

            // Enlace para eliminar el mensaje
            linkTo(methodOn(MensajeControllerV2.class).eliminarMensaje(mensaje.getId()))
                .withRel("eliminar")
                .withTitle("Eliminar mensaje de contacto"));
    }
}