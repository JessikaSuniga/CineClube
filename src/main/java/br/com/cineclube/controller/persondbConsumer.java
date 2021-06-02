package br.com.cineclube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.tmdb.model.PersonTMDB;
import br.com.cineclube.tmdb.model.WrapperPersonSearch;
import br.com.cineclube.tmdb.service.PersondbService;

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
    public PersonTMDB searchOneMovie(@RequestParam String name){
    	
    	return apiService.searchOneMovie(name);
    }

}
