package com.EduTech_vm.cl.EduTech_vm.Service;

import java.util.ArrayList;
import java.util.List;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Repository.cursoRepository;

import org.springframework.stereotype.Service;

@Service
public class carritoService {

    private final List<Curso> carrito = new ArrayList<>();
    private final cursoRepository cursoRepository;

    public carritoService(cursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Listar todos los cursos en el carrito
    public List<Curso> listar() {
        return new ArrayList<>(carrito);
    }

    // Agregar un curso al carrito y devolverlo
    public Curso agregar(int cursoId) {
        Curso curso = cursoRepository.buscarPorId(cursoId);
        if (curso != null) {
            carrito.add(curso);
        }
        return curso;
    }

    // Eliminar un curso por su ID
    public boolean eliminar(int cursoId) {
        return carrito.removeIf(c -> c.getId() == cursoId);
    }

    // Vaciar todo el carrito
    public void vaciar() {
        carrito.clear();
    }

    // Obtener la cantidad total de cursos en el carrito
    public int total() {
        return carrito.size();
    }
}
