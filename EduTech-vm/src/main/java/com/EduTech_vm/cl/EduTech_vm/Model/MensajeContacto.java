package com.EduTech_vm.cl.EduTech_vm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mensajes")
@Data
public class MensajeContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nombre")
    private String nombre;

    private String email;

    @JsonProperty("asunto")
    private String asunto;

    @JsonProperty("mensaje")
    private String mensaje;
}