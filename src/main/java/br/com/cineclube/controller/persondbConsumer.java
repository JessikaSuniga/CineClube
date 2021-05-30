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
import br.com.cineclube.service.PersondbService;

@RestController
@RequestMapping("/api/v1")
public class persondbConsumer {
	
	@Autowired
	PersondbService apiService;
    
    @GetMapping("/search-person")
    public WrapperPersonSearch searchPerson(@RequestParam String name){

    	
    	return apiService.searchPerson(name);
    }
    
    @GetMapping("/search-person1")
    public PersonDB searchOneMovie(@RequestParam String name){
    	
    	return apiService.searchOneMovie(name);
    }
    
    
}
