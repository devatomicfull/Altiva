package com.shopme.admin.usuario;

import com.shopme.common.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodosUsuarios(){
        return (List<Usuario>) repository.findAll();
    }


}
