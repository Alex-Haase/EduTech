package com.EduTech_vm.cl.EduTech_vm.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.EduTech_vm.cl.EduTech_vm.Model.Curso;;

@Repository
public class cursoRepository {
     
    // Arreglo que guardara todos los cursos
    private final List<Curso> listaCursos = new ArrayList<>();

    public cursoRepository() {
        // Agregar cursos por defecto
        listaCursos.add(new Curso(1, "informatica", "Introduccion a la informatica","20-04-2005" , "20-05-2005",30 ,"Alan Gajardo",50000));
        listaCursos.add(new Curso(2, "cocina", "Introduccion a la cocina","20-05-2005" , "20-06-2005",25 ,"Sofia Mezas",40000));
        listaCursos.add(new Curso(3, "ciberceguridad", "Introduccion a la ciberseguridad","20-04-2005" , "20-05-2005",30 ,"Pavel Morales",60000));
    }

    // Metodo que retorna todoa los cursos
    public List<Curso> obtenerCursos() {
        return listaCursos;
    }

    // Buscar un curso por su id
    public Curso buscarPorId(int id) {
        for (Curso curso : listaCursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    public Curso guardar(Curso cur) {
        // Generar nuevo ID secuencial
        long nuevoId = 1;
        for (Curso l : listaCursos) {
            if (l.getId() >= nuevoId) {
                nuevoId = l.getId() + 1;
            }
        }

        // Crear una nueva instancia con los datos del curso recibido
        Curso curso = new Curso();
        curso.setId((int) nuevoId); // ID generado automáticamente
        curso.setTitulo(cur.getTitulo());
        curso.setDescripcion(cur.getDescripcion());
        curso.setFechaInicio(cur.getFechaInicio());
        curso.setFechaTermino(cur.getFechaTermino());
        curso.setCapacidad(cur.getCapacidad());
        curso.setProfesor(cur.getProfesor());
        curso.setPrecio(cur.getPrecio());

        // Agregar el nuevo curso a la lista
        listaCursos.add(curso);

        return curso;
    }

    public Curso actualizar(Curso cur) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaCursos.size(); i++) {
            if (listaCursos.get(i).getId() == cur.getId()) {
                id = cur.getId();
                idPosicion = i;
            }
        }

        Curso curso1 = new Curso();
        curso1.setId(id);
        curso1.setTitulo(cur.getTitulo());
        curso1.setDescripcion(cur.getDescripcion());
        curso1.setFechaInicio(cur.getFechaInicio());
        curso1.setFechaTermino(cur.getFechaTermino());
        curso1.setCapacidad(cur.getCapacidad());
        curso1.setProfesor(cur.getProfesor());
        curso1.setPrecio(cur.getPrecio());

        listaCursos.set(idPosicion, curso1);
        return curso1;
    }

    public void eliminar(int id) {
        listaCursos.removeIf(x -> x.getId() == id);
    }

    public int totalcursos() {
        return listaCursos.size();
    }
}
