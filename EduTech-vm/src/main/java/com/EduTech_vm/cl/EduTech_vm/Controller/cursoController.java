package com.EduTech_vm.cl.EduTech_vm.Controller;

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


@RestController
@RequestMapping("/api/v1/cursos")
public class cursoController {
    @Autowired
    private cursoService cursoService;

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.getCursos();
    }

    @PostMapping
    public Curso agregarCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @GetMapping("{id}")
    public Curso buscarCurso(@PathVariable int id){
        return cursoService.getCursoId(id);
    }

    @PutMapping("{id}")
    public Curso actualizarCurso(@PathVariable int id, @RequestBody Curso curso){
        // el id lo usaremos mas adelante
        return cursoService.updateCurso(curso);
    }

    @DeleteMapping("{id}")
    public String eliminarCurso(@PathVariable int id) {
        return cursoService.deleteCurso(id);
    }


    @GetMapping("/total")
    public int totalCursosV2() {
        return cursoService.totalCursosV2();
    }
}
