package com.EduTech_vm.cl.EduTech_vm.Controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Service.cursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.EduTech_vm.cl.EduTech_vm.Assembler.CursoModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;
import  java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos")
public class cursoControllerV2 {
    @Autowired
    private cursoService cursoService;

    @Autowired
    private CursoModelAssembler assembler;
    
    @Operation(summary = "Listar todos los cursos", description = "Obtiene una lista de todos los cursos disponibles")
    @GetMapping
    public CollectionModel<EntityModel<Curso>> cursos =listarCursos(){}
    
    public List<Curso> listarCursos() {
        return cursoService.getCursos();
    }
    
    @Operation(summary = "Agregar un nuevo curso", description = "Permite agregar un nuevo curso a la plataforma")
    @PostMapping
    public Curso agregarCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @Operation(summary = "Buscar un curso por ID", description = "Obtiene un curso específico por su ID")
    @GetMapping("{id}")
    public Curso buscarCurso(@PathVariable int id){
        return cursoService.getCursoId(id);
    }

    @Operation(summary = "Actualizar un curso", description = "Permite actualizar la información de un curso existente")
    @PutMapping("{id}")
    public Curso actualizarCurso(@PathVariable int id, @RequestBody Curso curso){
        // el id lo usaremos mas adelante
        return cursoService.updateCurso(curso);
    }

    @Operation(summary = "Eliminar un curso", description = "Permite eliminar un curso específico de la plataforma")
    @DeleteMapping("{id}")
    public String eliminarCurso(@PathVariable int id) {
        return cursoService.deleteCurso(id);
    }


    @Operation(summary = "Contar el total de cursos", description = "Obtiene el número total de cursos disponibles")
    @GetMapping("/total")
    public int totalCursosV2() {
        return cursoService.totalCursosV2();
    }
}
