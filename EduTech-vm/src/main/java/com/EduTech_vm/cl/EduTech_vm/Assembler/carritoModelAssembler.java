package com.EduTech_vm.cl.EduTech_vm.Assembler;
package com.EduTech_vm.cl.EduTech_vm.Assembler;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Controller.carritControllerV2;
import com.EduTech_vm.cl.EduTech_vm.Controller.carritoController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.swing.text.html.parser.Entity;

import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

//agrega anotacion component para indicar q la clase cursoModelAssembler es un componnte spring
@Component
public class carritoModelAssembler  implements RepresentationModelAssembler<Curso, EntityModel<Curso>>{
    @Override
    public @NonNull EntityModel<Curso> toModel(Curso curso){
        return EntityModel.of(curso,
        linkTo(methodOn(carritoControllerV2.class).verCarrito)

    } 
    
}
