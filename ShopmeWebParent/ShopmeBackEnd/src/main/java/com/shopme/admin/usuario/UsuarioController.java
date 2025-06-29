package com.shopme.admin.usuario;

import com.shopme.common.entity.Papel_Perfil;
import com.shopme.common.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/usuarios/novo")
    public String novoUsuario(Model model) {
        List<Papel_Perfil> papel_perfilList = usuarioService.listarTodosPapelPerfil();
        Usuario usuario = new Usuario();
        usuario.setAtivo(true);
        model.addAttribute("usuario", usuario);
        model.addAttribute("papel_perfilList", papel_perfilList);
        return "usuario_form";
    }

    @PostMapping("/usuarios/salvar")
    public String salvarUsuario(Usuario usuario, RedirectAttributes redirectAttributes){
        System.out.println(usuario);
        usuarioService.save(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "O usuario foi salvo com sucesso!");
        return "redirect:/usuarios";
    }
}
