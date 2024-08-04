package br.com.entrevista.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Usuario")
public class Usuario implements Serializable {
	@Id
	private String id;
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;
	
	@NotEmpty(message = "E-mail não pode ser vazio")
	@Email
	private String email;
	
	@NotEmpty(message = "Telefone não pode ser vazio")
	private String telefone;

}
