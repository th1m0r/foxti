package br.com.foxti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.foxti.model.OrdemServico;
import br.com.foxti.repository.OrdemServicoRepository;
import br.com.foxti.service.exception.EntidadeNaoEncontradaException;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	public OrdemServico save(OrdemServico ordemServico) {
		return ordemServicoRepository.save(ordemServico);
	}

	public void delete(Long ordemServicoId) {
		OrdemServico ordemServico = buscarOuFalhar(ordemServicoId);
		ordemServicoRepository.delete(ordemServico);
	}

	public Page<OrdemServico> findAll(Pageable pageable) {
		return ordemServicoRepository.findAll(pageable);
	}

	public OrdemServico buscarOuFalhar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Ordem de serviço " + ordemServicoId + " não encontrada."));
	}

}
