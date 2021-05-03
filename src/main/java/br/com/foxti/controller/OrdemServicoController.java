package br.com.foxti.controller;

import java.time.LocalDate;
import java.util.Arrays;

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
import br.com.foxti.model.OrdemServico;
import br.com.foxti.model.OrdemServicoStatus;
import br.com.foxti.model.TipoServico;
import br.com.foxti.repository.ContratanteRepository;
import br.com.foxti.repository.RotaRepository;
import br.com.foxti.repository.TecnicoRepository;
import br.com.foxti.service.OrdemServicoService;

@Controller
@RequestMapping("ordens-servico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService ordemServicoService;

	@Autowired
	private ContratanteRepository contratanteRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private RotaRepository rotaRepository;
	

	@GetMapping
	public ModelAndView listar(@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ordem-servico/PesquisaOrdemServico");
		PageWrapper<OrdemServico> pagina = new PageWrapper<>(ordemServicoService.findAll(pageable), request);
		mv.addObject("pagina", pagina);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView cadastrar(OrdemServico ordemServico) {
		ModelAndView mv = new ModelAndView("ordem-servico/CadastroOrdemServico");
		
		ordemServico.setStatus(OrdemServicoStatus.ABERTA);
		ordemServico.setDataAbertura(LocalDate.now());
		
		mv.addObject("contratantes", contratanteRepository.findAll());
		mv.addObject("tecnicos",tecnicoRepository.findAll());
		mv.addObject("rotas",rotaRepository.findAll());
		mv.addObject("tiposServico", Arrays.asList(TipoServico.values()));
		
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid OrdemServico ordemServico, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastrar(ordemServico);
		}
		ordemServicoService.save(ordemServico);

		attributes.addFlashAttribute("mensagem", "Ordem de serviço salva com sucesso!");
		return new ModelAndView("redirect:/ordens-servico");
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		OrdemServico ordemServico = ordemServicoService.buscarOuFalhar(id);
		ModelAndView mv = cadastrar(ordemServico);
		mv.addObject(ordemServico);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		ordemServicoService.delete(id);
		return ResponseEntity.ok().build();
	}

}
