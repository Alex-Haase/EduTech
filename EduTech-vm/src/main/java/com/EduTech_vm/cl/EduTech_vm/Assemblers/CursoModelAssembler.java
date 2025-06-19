package com.EduTech_vm.cl.EduTech_vm.Assemblers;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Controller.cursoControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public @NonNull EntityModel<Curso> toModel(@NonNull Curso curso) {
        return EntityModel.of(curso,
            linkTo(methodOn(cursoControllerV2.class).buscarCurso(curso.getId())).withSelfRel(),
            linkTo(methodOn(cursoControllerV2.class).listarCursos()).withRel("cursos"),
            linkTo(methodOn(cursoControllerV2.class).eliminarCurso(curso.getId())).withRel("eliminar"),
            linkTo(methodOn(cursoControllerV2.class).actualizarCurso(curso.getId(), curso)).withRel("actualizar")
        );
    }

}
