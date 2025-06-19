package com.EduTech_vm.cl.EduTech_vm.Assemblers;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import com.EduTech_vm.cl.EduTech_vm.Controller.usuarioControllerV2;

// Importar la clase Static para crear enlaces HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

// Importar la clase EntityModel para usar HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// Importar la anotación NonNull para indicar que el método no acepta valores nulos
import org.springframework.lang.NonNull;

@Component//La anotación Component indica que esta clase es un componente de Spring y puede ser inyectada en otros componentes o controladores
// Clase que implementa RepresentationModelAssembler para convertir un objeto usuario en un EntityModel

public class usuarioModelAssembler implements RepresentationModelAssembler<usuario, EntityModel<usuario>> {
    @Override// Anotación Override para indicar que este método implementa un método de la interfaz RepresentationModelAssembler
    public @NonNull EntityModel<usuario> toModel(usuario u) { // Este método toma un objeto usuario y lo convierte en un EntityModel y con @NonNull indicamos que no acepta valores nulos. Usamos EntityModel para envolver el objeto usuario y añadir enlaces HATEOAS
        // Usamos linkTo y methodOn para crear enlaces HATEOAS para las operaciones
        return EntityModel.of(u,
                linkTo(methodOn(usuarioControllerV2.class).registrar(null)).withSelfRel(),
                linkTo(methodOn(usuarioControllerV2.class).login(u)).withRel("login"));
    }
}