package com.EduTech_vm.cl.EduTech_vm.Repository;

import com.EduTech_vm.cl.EduTech_vm.Model.MensajeContacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<MensajeContacto, Long> {
    
}
