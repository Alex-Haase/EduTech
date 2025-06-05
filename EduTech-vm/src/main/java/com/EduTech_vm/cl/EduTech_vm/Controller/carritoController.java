package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Service.cursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1/carrito")
public class carritoController {
    private final List<Curso> carrito = new ArrayList<>();

    @Autowired
    private cursoService cursoService;
    
    @PostMapping("/agregar/{id}")
    public String agregarCurso(@PathVariable int id){
        Curso curso = cursoService.getCursoId(id);
        if (curso != null){
            carrito.add(curso);
            return "Curso agregado al carrito: " + curso.getTitulo();
        }
        return "Curso no encontrado";
    }

    @GetMapping
    public List<Curso> verCarrito(){
        return carrito;
    }
    @DeleteMapping
    public String eliminarCurso(@PathVariable int id){
        boolean eliminado = carrito.removeIf(curso -> curso.getId() == id);
        return eliminado ? "Curso eliminado del carrito" : "Curso no estaba en el carrito";
    }
    @DeleteMapping("/vaciar")
    public String vaciarCarrito() {
        carrito.clear();
        return "Carrito vaciado";
    }
    @GetMapping("/total")
    public int totalCursosCarrito(){
        return carrito.size();
    }
}
