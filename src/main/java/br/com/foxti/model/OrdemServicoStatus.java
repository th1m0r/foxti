package br.com.foxti.model;

/**
 *
 * @author th1m0r
 */
public enum OrdemServicoStatus {
	
	ABERTA("Aberta"),
    ENCERRADA("Encerrada"),
    CANCELADA("Cancelada"),
    REAGENDADA_BASE("Reagendada base"),
    REAGENDADA_CAMPO("Reagendada campo");

    private final String descricao;

    OrdemServicoStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
