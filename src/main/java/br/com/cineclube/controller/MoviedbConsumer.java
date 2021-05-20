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
import br.com.cineclube.model.WrapperMovieSearch;

@RestController
@RequestMapping("/api/v1")
public class MoviedbConsumer {
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
    
    /**
     * TESTE pela nossa API:
     * http :8080/api/v1/filmedb/550
     * 
     * Chamar diretamenta a API do themoviedb
     * http https://api.themoviedb.org/3/movie/550?api_key=5c88b6f565d40408d394a6b68a40f51c 
     */
    @RequestMapping("/filmedb/{id}")
    public FilmeDB getFilmeById(@PathVariable Long id) {
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/movie/" + id + "?api_key=" +  apiKey;
    	
        FilmeDB filme = apiRequest.getForObject(filmeUrl, FilmeDB.class);
        
        return filme; // serializado em JSON
    }
    
    /**
     * TESTE pela nossa API:
     * http :8080/api/v1/search title==avatar year==2009
     * 
     * Chamada direta:
     * http https://api.themoviedb.org/3/search/movie?api_key=d1da20fbfa65312b857fb7b517bf855c query==avatar year==2009
     * 
     * Retorna uma lista de filmes
     */
    @GetMapping("/search")
    public WrapperMovieSearch searchMovie(@RequestParam String title, @RequestParam Integer year){
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/search/movie?api_key=" +  apiKey + "&query=" + title + "&year=" + year;
    	
    	// aonde ocorre a des-serializacao (converter o retorno em json para objeto java)
    	WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);
    	
    	return searchResult;
    }
    
    /*
     * retorna somente o primeiro filme da lista
     */
    @GetMapping("/search1")
    public FilmeDB searchOneMovie(@RequestParam String title, @RequestParam Integer year){
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/search/movie?api_key=" +  apiKey + "&language=pt-BR&query=" + title + "&year=" + year;
    	
    	
    	WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);
    	FilmeDB filme = searchResult.getResults().get(0);
    	
    	return filme;
    }
    
    
}
