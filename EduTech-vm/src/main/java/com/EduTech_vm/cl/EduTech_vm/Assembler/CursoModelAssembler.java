package com.EduTech_vm.cl.EduTech_vm.Assembler;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Controller.cursoControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

//agrega anotacion component para indicar q la clase cursoModelAssembler es un componnte spring
@Component
//la clase cursoModelAssembler debe contar a Representation para combertir a un objeto de curso en EntityModel
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {
    @Override
    public @NonNull EntityModel<Curso> toModel(Curso curso){
        return EntityModel.of(curso,
            linkTo(methodOn(cursoControllerV2.class).buscarCurso(curso.getId())).withSelfRel(),
            linkTo(methodOn(cursoControllerV2.class).listarCurso())).withRel("curso"),
            linkTo(methodOn(cursoControllerV2.class).ActualizarCurso()).withRel("curso"),
            linkTo(methodOn(cursoControllerV2.class).EliminarCurso()).withRel("curso")

    }
}
