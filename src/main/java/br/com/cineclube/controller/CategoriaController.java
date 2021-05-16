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

import br.com.cineclube.dao.CategoriaRepository;
import br.com.cineclube.model.Categoria;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaRepository dao;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Categoria> categorias = dao.findAll();
		
		model.addAttribute("categoriaList",categorias);
		
		return "categoria/list.html";
	}
	
	@GetMapping("/new")
	public String newForm(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		return "categoria/manterCategoria.html";
	}
	
	@PostMapping("/save")
	public String save(@Valid Categoria categoria, BindingResult result, Model model) {
		if(result.hasErrors())
			return "categoria/manterCategoria.html";
		dao.save(categoria);
		return "redirect:/categorias/list";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Categoria categoria = dao.findById(id).get();
		model.addAttribute("categoria", categoria);
		return "categoria/manterCategoria.html";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.removerCategoria(id);
		return "redirect:/categorias/list";
	}

}
