package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Service.cursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

//Importar las librerias de Swagger para la documentación de las API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//Importar el assembler para HATEOAS de este controlador
import com.EduTech_vm.cl.EduTech_vm.Assemblers.CursoModelAssembler;

//Importar las clases de HATEOAS EntityModel, CollectionModel y MediaType para manejar los modelos de respuestas
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

//Importar las clases responseEntity para manejar las respuestas HTTP
import org.springframework.http.ResponseEntity;

//Importar Stram y colecciones para manejar la lista Cursos en java
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos")
public class cursoControllerV2 {
    @Autowired
    private cursoService cursoService;
    
    //Inyectar el assembler de Curso
    @Autowired
    private CursoModelAssembler assembler;
    
    @Operation(summary = "Listar todos los cursos", description = "Obtiene una lista de todos los cursos disponibles")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Curso>> listarCursos() {
        //Obtener la lista de cursos y la convertiremos a EntityModel usando el assembler
        List<EntityModel<Curso>> cursos = cursoService.getCursos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(cursos, 
            linkTo(methodOn(cursoControllerV2.class).listarCursos()).withSelfRel());
    }
    
    @Operation(summary = "Agregar un nuevo curso", description = "Permite agregar un nuevo curso a la plataforma")
    @PostMapping
    public Curso agregarCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    //BUSCAR
    @Operation(summary = "Buscar un curso por ID", description = "Obtiene un curso específico por su ID")
    @GetMapping(value = "/{id}", produces =  MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Curso> buscarCurso(@PathVariable int id){
        Curso curso = cursoService.getCursoId(id);
        return assembler.toModel(curso);
    }

    //actualiza
    @Operation(summary = "Actualizar un curso", description = "Permite actualizar la información de un curso existente")
    @PutMapping(value = "/{id}", produces =  MediaTypes.HAL_JSON_VALUE )
    public ResponseEntity<EntityModel<Curso>> actualizarCurso(@PathVariable int id, @RequestBody Curso curso) {
        curso.setId(id);
        Curso actualizado = cursoService.updateCurso(curso);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    //elimina
    @Operation(summary = "Eliminar un curso", description = "Permite eliminar un curso específico de la plataforma")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> eliminarCurso(@PathVariable int id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }

    //cuenat total
    @Operation(summary = "Contar el total de cursos", description = "Obtiene el número total de cursos disponibles")
    @GetMapping(value = "/total", produces = MediaTypes.HAL_JSON_VALUE)
    public int totalCursosV2() {
        return cursoService.totalCursosV2();
    }
}