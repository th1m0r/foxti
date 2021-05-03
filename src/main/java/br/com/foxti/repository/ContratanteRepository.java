package br.com.foxti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foxti.model.Contratante;

@Repository
public interface ContratanteRepository extends JpaRepository<Contratante, Long> {

	public Contratante findByNome(String nome);

}
