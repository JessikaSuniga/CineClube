package br.com.cineclube.model;

import java.util.List;

public class WrapperMovieSearch {
	
	private List<FilmeDB> results;

	public List<FilmeDB> getResults() {
		// ordem decrescente == ordena por filmes mais populares
    	results.sort( (f1,f2) -> Integer.compare(f2.getVote_count(), f1.getVote_count()) );
		return results;
	}

	public void setResults(List<FilmeDB> results) {
		this.results = results;
	}

}
