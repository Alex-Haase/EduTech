package com.EduTech_vm.cl.EduTech_vm.Assemblers;

import com.EduTech_vm.cl.EduTech_vm.Controller.carritoControllerV2;
import com.EduTech_vm.cl.EduTech_vm.Model.Curso;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class carritoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public @NonNull EntityModel<Curso> toModel(@NonNull Curso curso) {
        return EntityModel.of(curso,
            // Link al carrito general (se asume que verCarrito no necesita parámetros)
            linkTo(methodOn(carritoControllerV2.class).verCarrito()).withRel("carrito"),
            // Link para eliminar este curso, usando su id
            linkTo(methodOn(carritoControllerV2.class).eliminarCurso(curso.getId())).withRel("eliminar")
        );
    }

}