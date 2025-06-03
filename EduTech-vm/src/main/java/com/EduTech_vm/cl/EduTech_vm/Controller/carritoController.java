package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Service.cursoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/carrito")
public class carritoController {
    private final List<Curso> carrito = new ArrayList<>();

    @Autowired
    private cursoService cursoService;
    @PostMapping("/agregar/{id}")
    public String agregarCurso(@RequestBody int id) {
        Curso curso = cursoService.getCursoId(id);
        if (curso != null) {
            carrito.add(curso);
            return "Curso agregado al carrito: " + curso.getTitulo();
        }
        return "Curso no encontrado";
    }

    @GetMapping
    public List<Curso> verCarrito(){
        return carrito;
    }
    
}
