package com.EduTech_vm.cl.EduTech_vm.Assemblers;

import com.EduTech_vm.cl.EduTech_vm.Controller.carritoControllerV2;
import com.EduTech_vm.cl.EduTech_vm.Model.Curso;

// Importar la clase Static para crear enlaces HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// Importar la clase EntityModel para usar HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
// Importar la anotación NonNull para indicar que el método no acepta valores nulos
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

//agrega anotacion component para indicar q la clase cursoModelAssembler es un componnte spring
@Component
public class carritoModelAssembler  implements RepresentationModelAssembler<Curso, EntityModel<Curso>>{
    @Override
    public @NonNull EntityModel<Curso> toModel(Curso curso){
        return EntityModel.of(curso,
        linkTo(methodOn(carritoControllerV2.class).verCarrito()).withRel("carrito"),
        linkTo(methodOn(carritoControllerV2.class).eliminarCurso(curso.getId())).withRel("eliminar"));
    } 
}