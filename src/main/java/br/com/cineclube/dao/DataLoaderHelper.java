package br.com.cineclube.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import br.com.cineclube.model.Categoria;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;

@Service
public class DataLoaderHelper {
	
	public static void loadData(FilmeRepository daof , PessoaRepository daop, CategoriaRepository daoc) {

		
		
		//FILMES E SERIES
		List<Filme> filmeList = new ArrayList<>();
		filmeList.add(new Filme("Fragmentado", 					9f, 	LocalDate.of(2017, 3, 17)));
		filmeList.add(new Filme("Matrix", 						9f, 	LocalDate.of(1999, 5, 21)));
		filmeList.add(new Filme("Gente Grande",					8f, 	LocalDate.of(2010, 9, 24)));
		filmeList.add(new Filme("Resgate", 						10f, 	LocalDate.of(2020, 4, 24)));
		filmeList.add(new Filme("Lupin", 						9f, 	LocalDate.of(2021, 1, 8)));
		filmeList.add(new Filme("The Sinner", 					8f, 	LocalDate.of(2017, 1, 1)));
		filmeList.add(new Filme("Chernobyl", 					8.4f, 	LocalDate.of(2012, 7, 13)));//7
		filmeList.add(new Filme("Por tras de seus olhos",		8.2f, 	LocalDate.of(2021, 2, 17)));
		filmeList.add(new Filme("Vozes e vultos", 				7.6f, 	LocalDate.of(2021, 4, 21)));
		filmeList.add(new Filme("O Inocente", 					9f, 	LocalDate.of(2020, 4, 21)));
		filmeList.add(new Filme("Stranger Things", 				6.5f, 	LocalDate.of(2016, 7, 15)));
		filmeList.add(new Filme("Hacher", 						10f, 	LocalDate.of(2015, 3, 5)));		
		filmeList.add(new Filme("Unidade 42", 					9.40f, 	LocalDate.of(2017, 10, 21)));
		filmeList.add(new Filme("Um contratempo",				8.11f, 	LocalDate.of(2017, 1, 6)));
		filmeList.add(new Filme("Terror na estrada", 			10f, 	LocalDate.of(2015, 10, 6)));
		filmeList.add(new Filme("Pressagio", 					9.3f, 	LocalDate.of(2009, 3, 20)));
		filmeList.add(new Filme("Labirinto verde", 				6.5f, 	LocalDate.of(2017, 4, 10)));
		filmeList.add(new Filme("Deadwind", 					10f, 	LocalDate.of(2018, 1, 15)));
		daof.saveAll(filmeList);
		
		//ELENCO
		List<Pessoa> pessoaList = new ArrayList<>();
		pessoaList.add(new Pessoa("James McAvoy", 		LocalDate.of(1979, 4, 21))); 
		pessoaList.add(new Pessoa("Adam Sandler", 		LocalDate.of(1966, 9, 9)));
		pessoaList.add(new Pessoa("Chris Hemsworth",  	LocalDate.of(1983, 8, 11)));
		pessoaList.add(new Pessoa("Bill Pullman", 	 	LocalDate.of(1953, 12, 17)));
		pessoaList.add(new Pessoa("Simona Brown",	 	LocalDate.of(1994, 4, 6)));
		pessoaList.add(new Pessoa("Jessica Biel",	 	LocalDate.of(1982, 3, 3)));
		pessoaList.add(new Pessoa("Amanda Seyfried",	LocalDate.of(1985, 12, 3)));
		pessoaList.add(new Pessoa("Winona Ryder", 		LocalDate.of(1971, 10, 29)));
		pessoaList.add(new Pessoa("Patrick Ridremont", 	LocalDate.of(1967, 8, 9)));
		pessoaList.add(new Pessoa("Mario Casas",  		LocalDate.of(1986, 6, 12)));
		pessoaList.add(new Pessoa("Julianne Hough", 	LocalDate.of(1988, 7, 20)));
		pessoaList.add(new Pessoa("Nicolas Cage",	 	LocalDate.of(1964, 1, 7)));
		pessoaList.add(new Pessoa("Suliane Brahim",	 	LocalDate.of(1978, 4, 1)));
		pessoaList.add(new Pessoa("Pihla Viitala",	 	LocalDate.of(1982, 9, 30)));
		daop.saveAll(pessoaList);
		
		
		//CETEGORIA
		List<Categoria> categoriaList = new ArrayList<>();
		categoriaList.add(new Categoria("Acao"));
		categoriaList.add(new Categoria("Aventura"));
		categoriaList.add(new Categoria("Comedia"));
		categoriaList.add(new Categoria("Documentario"));
		categoriaList.add(new Categoria("Drama"));
		categoriaList.add(new Categoria("Faroeste"));
		categoriaList.add(new Categoria("Fantasia"));
		categoriaList.add(new Categoria("Ficcao cientifica"));
		categoriaList.add(new Categoria("Musical"));
		categoriaList.add(new Categoria("Romance"));
		categoriaList.add(new Categoria("Suspense"));
		categoriaList.add(new Categoria("Terror"));
		categoriaList.add(new Categoria("Misterio"));
		daoc.saveAll(categoriaList);

		
		//LIGAÇÃO DE ELENCO E CATEGORIA AOS FILMES
		
		Set<Pessoa> elencoFragmentado = new HashSet<>();
		elencoFragmentado.add(daop.findById(1L).get());	
		Set<Categoria> categoriaFragmentado = new HashSet<>();
		categoriaFragmentado.add(daoc.findById(19L).get());
		categoriaFragmentado.add(daoc.findById(20L).get());
		Filme fragmentado = daof.findById(1L).get();
		fragmentado.setPessoas(elencoFragmentado);
		fragmentado.setCategoria(categoriaFragmentado);
		daof.save(fragmentado);
		
		Set<Pessoa> elencoMatrix = new HashSet<>();
		elencoMatrix.add(daop.findById(7L).get());	
		Set<Categoria> categoriaMatrix = new HashSet<>();
		categoriaMatrix.add(daoc.findById(19L).get());
		Filme matrix = daof.findById(2L).get();
		matrix.setPessoas(elencoMatrix);
		matrix.setCategoria(categoriaMatrix);
		daof.save(matrix);
		
		Set<Pessoa> elencoGG = new HashSet<>();
		elencoGG.add(daop.findById(2L).get());	
		Set<Categoria> categoriaGG = new HashSet<>();
		categoriaGG.add(daoc.findById(21L).get());
		Filme gg = daof.findById(3L).get();
		gg.setPessoas(elencoGG);
		gg.setCategoria(categoriaGG);
		daof.save(gg);
		
		Set<Pessoa> elencoResgate = new HashSet<>();
		elencoResgate.add(daop.findById(3L).get());	
		Set<Categoria> categoriaResgate = new HashSet<>();
		categoriaResgate.add(daoc.findById(19L).get());
		categoriaResgate.add(daoc.findById(20L).get());
		categoriaResgate.add(daoc.findById(23L).get());
		Filme resgate = daof.findById(4L).get();
		resgate.setPessoas(elencoResgate);
		resgate.setCategoria(categoriaResgate);
		daof.save(resgate);
		
		Set<Pessoa> elencoLupin = new HashSet<>();
		elencoLupin.add(daop.findById(2L).get());	
		Set<Categoria> categoriaLupin = new HashSet<>();
		categoriaLupin.add(daoc.findById(19L).get());
		categoriaLupin.add(daoc.findById(26L).get());
		Filme lupin = daof.findById(5L).get();
		lupin.setPessoas(elencoLupin);
		lupin.setCategoria(categoriaLupin);
		daof.save(lupin);
		
		Set<Pessoa> elencoSinner = new HashSet<>();
		elencoSinner.add(daop.findById(6L).get());	
		Set<Categoria> categoriaSinner = new HashSet<>();
		categoriaSinner.add(daoc.findById(19L).get());
		categoriaSinner.add(daoc.findById(23L).get());
		Filme sinner = daof.findById(6L).get();
		sinner.setPessoas(elencoSinner);
		sinner.setCategoria(categoriaSinner);
		daof.save(sinner);
		
		Set<Pessoa> elencoByl = new HashSet<>();
		elencoByl.add(daop.findById(13L).get());	
		Set<Categoria> categoriaByl = new HashSet<>();
		categoriaByl.add(daoc.findById(22L).get());
		Filme byl = daof.findById(7L).get();
		byl.setPessoas(elencoByl);
		byl.setCategoria(categoriaByl);
		daof.save(byl);
		
		Set<Pessoa> elencoOlhos = new HashSet<>();
		elencoOlhos.add(daop.findById(5L).get());	
		Set<Categoria> categoriaOlhos = new HashSet<>();
		categoriaOlhos.add(daoc.findById(30L).get());
		categoriaOlhos.add(daoc.findById(29L).get());
		Filme olhos = daof.findById(8L).get();
		olhos.setPessoas(elencoOlhos);
		olhos.setCategoria(categoriaOlhos);
		daof.save(olhos);
		
		Set<Pessoa> elencoVozes = new HashSet<>();
		elencoVozes.add(daop.findById(7L).get());	
		Set<Categoria> categoriaVozes = new HashSet<>();
		categoriaVozes.add(daoc.findById(29L).get());
		Filme vozes = daof.findById(9L).get();
		vozes.setPessoas(elencoVozes);
		vozes.setCategoria(categoriaVozes);
		daof.save(vozes);
		
		Set<Pessoa> elencoInocente = new HashSet<>();
		elencoInocente.add(daop.findById(10L).get());	
		Set<Categoria> categoriaInocente = new HashSet<>();
		categoriaInocente.add(daoc.findById(31L).get());
		Filme inocente = daof.findById(10L).get();
		inocente.setPessoas(elencoInocente);
		inocente.setCategoria(categoriaInocente);
		daof.save(inocente);
		
		Set<Pessoa> elencoStranger = new HashSet<>();
		elencoStranger.add(daop.findById(8L).get());	
		Set<Categoria> categoriaStranger = new HashSet<>();
		categoriaStranger.add(daoc.findById(26L).get());
		Filme stranger = daof.findById(11L).get();
		stranger.setPessoas(elencoStranger);
		stranger.setCategoria(categoriaStranger);
		daof.save(stranger);
		
		Set<Pessoa> elencoHacker = new HashSet<>();
		elencoHacker.add(daop.findById(3L).get());	
		Set<Categoria> categoriaHacker = new HashSet<>();
		categoriaHacker.add(daoc.findById(19L).get());
		categoriaHacker.add(daoc.findById(29L).get());
		categoriaHacker.add(daoc.findById(23L).get());
		Filme hacker = daof.findById(12L).get();
		hacker.setPessoas(elencoHacker);
		hacker.setCategoria(categoriaHacker);
		daof.save(hacker);
		
		Set<Pessoa> elenco42 = new HashSet<>();
		elenco42.add(daop.findById(9L).get());	
		Set<Categoria> categoria42 = new HashSet<>();
		categoria42.add(daoc.findById(29L).get());
		categoria42.add(daoc.findById(30L).get());
		Filme u42 = daof.findById(13L).get();
		u42.setPessoas(elenco42);
		u42.setCategoria(categoria42);
		daof.save(u42);
		
		Set<Pessoa> elencoContra = new HashSet<>();
		elencoContra.add(daop.findById(10L).get());	
		Set<Categoria> categoriaContra = new HashSet<>();
		categoriaContra.add(daoc.findById(19L).get());
		categoriaContra.add(daoc.findById(23L).get());
		categoriaContra.add(daoc.findById(29L).get());
		Filme contra = daof.findById(14L).get();
		contra.setPessoas(elencoContra);
		contra.setCategoria(categoriaContra);
		daof.save(contra);
		
		Set<Pessoa> elencoEstrada = new HashSet<>();
		elencoEstrada.add(daop.findById(11L).get());	
		Set<Categoria> categoriaEstrada = new HashSet<>();
		categoriaEstrada.add(daoc.findById(31L).get());
		Filme estrada = daof.findById(15L).get();
		estrada.setPessoas(elencoEstrada);
		estrada.setCategoria(categoriaEstrada);
		daof.save(estrada);
		
		Set<Pessoa> elencoPressagio = new HashSet<>();
		elencoPressagio.add(daop.findById(12L).get());	
		Set<Categoria> categoriaPressagio = new HashSet<>();
		categoriaPressagio.add(daoc.findById(30L).get());
		categoriaPressagio.add(daoc.findById(20L).get());
		Filme pressagio = daof.findById(16L).get();
		pressagio.setPessoas(elencoPressagio);
		pressagio.setCategoria(categoriaPressagio);
		daof.save(pressagio);
		
		Set<Pessoa> elencoVerde = new HashSet<>();
		elencoVerde.add(daop.findById(13L).get());	
		Set<Categoria> categoriaVerde = new HashSet<>();
		categoriaVerde.add(daoc.findById(29L).get());
		Filme verde = daof.findById(17L).get();
		verde.setPessoas(elencoVerde);
		verde.setCategoria(categoriaVozes);
		daof.save(verde);
		
		Set<Pessoa> elencoDead = new HashSet<>();
		elencoDead.add(daop.findById(14L).get());	
		Set<Categoria> categoriaDead = new HashSet<>();
		categoriaDead.add(daoc.findById(31L).get());
		Filme dead = daof.findById(18L).get();
		dead.setPessoas(elencoDead);
		dead.setCategoria(categoriaDead);
		daof.save(dead);
		
	}
	@Bean
	public CommandLineRunner loader(FilmeRepository daof, PessoaRepository daop, CategoriaRepository daoc) {
		return (args) -> {
			DataLoaderHelper.loadData(daof, daop, daoc);
		};
	}
}
