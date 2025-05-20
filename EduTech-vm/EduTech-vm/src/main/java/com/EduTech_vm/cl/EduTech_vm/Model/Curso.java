package com.EduTech_vm.cl.EduTech_vm.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //Genera getters, setters, toString, equals, hashCode y un constructor con los campos requeridos.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor // Genera un constructor con todos los campos.
public class Curso {
    private int id;
    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaTermino;
    private int capacidad;
    private String profesor;
}

