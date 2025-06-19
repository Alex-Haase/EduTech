package com.EduTech_vm.cl.EduTech_vm.Assemblers;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Controller.cursoControllerV2;

//Importar las clases static para crear los enlaces HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//Importar la clase EntityModel para usar HATEOAS
import org.springframework.hateoas.EntityModel;

//Importar la interfaz RepresentationModelAssembler para crear el ensamblador de LibroModelAssembler
import org.springframework.hateoas.server.RepresentationModelAssembler;

//Importar los stereotipos necesarios para el ensamblador
import org.springframework.stereotype.Component;

//Importar la anotación NonNull para indicar que el método no acepta valores nulos
import org.springframework.lang.NonNull;

//Agregar la anotación Component para indicar que nuestra clase LibroModelAssembler es un componente Spring
@Component
//La clase LibroModelAssemnbler debe implementar a RepresentationModelAssembler para convertir un objeto de Libro en EntityModel

public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {
    @Override
    public @NonNull EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
            linkTo(methodOn(cursoControllerV2.class).buscarCurso(curso.getId())).withSelfRel(),
            linkTo(methodOn(cursoControllerV2.class).listarCursos()).withRel("curso"),
            linkTo(methodOn(cursoControllerV2.class).eliminarCurso(curso.getId())).withRel("eliminar"),
            linkTo(methodOn(cursoControllerV2.class).actualizarCurso(curso.getId(), curso)).withRel("actualizar"));
    }
}
