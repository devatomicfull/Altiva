package com.shopme.admin.usuario;

import com.shopme.common.entity.Papel_Perfil;
import com.shopme.common.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("pageTitle", "Criar novo usuario");
        return "usuario_form";
    }

    @PostMapping("/usuarios/salvar")
    public String salvarUsuario(Usuario usuario, RedirectAttributes redirectAttributes){
        System.out.println(usuario);
        usuarioService.save(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "O usuario foi salvo com sucesso!");
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/edit/{id}")
    public String editarUsuario(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioService.get(id);
            List<Papel_Perfil> papel_perfilList = usuarioService.listarTodosPapelPerfil();

            model.addAttribute("usuario", usuario);// usuario do form: object
            model.addAttribute("pageTitle", "Editar usuario (ID: " + id + ")");
            model.addAttribute("papel_perfilList", papel_perfilList);

            return "usuario_form";
        }catch (UsuarioNotFoundException erro){
            redirectAttributes.addFlashAttribute("mensagem", erro.getMessage());
            return "redirect:/usuarios";
        }
    }

    /**
     * Exclui um usuário com base no seu ID.
     *
     * <p>Este método é responsável por receber o ID do usuário através da URL
     * e acionar o serviço {@code usuarioService.delete(id)} para tentar remover o usuário do banco de dados.
     * Caso o usuário não seja encontrado, uma exceção personalizada {@link UsuarioNotFoundException}
     * é capturada e uma mensagem é adicionada como atributo flash para ser exibida na página.
     *
     * <p>Ao final, o método redireciona para a lista de usuários.
     *
     * @param id o identificador único do usuário que se deseja excluir.
     * @param model o modelo usado para passar dados à view (não utilizado aqui, mas necessário para consistência do handler).
     * @param redirectAttributes usado para adicionar mensagens flash que serão exibidas após o redirecionamento.
     * @return uma string de redirecionamento para a lista de usuários.
     */
    @GetMapping("/usuarios/delete/{id}")
    public String deleteUsuario(@PathVariable(name = "id") Integer id,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        try {
            usuarioService.delete(id); // tenta deletar o usuário pelo ID
            // Mensagem opcional de sucesso (se quiser mostrar depois):
            redirectAttributes.addFlashAttribute("mensagem", "Usuário deletado com sucesso!");
            return "redirect:/usuarios"; // redireciona para a lista de usuários
        } catch (UsuarioNotFoundException erro) {
            // Caso o usuário não seja encontrado, envia a mensagem de erro
            redirectAttributes.addFlashAttribute("mensagem", erro.getMessage());
            return "redirect:/usuarios"; // redireciona para a lista de usuários mesmo com erro
        }
    }

}
