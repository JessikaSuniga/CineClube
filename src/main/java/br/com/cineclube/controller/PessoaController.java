package br.com.cineclube.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Category;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;

@Controller
@RequestMapping("/pessoas") // http://localhost:8080/pessoas
public class PessoaController {

	@Autowired
	PessoaRepository daop;
	
	@RequestMapping("/new")
	public String newForm(Model model) {
		Pessoa pessoa = new Pessoa();
		model.addAttribute("pessoa", pessoa);
		return "pessoa/new.html";
	}
	
	@GetMapping(value = "/delete/{id}") 
	public String delete(@PathVariable Long id) {
		daop.deleteById(id);
		return "redirect:/pessoas/list";
	}
	@GetMapping(value = "/edit/{id}") 
	public String edit(@PathVariable Long id, Model model) {
		Pessoa pessoa = daop.getOne(id);
		model.addAttribute("pessoa", pessoa);
		return "pessoa/new.html";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Pessoa> pessoaList = daop.findAll();
		model.addAttribute("pessoaList", pessoaList);
		return "pessoa/list.html";
	}
	
	@PostMapping("/save") // usar @Valid para validar o objeto
	public String save(@Valid Pessoa pessoa, BindingResult result, Model model) {
		if(result.hasErrors()) { // se possui algum erro retorna ao formulario
			return "pessoa/new.html";
		}
		// se tudo ok, entao salva no db:
		daop.save(pessoa);
		return "redirect:/pessoas/list";
	}
	
	
}
