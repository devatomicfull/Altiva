package com.shopme.admin.usuario;

import com.shopme.common.entity.Papel_Perfil;
import com.shopme.common.entity.Usuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTests {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCriarUsuario(){
        Papel_Perfil papelPerfil_Admin = entityManager.find(Papel_Perfil.class, 1);
        Usuario usuarioTest = new Usuario("teste@testegmail.com", "teste123","teste", "teste");
        usuarioTest.addPapelPerfil(papelPerfil_Admin);

        Usuario salvoUsuario = repository.save(usuarioTest);
        assertThat(salvoUsuario.getId()).isGreaterThan(0);
    }

    @Test
    public void testCriarNovoUsuarioComUmPapelPerfil(){
        Papel_Perfil papelPerfil_Admin = entityManager.find(Papel_Perfil.class, 2);
        Usuario usuarioTest = new Usuario("123@testegmail.com", "123","t", "teste");
        usuarioTest.addPapelPerfil(papelPerfil_Admin);

        Usuario salvoUsuario = repository.save(usuarioTest);
        assertThat(salvoUsuario.getId()).isGreaterThan(0);
    }

    @Test
    public void testCriarNovoUsuarioComDoisPapelPerfil(){
        Usuario usuarioTest = new Usuario("test@test.com", "123","test", "teste");
        Papel_Perfil papelPerfil_Editor = new Papel_Perfil(3);
        Papel_Perfil papelPerfil_Assistente = new Papel_Perfil(5);

        usuarioTest.addPapelPerfil(papelPerfil_Editor);
        usuarioTest.addPapelPerfil(papelPerfil_Assistente);

        Usuario salvoUsuario = repository.save(usuarioTest);
        assertThat(salvoUsuario.getId()).isGreaterThan(0);
    }

    @Test
    public void testListarTodosUsuarios(){
        Iterable<Usuario> usuarios = repository.findAll();
        usuarios.forEach(usuario -> System.out.println(usuario));
        assertThat(usuarios).hasSizeGreaterThan(0);
    }

    @Test
    public void testGetUsuarioPorId(){
        Usuario usuarioNome = repository.findById(1).get();
        System.out.println(usuarioNome);
        assertThat(usuarioNome).isNotNull();
    }

    @Test
    public void testAtualizarUsuarioDetalhes(){
        Usuario usuario = repository.findById(1).get();
        usuario.setAtivo(Boolean.TRUE);
        usuario.setEmail("teste@deucerto.com");

        repository.save(usuario);
    }

    @Test
    public void testAtualizarUsuarioPapelPerfil(){
        Usuario usuario = repository.findById(2).get();
        Papel_Perfil papel_perfil_Editor = entityManager.find(Papel_Perfil.class, 3);
        Papel_Perfil papel_perfil_Admin = entityManager.find(Papel_Perfil.class, 5);
        usuario.getPapelPerfils().remove(papel_perfil_Editor);
        usuario.getPapelPerfils().add(papel_perfil_Admin);

        repository.save(usuario);
    }

    @Test
    public void testDeletarUsuario(){
        Usuario usuario = repository.findById(1).get();
        repository.delete(usuario);
    }

    @Test
    public void testDeletarUsuarioPorId(){
        repository.deleteById(2);

    }
}
