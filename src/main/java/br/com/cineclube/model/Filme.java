package br.com.cineclube.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long filmeId;
	private String nome; //
	private Integer ano; // 2016
	private String categoria; // drama, action, ...
	private Float nota; // 0..10

	public Filme() {
	}

	public Filme(String nome, Float nota, Integer ano, String categoria) {
		this.nome = nome;
		this.ano = ano;
		this.nota = nota;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public Long getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Long filmeId) {
		this.filmeId = filmeId;
	}

}