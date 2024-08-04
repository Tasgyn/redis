package br.com.entrevista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.entrevista.model.Usuario;
import br.com.entrevista.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario saveUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Optional<Usuario> getUsuario(String id) {
		 return usuarioRepository.findById(id);
	}

	public List<Usuario> getAllUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public void updateUsuario(String id, Usuario dados) {
	
		Usuario usuario = usuarioRepository.findById(id) .orElseThrow(() -> new
		 RuntimeException("Usuário não encontrado"));
		 usuario.setNome(dados.getNome());
		 usuario.setEmail(dados.getEmail());
		 usuario.setTelefone(dados.getTelefone());
		 usuarioRepository.save(usuario);
		 
	}

	public void deleteUsuario(String id) {
		usuarioRepository.deleteById(id);
	}

}
