package com.EduTech_vm.cl.EduTech_vm.Controller;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Service.carritoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito")
@CrossOrigin(origins = "*")
public class carritoController {

    private final carritoService carritoService;

    public carritoController(carritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping("/agregar/{id}")
    public String agregarCurso(@PathVariable int id) {
        carritoService.agregar(id);
        return "Curso agregado al carrito";
    }

    @GetMapping
    public List<Curso> verCarrito() {
        return carritoService.listar();
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable int id) {
        carritoService.eliminar(id);
        return "Curso eliminado del carrito";
    }

    @DeleteMapping("/vaciar")
    public String vaciarCarrito() {
        carritoService.vaciar();
        return "Carrito vaciado";
    }
}
