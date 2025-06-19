package com.EduTech_vm.cl.EduTech_vm.Assembler;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import com.EduTech_vm.cl.EduTech_vm.Controller.CursoControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

//agrega anotacion component para indicar q la clase cursoModelAssembler es un componnte spring
@Component

public class usuarioModelAssembler implements RepresentationModelAssembler<usuario, EntityModel<usuario>> {
    @Override
    public @NonNull EntityModel<usuario> toModel(usuario u){
        return EntityModel.of(u,
            linkTo(methodOn(usuarioControllerV2.class).registrar(null)).withSelfRel(),
            linkTo(methodOn(usuarioControllerV2.class).login(u)).withRel("login"));
          
    }
}
