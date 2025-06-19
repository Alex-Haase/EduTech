package com.EduTech_vm.cl.EduTech_vm.Assemblers;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import com.EduTech_vm.cl.EduTech_vm.Controller.usuarioController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class usuarioModelAssembler implements RepresentationModelAssembler<usuario, EntityModel<usuario>> {

    @Override
    public @NonNull EntityModel<usuario> toModel(@NonNull usuario u) {
        return EntityModel.of(u,
            // Link al registro de usuarios (POST /registrar)
            linkTo(methodOn(usuarioController.class).registrar(null)).withRel("registrar"),
            
            // Link para login (POST /login)
            linkTo(methodOn(usuarioController.class).login(null)).withRel("login")
        );
    }
}