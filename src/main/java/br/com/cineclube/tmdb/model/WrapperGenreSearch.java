package br.com.cineclube.tmdb.model;

import java.util.List;

public class WrapperGenreSearch {
	
	private List<GenreTMDB> results;

    public List<GenreTMDB> getResults() {
    	// descending orders
        return results;
    }
    public void setResults(List<GenreTMDB> results) {
        this.results = results;
    }

}
