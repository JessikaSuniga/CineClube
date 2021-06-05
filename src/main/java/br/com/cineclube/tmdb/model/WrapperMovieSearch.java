package br.com.cineclube.tmdb.model;

import java.util.List;

public class WrapperMovieSearch {
	// representa o json retornado por essa url:

	private List<MovieTMDB> results;

	public List<MovieTMDB> getResults() {
		// ordem decrescente == ordena por filmes mais populares
		results.sort((f1, f2) -> Integer.compare(f2.getVote_count(), f1.getVote_count()));
		return results;
	}

	public void setResults(List<MovieTMDB> results) {
		this.results = results;
	}

}
