package br.com.cineclube.model;


import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import br.com.cineclube.tmdb.model.MovieTMDB;


@Entity
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Transient
	private MovieTMDB moviedb; // mapea o json que vem da moviedb api
	
	@NotBlank(message="Nome campo obrigatorio")
	@Size(min=1, max=50, message="Minimo de {min} caracteres e maximo de {max}")
	@Column(nullable = false)
	private String nome;
	
	@NotNull
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate lancamento;
	
	private Float nota;
	
	@ManyToMany
	@JsonSerialize(using = PessoaListSerializer.class)
	@JoinTable(name="filme_pessoa",
	joinColumns = {@JoinColumn(name="filme_id")},
	inverseJoinColumns = {@JoinColumn(name="pessoa_id")})
	private Set<Pessoa> pessoas;

	@ManyToMany
	@JoinTable(name = "filme_categoria",
	joinColumns = {@JoinColumn(name="filme_id")},
	inverseJoinColumns = {@JoinColumn(name="categoria_id")})
	@Size(min=1, max=3, message="Minimo de {min} categoria e maximo de {max} categorias")
	private Set<Categoria> categoria;
	
	public Filme() {}

	public Filme(String nome, Float nota, LocalDate lancamento) {
		this.nome = nome;
		this.nota = nota;
		this.lancamento = lancamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Set<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(Set<Categoria> categoria) {
		this.categoria = categoria;
	}

	public MovieTMDB getMoviedb() {
		return moviedb;
	}

	public void setMoviedb(MovieTMDB moviedb) {
		this.moviedb = moviedb;
	}
	
}
