package br.com.cineclube.tmdb.model;

import java.util.List;

public class WrapperGenreSearch {

	private List<GenreTMDB> genres;

	public List<GenreTMDB> getGenres() {
		if (genres != null) {
			genres.sort((a, b) -> Long.compare(a.getId(), b.getId()));
			return genres;
		}
		return null;
	}

	public void setGenres(List<GenreTMDB> genres) {
		this.genres = genres;
	}
}
