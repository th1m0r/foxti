package br.com.foxti.service.exception;

public class EntidadeExistenteException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeExistenteException(String message) {
		super(message);
	}
}
