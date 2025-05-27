package com.EduTech_vm.cl.EduTech_vm.Repository;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface usuarioRepository extends JpaRepository<usuario, Long>{
    Optional <usuario> findByEmail(String email);
}
