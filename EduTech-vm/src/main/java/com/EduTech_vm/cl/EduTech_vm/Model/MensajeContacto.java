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

    @JsonProperty("name")
    private String nombre;

    private String email;

    @JsonProperty("subject")
    private String asunto;

    @JsonProperty("message")
    private String mensaje;
}