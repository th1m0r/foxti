package br.com.foxti.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.foxti.controller.page.PageWrapper;
import br.com.foxti.model.Usuario;
import br.com.foxti.service.UsuarioService;
import br.com.foxti.service.exception.UsuarioExistenteException;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ModelAndView listar(@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("usuario/PesquisaUsuario");
		PageWrapper<Usuario> pagina = new PageWrapper<>(usuarioService.findAll(pageable), request);
		mv.addObject("pagina", pagina);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView cadastrar(Usuario usuario) {
		return new ModelAndView("usuario/CadastroUsuario");
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastrar(usuario);
		}
		try {
			usuarioService.save(usuario);
		} catch (UsuarioExistenteException ex) {
			result.rejectValue("login", ex.getMessage(), ex.getMessage());
			return cadastrar(usuario);
		}
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Usuario usuario = usuarioService.findById(id);
		ModelAndView mv = cadastrar(usuario);
		mv.addObject(usuario);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		usuarioService.delete(id);
		return ResponseEntity.ok().build();
	}

}
