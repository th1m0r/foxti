package br.com.foxti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String login;

	@NotEmpty
	private String senha;

	@Transient
	private String confirmaSenha;

	public boolean isNovo() {
		return id == null;
	}
	
	@PreUpdate
	@PrePersist
	public void toUpperCase() {
		setNome(nome.toUpperCase());		
	}
}
