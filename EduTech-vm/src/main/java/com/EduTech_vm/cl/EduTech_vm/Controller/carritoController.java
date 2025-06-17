package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Service.carritoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/carrito")
@CrossOrigin(origins = "*")
@Tag(name = "Carrito de Compras", description = "Operaciones relacionadas con el carrito de compras")
public class carritoController {

    private final carritoService carritoService;

    public carritoController(carritoService carritoService) {
        this.carritoService = carritoService;
    }

    @Operation(summary = "Agregar un curso al carrito", description = "Permite agregar un curso al carrito de compras")
    @PostMapping("/agregar/{id}")
    public String agregarCurso(@PathVariable int id) {
        Curso curso = carritoService.agregar(id);
        if (curso != null) {
            return "Curso agregado al carrito: " + curso.getTitulo();
        } else {
            return "Curso no encontrado";
        }
    }

    @Operation(summary = "Ver el carrito", description = "Permite ver los cursos en el carrito de compras")
    @GetMapping
    public List<Curso> verCarrito() {
        return carritoService.listar();
    }

    @Operation(summary = "Eliminar un curso del carrito", description = "Permite eliminar un curso específico del carrito de compras")
    @DeleteMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable int id) {
        boolean eliminado = carritoService.eliminar(id);
        if (eliminado) {
            return "Curso eliminado del carrito";
        } else {
            return "Curso no encontrado en el carrito";
        }
    }

    @Operation(summary = "Vaciar el carrito", description = "Permite vaciar todos los cursos del carrito de compras")
    @DeleteMapping("/vaciar")
    public String vaciarCarrito() {
        carritoService.vaciar();
        return "Carrito vaciado";
    }

    @Operation(summary = "Cantidad total de cursos en el carrito", description = "Retorna el número total de cursos en el carrito")
    @GetMapping("/total")
    public String totalCarrito() {
        return String.valueOf(carritoService.total());
    }
}
