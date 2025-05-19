package com.shopme.admin.usuario;

import com.shopme.common.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public String listarTodosUsuarios(Model model) {
        List<Usuario> listaDeUsuarios = usuarioService.listarTodosUsuarios();
        model.addAttribute("listaDeUsuarios", listaDeUsuarios); // minha lista de usuarios sendo enviada para /usuarios
        return "usuarios"; // templates/usuarios
    }
}
