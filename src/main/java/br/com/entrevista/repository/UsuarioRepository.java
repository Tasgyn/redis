package br.com.entrevista.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.entrevista.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String>{

}
