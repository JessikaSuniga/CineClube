package br.com.cineclube.tmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.tmdb.model.MovieTMDB;
import br.com.cineclube.tmdb.model.WrapperMovieSearch;

@Service // acessada via autowired (uso interno das nossas classes)
public class MoviedbService {

	@Value("${api.moviedb.key}")
	private String apiKey;

	@Autowired
	private RestTemplate apiRequest;

	public WrapperMovieSearch searchMovie(String title, Integer year) {

		String filmeUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&language=pt-BR&query="
				+ title + "&year=" + year;

		WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);

		return searchResult;
	}

	public MovieTMDB getMovieById(Long id) {

		String filmeUrl = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey + "&language=pt-BR";

		MovieTMDB filme = apiRequest.getForObject(filmeUrl, MovieTMDB.class);

		return filme; // serializado em JSON

	}

	public MovieTMDB searchOneMovie(String title, Integer year) {

		String filmeUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&language=pt-BR&query="
				+ title + "&year=" + year;

		WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);

		MovieTMDB filme = new MovieTMDB();

		// se existe filme
		if (searchResult.getResults() != null && searchResult.getResults().size() > 0) {
			filme = searchResult.getResults().get(0);
			return filme;
		}

		// se não existe verifica se existe em séries
		return searchOneSerie(title, year);
	}

	public MovieTMDB searchOneSerie(String title, Integer year) {

		String filmeUrl = "https://api.themoviedb.org/3/search/tv?api_key=" + apiKey + "&language=pt-BR&query=" + title
				+ "&year=" + year;

		WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);

		MovieTMDB filme = searchResult.getResults() != null && searchResult.getResults().size() > 0
				? searchResult.getResults().get(0)
				: null;

		return filme;
	}

}
