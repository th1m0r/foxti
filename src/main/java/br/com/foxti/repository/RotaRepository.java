package br.com.foxti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foxti.model.Rota;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Long> {

	public Rota findByNome(String nome);

}
