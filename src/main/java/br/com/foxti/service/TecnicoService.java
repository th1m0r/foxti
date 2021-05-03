package br.com.foxti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.foxti.model.Tecnico;
import br.com.foxti.repository.TecnicoRepository;
import br.com.foxti.service.exception.EntidadeNaoEncontradaException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico save(Tecnico tecnico) {
		return tecnicoRepository.save(tecnico);
	}

	public void delete(Long tecnicoId) {
		Tecnico tecnico = buscarOuFalhar(tecnicoId);
		tecnicoRepository.delete(tecnico);
	}
	
	public Page<Tecnico> findAll(Pageable pageable) {
		return tecnicoRepository.findAll(pageable);
	}

	public Tecnico buscarOuFalhar(Long tecnicoId) {
		return tecnicoRepository.findById(tecnicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Técnico " + tecnicoId + " não encontrado."));
	}

}
