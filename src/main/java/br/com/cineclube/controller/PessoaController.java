package br.com.cineclube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;

@Controller
@RequestMapping("/pessoas") // http://localhost:8080/pessoas
public class PessoaController {

	@Autowired
	PessoaRepository dao;
	
	@RequestMapping("/new")
	public String newForm(Model model) {
		Pessoa pessoa = new Pessoa();
		model.addAttribute("pessoa", pessoa);
		return "pessoa/new.html";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Pessoa> pessoaList = dao.findAll();
		model.addAttribute("pessoaList", pessoaList);
		return "pessoa/list.html";
	}
	
	@PostMapping("/save")
	public String save(Pessoa pessoa, Model model) {
		dao.save(pessoa);
		return "redirect:/pessoas/list";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
	    dao.deleteById(id);
	    return "redirect:/pessoas/list";
	}
	
}
