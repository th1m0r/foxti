package br.com.foxti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.foxti.model.Contratante;
import br.com.foxti.repository.ContratanteRepository;
import br.com.foxti.service.exception.EntidadeExistenteException;
import br.com.foxti.service.exception.EntidadeNaoEncontradaException;

@Service
public class ContratanteService {

	@Autowired
	private ContratanteRepository contratanteRepository;

	public Contratante save(Contratante contratante) {
		if (contratante.isNovo() && contratanteRepository.findByNome(contratante.getNome()) != null) {
			throw new EntidadeExistenteException("Contratante " + contratante.getNome() + " já está cadastrada.");
		}
		return contratanteRepository.save(contratante);
	}

	public void delete(Long contratanteId) {
		Contratante contratante = buscarOuFalhar(contratanteId);
		contratanteRepository.delete(contratante);
	}

	public Page<Contratante> findAll(Pageable pageable) {
		return contratanteRepository.findAll(pageable);
	}

	public Contratante buscarOuFalhar(Long contratanteId) {
		return contratanteRepository.findById(contratanteId).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Contratante " + contratanteId + " não encontrada."));
	}

}
