package com.avik.movieinfoservice.models;


public class Movie {
    private String overview;
    private String title;
    private String id;
    private String original_title;
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Movie(String overview, String title, String id,String original_title) {
		super();
		this.overview = overview;
		this.title = title;
		this.id = id;
		this.original_title=original_title;
	}

	
	public Movie() {
		
	}

	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
    
}
