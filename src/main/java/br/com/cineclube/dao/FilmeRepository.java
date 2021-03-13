package br.com.cineclube.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cineclube.model.Filme;

// T - type
// ID - tipo do filmeId
public interface FilmeRepository extends CrudRepository<Filme, Long>{
	
	List<Filme> findAll();

}
