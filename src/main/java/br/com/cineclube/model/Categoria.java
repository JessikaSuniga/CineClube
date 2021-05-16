package br.com.cineclube.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	@JsonProperty("text")
	@Size(min=2, max=20, message="Mínimo de {min} caracteres e máximo de {max}")
	private String nome;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categoria")
	private Set<Filme> filmes;
	
	
	public Categoria() {}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(Set<Filme> filmes) {
		this.filmes = filmes;
	}
	
}
