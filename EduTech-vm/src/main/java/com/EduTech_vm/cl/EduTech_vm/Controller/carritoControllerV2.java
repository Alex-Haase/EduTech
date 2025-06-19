package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Service.cursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//Importar las librerias de Swagger para la documentación de las API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//Importamos las librerías de swagger para la documentación de la API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//Importar el assembler para HATEOAS
import com.EduTech_vm.cl.EduTech_vm.Assemblers.CursoModelAssembler;

//Importar las clases necesarias para HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
// Importar las clases de HATEOAS EntityModel y CollectionModel para manejar los modelos de respuesta
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

// Importar las clases de responseEntity para manejar respuestas HTTP
import org.springframework.http.ResponseEntity;

// Importar streams y colecciones para manejar listas de cursos
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carrito")
//La anotación Tag se usa para agrupar y etiquetar los controladores dentro de la documentación
@Tag(name = "Carrito de Compras", description = "Operaciones sobre el carrito de compras")
public class carritoControllerV2 {
    private final List<Curso> carrito = new ArrayList<>();

    @Autowired
    private cursoService cursoService;

    // Inyectamos el assembler para HATEOAS
    @Autowired
    private CursoModelAssembler assembler;

    //Agregar un curso al carrito
    //La anotación Operation se usa para describir cada metodo REST o endpoint individual
    @Operation(summary = "Agregar un producto al carrito de compras", description = "Agrega un curso al carrito de compras")
    @PostMapping("/agregar/{id}")
    public ResponseEntity<String> agregarCurso(@PathVariable int id) {
        Curso curso = cursoService.getCursoId(id);
        if (curso != null) {
            carrito.add(curso);
            return ResponseEntity.ok("Curso agregado al carrito: " + curso.getTitulo());
        }
        return ResponseEntity.badRequest().body("Curso no encontrado");
    }
    //Ver el carrito
    @Operation(summary = "Muestrar los productos agregados al carrito de compras", description = "Muestra todos los cursos en el carrito de compras")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Curso>> verCarrito() {
        List<EntityModel<Curso>> cursos = carrito.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(cursos,
                linkTo(methodOn(carritoControllerV2.class).verCarrito()).withSelfRel());
    }

    //Eliminar un curso del carrito
    @Operation(summary = "Eliminar un producto del carrito de compras", description = "Elimina un curso del carrito de compras por ID")
    @DeleteMapping(value = "/eliminar/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<String> eliminarCurso(@PathVariable int id) {
        boolean eliminado = carrito.removeIf(curso -> curso.getId() == id);
        return eliminado ? ResponseEntity.ok("Curso eliminado del carrito")
        : ResponseEntity.badRequest().body("Curso no estaba en el carrito");
    }
    //Vaciar el carrito
    @Operation(summary = "Vaciar el carrito de compras", description = "Elimina todos los cursos del carrito de compras")
    @DeleteMapping(value = "/vaciar", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<String> vaciarCarrito() {
        carrito.clear();
        return ResponseEntity.ok("Carrito vaciado");
    }
    //Contar los Cursos en el carrito
    @Operation(summary = "Contar los producto del carrito de compras", description = "Devuelve el númeto total de Cursos en el carrito de compras")
    @GetMapping("/total")
    public int totalCursosCarrito() {
        return carrito.size();
    }
}
