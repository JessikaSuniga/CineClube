package br.com.cineclube.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.FilmeDB;
import br.com.cineclube.model.WrapperMovieSearch;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	FilmeRepository dao;
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Filme> filmeList = dao.findAll();
		model.addAttribute("filmeList", filmeList);
		return "filme/list.html";
	}
	
	@RequestMapping("/new")
	public String newForm(Model model) {
		Filme filme = new Filme();
		model.addAttribute("filme", filme);
		return "filme/manterFilme.html";
	}
	
	@PostMapping("/save")
	public String save(@Valid Filme filme, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "filme/manterFilme.html";
		}
		dao.save(filme);
		return "redirect:/filmes/list";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Filme filme = dao.getOne(id);
		model.addAttribute("filme", filme);
		
		String filmeUrl = 
				"https://api.themoviedb.org/3/search/movie?api_key=" +  apiKey + 
				"&language=pt-BR&query=" + filme.getNome() + "&year=" + filme.getLancamento().getYear();
				
		WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);    	
		
		if(searchResult.getResults().size() > 0) {
			FilmeDB moviedb = searchResult.getResults().get(0);    	
			filme.setMoviedb(moviedb);			
		}
		
		return "filme/manterFilme.html";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.deleteById(id);
		return "redirect:/filmes/list";
	}
	
	@PostMapping(value = "/categorias")
	public String filtra(String categoria, Model model) {
		List<Filme> filmes = dao.findByCategoria(categoria);
		model.addAttribute("filmeList",filmes);
		model.addAttribute("categoria",categoria);
		return "filme/list.html";
	}
}
