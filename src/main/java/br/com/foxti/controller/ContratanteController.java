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
import br.com.foxti.model.Contratante;
import br.com.foxti.service.ContratanteService;
import br.com.foxti.service.exception.EntidadeExistenteException;

@Controller
@RequestMapping("contratantes")
public class ContratanteController {

	@Autowired
	private ContratanteService contratanteService;

	@GetMapping
	public ModelAndView listar(@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("contratante/PesquisaContratante");
		PageWrapper<Contratante> pagina = new PageWrapper<>(contratanteService.findAll(pageable), request);
		mv.addObject("pagina", pagina);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView cadastrar(Contratante contratante) {
		return new ModelAndView("contratante/CadastroContratante");
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Contratante contratante, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastrar(contratante);
		}
		try {
			contratanteService.save(contratante);
		} catch (EntidadeExistenteException ex) {
			result.rejectValue("nome", ex.getMessage(), ex.getMessage());
			return cadastrar(contratante);
		}
		attributes.addFlashAttribute("mensagem", "Contratante salvo com sucesso!");
		return new ModelAndView("redirect:/contratantes");
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Contratante contratante = contratanteService.buscarOuFalhar(id);
		ModelAndView mv = cadastrar(contratante);
		mv.addObject(contratante);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		contratanteService.delete(id);
		return ResponseEntity.ok().build();
	}

}
