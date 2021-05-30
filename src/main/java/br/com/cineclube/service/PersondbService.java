package br.com.cineclube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.model.PersonDB;
import br.com.cineclube.model.WrapperPersonSearch;

@Service
public class PersondbService {
	
	@Value("${api.persondb.key")
	private String apiKey;
	
	@Autowired
	private RestTemplate apiRequest;
	
	public WrapperPersonSearch searchPerson(String name){
    	
    	String pessoaUrl = 
        		"https://api.themoviedb.org/3/search/person?api_key=" +  apiKey + "&query=" + name ;
    	
    	WrapperPersonSearch searchResult = apiRequest.getForObject(pessoaUrl, WrapperPersonSearch.class);
    	
    	return searchResult;
    }
	
	@GetMapping("/search-person1")
    public PersonDB searchOneMovie(String name){
    	
    	String pessoaUrl = 
        		"https://api.themoviedb.org/3/search/person?api_key=" +  apiKey + "&query=" + name;
    	
    	
    	WrapperPersonSearch searchResult = apiRequest.getForObject(pessoaUrl, WrapperPersonSearch.class);
    	
    	PersonDB pessoa = searchResult.getResults().get(0);
    	
    	return pessoa;
    }

}
