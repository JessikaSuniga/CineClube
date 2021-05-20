package br.com.cineclube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.model.FilmeDB;
import br.com.cineclube.model.PersonDB;
import br.com.cineclube.model.WrapperMovieSearch;
import br.com.cineclube.model.WrapperPersonSearch;

@RestController
@RequestMapping("/api/v1")
public class persondbConsumer {
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
    
    @GetMapping("/search-person")
    public WrapperPersonSearch searchPerson(@RequestParam String name){
    	
    	String pessoaUrl = 
        		"https://api.themoviedb.org/3/search/person?api_key=" +  apiKey + "&query=" + name ;
    	
    	WrapperPersonSearch searchResult = apiRequest.getForObject(pessoaUrl, WrapperPersonSearch.class);
    	
    	return searchResult;
    }
    
    @GetMapping("/search-person1")
    public PersonDB searchOneMovie(@RequestParam String name){
    	
    	String pessoaUrl = 
        		"https://api.themoviedb.org/3/search/person?api_key=" +  apiKey + "&query=" + name;
    	
    	
    	WrapperPersonSearch searchResult = apiRequest.getForObject(pessoaUrl, WrapperPersonSearch.class);
    	
    	PersonDB pessoa = searchResult.getResults().get(0);
    	
    	return pessoa;
    }
    
    
}
