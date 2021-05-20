package br.com.cineclube.controller;

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

import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Pessoa;
import br.com.cineclube.model.PersonDB;
import br.com.cineclube.model.WrapperPersonSearch;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaRepository dao;
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
    
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("pessoaList",dao.findAll());
		return "pessoa/list.html";
	}
	@GetMapping("/new")
	public String newForm(Model model) {
		Pessoa pessoa = new Pessoa();
		model.addAttribute("pessoa", pessoa);
		return "pessoa/manterPessoa.html";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Pessoa pessoa = dao.findById(id).get();
		model.addAttribute("pessoa", pessoa);
		
		String pessoaUrl = 
        		"https://api.themoviedb.org/3/search/person?api_key=" +  apiKey + "&query=" + pessoa.getNome();
    	
    	WrapperPersonSearch searchResult = apiRequest.getForObject(pessoaUrl, WrapperPersonSearch.class);
    	
    	if(searchResult.getResults().size() > 0) {
    		PersonDB personDB = searchResult.getResults().get(0);
    		pessoa.setPersondb(personDB);    		
    	}
		
		return "pessoa/manterPessoa.html";	}
	
	@PostMapping("/save")
	public String save(@Valid Pessoa pessoa, BindingResult result, Model model) {
		if(result.hasErrors())
			return "pessoa/manterPessoa.html";
		dao.save(pessoa);
		return "redirect:/pessoas/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.removerPessoa(id);
		return "redirect:/pessoas/list";
	}
	
	
}
