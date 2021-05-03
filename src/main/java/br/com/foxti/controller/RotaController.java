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
import br.com.foxti.model.Rota;
import br.com.foxti.service.RotaService;
import br.com.foxti.service.exception.EntidadeExistenteException;

@Controller
@RequestMapping("rotas")
public class RotaController {

	@Autowired
	private RotaService rotaService;

	@GetMapping
	public ModelAndView listar(@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("rota/PesquisaRota");
		PageWrapper<Rota> pagina = new PageWrapper<>(rotaService.findAll(pageable), request);
		mv.addObject("pagina", pagina);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView cadastrar(Rota rota) {
		return new ModelAndView("rota/CadastroRota");
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Rota rota, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastrar(rota);
		}
		try {
			rotaService.save(rota);
		} catch (EntidadeExistenteException ex) {
			result.rejectValue("nome", ex.getMessage(), ex.getMessage());
			return cadastrar(rota);
		}
		attributes.addFlashAttribute("mensagem", "Rota salva com sucesso!");
		return new ModelAndView("redirect:/rotas");
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Rota rota = rotaService.buscarOuFalhar(id);
		ModelAndView mv = cadastrar(rota);
		mv.addObject(rota);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		rotaService.delete(id);
		return ResponseEntity.ok().build();
	}

}
