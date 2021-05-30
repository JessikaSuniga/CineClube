package br.com.cineclube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.model.FilmeDB;
import br.com.cineclube.model.WrapperMovieSearch;

@Service
public class MoviedbService {

	@Value("${api.moviedb.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate apiRequest;
	
public WrapperMovieSearch searchMovie(String title, Integer year){
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/search/movie?api_key=" 
        			+  apiKey + "&query=" + title + "&year=" + year;
    	
    	// aonde ocorre a des-serializacao (converter o retorno em json para objeto java)
    	WrapperMovieSearch searchResult = 
    			apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);
    	
    	return searchResult;
    }	

public FilmeDB getMovieById(Long id) {
	
	String filmeUrl = 
    		"https://api.themoviedb.org/3/movie/" + id + "?api_key=" +  apiKey;
	
    FilmeDB filme = apiRequest.getForObject(filmeUrl, FilmeDB.class);
    
    return filme; // serializado em JSON
    
}
public FilmeDB searchOneMovie(String title, Integer year){
	
	String filmeUrl = 
    		"https://api.themoviedb.org/3/search/movie?api_key=" +  apiKey + "&query=" + title + "&year=" + year;
	
	
	WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);
	
	FilmeDB filme = new FilmeDB();
	
	if (searchResult.getResults()!=null && searchResult.getResults().size() > 0) {
		filme = searchResult.getResults().get(0);
	}
	
	return filme;
}
}
