package com.shopme.admin.usuario;

import com.shopme.common.entity.Papel_Perfil;
import com.shopme.common.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        boolean estaAtualizandoUsuario = (usuario.getId() != null);

        if (estaAtualizandoUsuario) {
            Usuario existiUsuario = usuario_UsuarioRepository.findById(usuario.getId()).get();

            if (usuario.getSenha().isEmpty()){
                usuario.setSenha(existiUsuario.getSenha());
            }else {
                encodeSenha(usuario);
            }
        }else {
            encodeSenha(usuario);
        }

        usuario_UsuarioRepository.save(usuario);
    }

    private void encodeSenha(Usuario usuario) {
        String encodedSenha = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encodedSenha);
    }

    public boolean existeEsseEmailUnico(Integer id, String email) {
        Usuario usuarioDoEmail = usuario_UsuarioRepository.findByEmail(email);

        if(usuarioDoEmail == null) return true;

        boolean estaCriandoNovo = (id == null);

        if (estaCriandoNovo){
            if (usuarioDoEmail != null ) return false;
        }else{
            if (usuarioDoEmail.getId() != id) return false;
        }

        return true;
    }

    public Usuario get(Integer id) throws UsuarioNotFoundException {
        try{
            return usuario_UsuarioRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new UsuarioNotFoundException("Não foi possível encontrar nenhum usuário com ID " + id);
        }
    }

    /**
     * Exclui um usuário com base no seu ID.
     *
     * <p>Caso não seja encontrado nenhum usuário com o ID informado,
     * é lançada uma {@link UsuarioNotFoundException} com uma mensagem personalizada.</p>
     *
     * @param id Identificador único do usuário a ser excluído
     * @throws UsuarioNotFoundException se não existir nenhum usuário com o ID informado
     */
    public void delete(Integer id) throws UsuarioNotFoundException {
        Long countById = usuario_UsuarioRepository.countById(id);
        if (countById == null || countById == 0) {
            // Mensagem para exceção personalizada
            throw new UsuarioNotFoundException("Não foi possível encontrar nenhum usuário com o ID " + id);
        }
        usuario_UsuarioRepository.deleteById(id);
    }


}
