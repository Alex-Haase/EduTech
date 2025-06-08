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

    public List<Curso> listar() {
        return new ArrayList<>(carrito);
    }

    public boolean agregar(int cursoId) {
        Curso curso = cursoRepository.buscarPorId(cursoId);
        if (curso != null) {
            carrito.add(curso);
            return true;
        }
        return false;
    }

    public boolean eliminar(int cursoId) {
        return carrito.removeIf(c -> c.getId() == cursoId);
    }

    public void vaciar() {
        carrito.clear();
    }
}
