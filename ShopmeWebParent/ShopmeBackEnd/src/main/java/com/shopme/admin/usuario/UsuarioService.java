package com.shopme.admin.usuario;

import com.shopme.common.entity.Papel_Perfil;
import com.shopme.common.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuario_UsuarioRepository;
    @Autowired
    private Papel_PerfilRepository papelFuncao_PerfilRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodosUsuarios(){
        return (List<Usuario>) usuario_UsuarioRepository.findAll();
    }

    public List<Papel_Perfil> listarTodosPapelPerfil(){
        return (List<Papel_Perfil>) papelFuncao_PerfilRepository.findAll();
    }


    public void save(Usuario usuario) {
        encodeSenha(usuario);
        usuario_UsuarioRepository.save(usuario);
    }

    private void encodeSenha(Usuario usuario) {
        String encodedSenha = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encodedSenha);
    }

    public boolean existeEsseEmailUnico(String email) {
        Usuario usuarioDoEmail = usuario_UsuarioRepository.findByEmail(email);
        return usuarioDoEmail == null;
    }
}
