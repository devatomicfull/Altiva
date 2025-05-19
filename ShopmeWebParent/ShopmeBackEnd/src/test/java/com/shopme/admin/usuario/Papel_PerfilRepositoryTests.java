package com.shopme.admin.usuario;

import com.shopme.common.entity.Papel_Perfil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class Papel_PerfilRepositoryTests {
    @Autowired
    private Papel_PerfilRepository repository;

    @Test
    public void testCriarPrimeiroPapel_Perfil() {
        Papel_Perfil papelPerfil_Admin  = new Papel_Perfil("admin", "Administrador do sistema");
        Papel_Perfil salvoPapelPerfil = repository.save(papelPerfil_Admin);
        //assert salvoPapelPerfil.getId() != null; // verifica se o id foi gerado
        assertThat(salvoPapelPerfil.getId()).isGreaterThan(0); //
    }

    @Test
    public void testCriarRestPapel_Perfil() {
        Papel_Perfil papelPerfil_Vendedor  = new Papel_Perfil("vendedor", "vendedor, gerenciar preço do produto, clientes, remessa, pedidos e relatório de vendas");
        Papel_Perfil papelPerfil_Editor  = new Papel_Perfil("editor", "editor, gerenciar categorias, marcas, produtos, artigos e menus");
        Papel_Perfil papelPerfil_Expedidor  = new Papel_Perfil("expedidor", "visualizar produtos, visualizar pedidos e atualizar status do pedido");
        Papel_Perfil papelPerfil_Assistente  = new Papel_Perfil("assistente" , "gerenciar perguntas e avaliações");

        repository.saveAll(List.of(papelPerfil_Vendedor, papelPerfil_Editor, papelPerfil_Expedidor, papelPerfil_Assistente));
    }
}
