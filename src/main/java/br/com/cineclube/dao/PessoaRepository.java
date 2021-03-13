package br.com.cineclube.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cineclube.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long>{

	List<Pessoa> findAll();
}
