package br.com.cineclube.model;

public class PersonDB {

	private Long id;
	private String name;
	private Integer popularity;
	private String profile_path;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPopularity() {
		return popularity;
	}
	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	public String getProfile_path() {
		return "https://image.tmdb.org/t/p/w200"+ profile_path;
	}
	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}
	
}

