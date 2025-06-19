package com.EduTech_vm.cl.EduTech_vm.Assemblers;

import com.EduTech_vm.cl.EduTech_vm.Model.MensajeContacto;
import com.EduTech_vm.cl.EduTech_vm.Controller.MensajeControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MensajeContactoModelAssembler implements RepresentationModelAssembler<MensajeContacto, EntityModel<MensajeContacto>> {

    @Override
    public @NonNull EntityModel<MensajeContacto> toModel(@NonNull MensajeContacto mensaje) {
        return EntityModel.of(mensaje,
            linkTo(methodOn(MensajeControllerV2.class).obtenerMensajePorId(mensaje.getId()))
                .withSelfRel()
                .withTitle("Ver detalle del mensaje de contacto"),
            linkTo(methodOn(MensajeControllerV2.class).listarMensajes())
                .withRel("mensajes")
                .withTitle("Ver todos los mensajes de contacto"),
            linkTo(methodOn(MensajeControllerV2.class).eliminarMensaje(mensaje.getId()))
                .withRel("eliminar")
                .withTitle("Eliminar mensaje de contacto")
        );
    }
}