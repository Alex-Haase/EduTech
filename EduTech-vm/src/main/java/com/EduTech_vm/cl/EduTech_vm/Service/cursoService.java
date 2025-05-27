package com.EduTech_vm.cl.EduTech_vm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;
import com.EduTech_vm.cl.EduTech_vm.Repository.cursoRepository;

@Service
public class cursoService {
    @Autowired
    private cursoRepository cursoRepository;

    public List<Curso> getCursos() {
        return cursoRepository.obtenerCursos();
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.guardar(curso);
    }

    public Curso getCursoId(int id) {
        return cursoRepository.buscarPorId(id);
    }

    public Curso updateCurso(Curso curso) {
        return cursoRepository.actualizar(curso);
    }

    public String deleteCurso(int id) {
        cursoRepository.eliminar(id);
        return "producto eliminado";
    }

    // LA ACCIÓN LA HACE EL SERVICE
    public int totalcursos() {
        return cursoRepository.obtenerCursos().size();
    }

    // LA ACCIÓN LA HACE EL MODELO
    public int totalCursosV2() {
        return cursoRepository.totalcursos();
    }
}
