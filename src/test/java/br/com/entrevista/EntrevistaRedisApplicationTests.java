package br.com.entrevista;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.entrevista.controller.UsuarioController;
import br.com.entrevista.model.Usuario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
class EntrevistaRedisApplicationTests {

	@Autowired
	private UsuarioController controller;

	public void init() {

	}

	@Test
	void saveUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@gmail.com");
		usuario.setNome("Teste");
		usuario.setTelefone("62999999999");
		controller.saveUsuario(usuario);

		Usuario usuarioSave = controller.getUsuario(usuario.getId()).get();
		assertEquals(usuarioSave.getNome(), usuario.getNome());
		assertEquals(usuarioSave.getEmail(), usuario.getEmail());
		assertEquals(usuarioSave.getTelefone(), usuario.getTelefone());
	}
	
	@Test
	void saveUsuarioErrorEmailNull() {
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setTelefone("62999999999");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<Usuario>> constraintViolations =
                validator.validate(usuario);
        
        assertThat(constraintViolations.size()).isOne();

	}
	
	@Test
	void saveUsuarioErrorEmailInvalid() {
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setEmail("1111111");
		usuario.setTelefone("62999999999");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<Usuario>> constraintViolations =
                validator.validate(usuario);
        
        assertThat(constraintViolations.size()).isOne();

	}

	@Test
	void saveUsuarioErrorNomeNull() {
		Usuario usuario = new Usuario();
		usuario.setTelefone("62999999999");
		usuario.setEmail("teste@gmail.com");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<Usuario>> constraintViolations =
                validator.validate(usuario);
        
        assertThat(constraintViolations.size()).isOne();

	}
	
	@Test
	void saveUsuarioErrorTelefoneNull() {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@gmail.com");
		usuario.setNome("Teste");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<Usuario>> constraintViolations =
                validator.validate(usuario);
        
        assertThat(constraintViolations.size()).isOne();
	}
	
	@Test
	void savedUsuarioErrorAllNull() {
		Usuario usuario = new Usuario();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<Usuario>> constraintViolations =
                validator.validate(usuario);
        
        assertEquals(constraintViolations.size(), 3);
	}
	
	@Test
	void deleteUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@gmail.com");
		usuario.setNome("Teste");
		usuario.setTelefone("62999999999");
		controller.saveUsuario(usuario);
		
		controller.deleteUsuario(usuario.getId());
		
		assertTrue(controller.getUsuario(usuario.getId()).isEmpty());
	}

}
