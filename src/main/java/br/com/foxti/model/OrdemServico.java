package br.com.foxti.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrdemServico {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long numero;
	
	@Enumerated(EnumType.STRING)
	private OrdemServicoStatus status;
	
	@Enumerated(EnumType.STRING)
	private TipoServico tipoServico;
	
	@ManyToOne
	@JoinColumn(name = "rota_id", referencedColumnName = "id")
	private Rota rota;
	
	@ManyToOne
	@JoinColumn(name = "contratante_id", referencedColumnName = "id")
	private Contratante contratante;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id", referencedColumnName = "id")
	private Tecnico tecnico;
	
	private LocalDate dataAbertura;
	private LocalDate dataAtendimento;
	private LocalDate dataRetorno;
	private LocalDate dataVencimento;
	private LocalDate dataReagendamento;
	
	private String usuarioCadastro;
	private String usuarioReagendamento;
	private String usuarioEncerramento;
	
	private String observacao;
	
	public boolean isNovo() {
		return id == null;
	}

}
