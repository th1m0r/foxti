package br.com.foxti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.foxti.model.Usuario;
import br.com.foxti.repository.UsuarioRepository;
import br.com.foxti.service.exception.NegocioException;
import br.com.foxti.service.exception.UsuarioExistenteException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario save(Usuario usuario) {
		if (existeUsuario(usuario.getLogin()) && usuario.isNovo()) {
			throw new UsuarioExistenteException("Login (" + usuario.getLogin() + ") já cadadstro");
		}
		return usuarioRepository.save(usuario);
	}
	
	public void delete(Long usuarioId) {
		if(!usuarioRepository.existsById(usuarioId)) {
			throw new NegocioException("Usuário não encontrado. Código: " + usuarioId);
		}
		usuarioRepository.deleteById(usuarioId);		
	}
	
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	private boolean existeUsuario(String login) {
		return usuarioRepository.findByLogin(login) != null;
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new NegocioException("Usuário não encontrado."));
	}

}
