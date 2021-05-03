package br.com.foxti.model;

/**
 *
 * @author th1m0r
 */
public enum TipoServico {

	MANUTENCAO("Manutenção"), 
	INSTALACAO("Instalação"), 
	ENTREGA("Entrega"), 
	TROCA("Troca");

	private final String descricao;

	TipoServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
