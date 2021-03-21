package br.com.cineclube.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import br.com.cineclube.model.Category;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;

//Service => anotacao que identifica que a classe deve ser detectada durante a fase de scanning do classpath
@Service
public class DataLoaderHelper {
	
	public static void loadData(FilmeRepository dao , PessoaRepository daop) {
		
		List<Filme> filmeList = new ArrayList<>();
		filmeList.add(new Filme("Avatar", 	7f, 	LocalDate.of(2009, 1, 28), Category.ACTION.name()));
		filmeList.add(new Filme("Matrix", 	9f, 	LocalDate.of(1999, 1, 1), Category.SCIFI.name()));
		filmeList.add(new Filme("Terminator",8f, 	LocalDate.of(1984, 1, 1), Category.SCIFI.name()));
		filmeList.add(new Filme("Rock", 	6f, 	LocalDate.of(1976, 1, 1), Category.ACTION.name()));
		filmeList.add(new Filme("Titanic", 	4f, 	LocalDate.of(1997, 1, 1), Category.DRAMA.name()));
		filmeList.add(new Filme("Alien", 	10f, 	LocalDate.of(1979, 1, 1), Category.SCIFI.name()));
		dao.saveAll(filmeList);
		
		List<Pessoa> pessoaList = new ArrayList<>();
		pessoaList.add(new Pessoa("Leonard", LocalDate.of(1944, 04, 8)));
		pessoaList.add(new Pessoa("Jake", 	 LocalDate.of(1999, 11, 28)));
		pessoaList.add(new Pessoa("Arnold",  LocalDate.of(1962, 11, 15)));
		pessoaList.add(new Pessoa("Kate",    LocalDate.of(2008, 05, 1)));
		pessoaList.add(new Pessoa("Anne",    LocalDate.of(1981, 06, 20)));
		daop.saveAll(pessoaList);
	}
	// @Bean => indica que o metodo loader gera um Bean gerenciado pelo Spring container.
	// CommandLineRunner => indica que deve ser executado pelo SpringApplication.
	@Bean
	public CommandLineRunner loader(FilmeRepository dao, PessoaRepository daop) {
		return (args) -> {
			DataLoaderHelper.loadData(dao, daop);
		};
	}

}
