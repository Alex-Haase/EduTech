package com.EduTech_vm.cl.EduTech_vm.Model;

import java.util.Optional;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String password;

    public static Optional<usuario> map(Object o){
        throw new UnsupportedOperationException("Uniplementend method 'map'");
    }
}
