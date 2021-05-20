package br.com.cineclube.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import br.com.cineclube.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
	List<Filme> findByCategoria(String categoria);
	List<Filme> findByNome(String nome);
	List<Filme> findByNomeAndCategoria(String nome, String categoria);
	List<Filme> findByNomeOrCategoria(String nome, String categoria);
	List<Filme> findByOrderByCategoria();
	List<Filme> findByOrderByCategoriaDesc();
	List<Filme> findByNotaGreaterThan(Float nota);
	List<Filme> findByNotaGreaterThanEqual(Float nota);
	List<Filme> findByNotaLessThanEqual(Float nota);
	List<Filme> findTop3ByNotaGreaterThanEqualOrderByNotaDesc(Float nota);
	List<Filme> findByNotaBetween(Float min, Float max);
	boolean existsFilmeByCategoria(String categoria);

	
	@Query("select f from Filme f join f.categoria c where c.id = ?1")
	List<Filme> selecionatFilmePorCategoria(Long categoriaId);
	
	@Query("select f from Filme f join f.pessoas p join f.categoria c where p.nome = ?1 and c.id = ?2")
	List<Filme> buscaPorPessoaAndCategoria(String nome, Long categoria);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Filme f where f.id = :id")
	void removerFilme(Long id);
}
