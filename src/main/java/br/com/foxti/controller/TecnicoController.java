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
import br.com.foxti.model.Tecnico;
import br.com.foxti.service.TecnicoService;

@Controller
@RequestMapping("tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping
	public ModelAndView listar(@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tecnico/PesquisaTecnico");
		PageWrapper<Tecnico> pagina = new PageWrapper<>(tecnicoService.findAll(pageable), request);
		mv.addObject("pagina", pagina);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView cadastrar(Tecnico tecnico) {
		return new ModelAndView("tecnico/CadastroTecnico");
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Tecnico tecnico, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastrar(tecnico);
		}
		tecnicoService.save(tecnico);

		attributes.addFlashAttribute("mensagem", "Técnico salvo com sucesso!");
		return new ModelAndView("redirect:/tecnicos");
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Tecnico tecnico = tecnicoService.buscarOuFalhar(id);
		ModelAndView mv = cadastrar(tecnico);
		mv.addObject(tecnico);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		tecnicoService.delete(id);
		return ResponseEntity.ok().build();
	}

}
