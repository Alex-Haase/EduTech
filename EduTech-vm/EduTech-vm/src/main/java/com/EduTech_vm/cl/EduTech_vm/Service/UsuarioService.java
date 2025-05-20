package com.EduTech_vm.cl.EduTech_vm.Service;

import com.EduTech_vm.cl.EduTech_vm.Model.usuario;
import com.EduTech_vm.cl.EduTech_vm.Repository.usuarioRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usuarioService {
    @Autowired
    private usuarioRepository repo;

    public usuario registrar(usuario u){
        return repo.save(u);
    }

    public Optional <usuario> autenticar(String email, String password){
        return repo.findByEmail(email).filter(u-> u.getPassword().equals(password));
    }
}
