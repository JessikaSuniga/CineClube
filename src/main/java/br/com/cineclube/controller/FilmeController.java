package br.com.cineclube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.model.Filme;

@Controller
@RequestMapping("/filmes") // http://localhost:8080/filmes
public class FilmeController {
	// associa a controller a um endpoint
	
	@Autowired // delega ao spring a criacao do repository
	FilmeRepository dao;
	
	// http://localhost:8080/filmes/new
	@RequestMapping("/new")
	public String newForm(Model model) {
		Filme filme = new Filme();
		model.addAttribute("filme", filme);
		return "filme/new.html";
	}
	@RequestMapping("/list")
	public String list(Model model) {
		List<Filme> filmeList = dao.findAll();
		model.addAttribute("filmeList", filmeList);
		return "filme/list.html";
	}
	@PostMapping("/save")
	public String save(Filme filme, Model model) {
		dao.save(filme);
		return "redirect:/filmes/list"; // chama action /filmes/list
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
	    dao.deleteById(id);
	    return "redirect:/filmes/list";
	}
}
