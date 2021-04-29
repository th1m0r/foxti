package br.com.foxti.service.exception;

public class UsuarioExistenteException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public UsuarioExistenteException(String message) {
		super(message);
	}
}
