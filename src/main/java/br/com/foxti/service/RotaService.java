package br.com.foxti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.foxti.model.Rota;
import br.com.foxti.repository.RotaRepository;
import br.com.foxti.service.exception.EntidadeExistenteException;
import br.com.foxti.service.exception.EntidadeNaoEncontradaException;

@Service
public class RotaService {
	
	@Autowired
	private RotaRepository rotaRepository;
	
	public Rota save(Rota rota) {
		if (rota.isNovo() && rotaRepository.findByNome(rota.getNome()) != null) {
			throw new EntidadeExistenteException("Rota " + rota.getNome() + " já está cadastrada.");
		}
		return rotaRepository.save(rota);
	}

	public void delete(Long rotaId) {
		Rota rota = buscarOuFalhar(rotaId);
		rotaRepository.delete(rota);
	}

	public Page<Rota> findAll(Pageable pageable) {
		return rotaRepository.findAll(pageable);
	}

	public Rota buscarOuFalhar(Long rotaId) {
		return rotaRepository.findById(rotaId).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Rota " + rotaId + " não encontrada."));
	}

}
