package br.com.cineclube.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pessoaId;
	
	@NotBlank(message="Nome campo obrigatorio")
	@Size(min=3, max=50, message="Minimo de {min} caracteres em maximo de {max}")
	private String nome;
	
	
	@NotNull
	@Past // ==> data de nascimento da Pessoa deve ser validada com Past (valida datas anteriores ao data agora)
	// no caso de lancamento do filme essa regra nem sempre eh interessante pois um filme pode
	// estar programado para lancamento no futuro (mas serve aqui como exemplo de como validar)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dataNasc;
	
	private int Idade;
	
	public Pessoa() {
	}
	
	public Pessoa(String nome, LocalDate dataNasc) {
		this.nome = nome;
		this.dataNasc = dataNasc;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	
	public Integer getIdade() {
		// retornar a diferenca em anos entre a data de lançamento e data atual:
		
		return (int)ChronoUnit.YEARS.between(this.dataNasc, LocalDate.now());
	}

	
}
