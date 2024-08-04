package br.com.entrevista.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entrevista.model.Usuario;
import br.com.entrevista.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping
	public Usuario saveUsuario(@RequestBody @Valid Usuario usuario) {
		return usuarioService.saveUsuario(usuario);
	}

	@GetMapping("/{id}")
	public Optional<Usuario> getUsuario(@PathVariable String id) {
		return usuarioService.getUsuario(id);
	}

	@GetMapping
	public List<Usuario> getAllUsuarios() {
		return (List<Usuario>) usuarioService.getAllUsuarios();
	}

	@PutMapping("/{id}")
	public void updateUsuario(@PathVariable String id, @RequestBody Usuario dados) {
		usuarioService.updateUsuario(id, dados);
	}

	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable String id) {
		usuarioService.deleteUsuario(id);
	}

}
