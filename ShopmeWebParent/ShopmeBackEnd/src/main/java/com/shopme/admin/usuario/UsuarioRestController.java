package com.shopme.admin.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST responsável por fornecer endpoints assíncronos relacionados a usuários.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Verifica se o e-mail informado já está cadastrado no sistema.
     *
     * <p>Este endpoint é utilizado em chamadas AJAX para validar, em tempo real,
     * se o e-mail digitado no formulário de cadastro já existe.</p>
     *
     * @param email e-mail informado no formulário
     * @return "OK" se o e-mail é único, ou "Duplicado" se já está registrado
     */
    @PostMapping("/check-email")
    public String checkDuplicidadeEmail(@Param("email") Integer id, @Param("email") String email) {
        return usuarioService.existeEsseEmailUnico(id, email) ? "OK" : "Duplicado";
    }
}
