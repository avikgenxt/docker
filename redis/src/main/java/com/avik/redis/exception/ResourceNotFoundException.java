package com.avik.redis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

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

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	private String overview;
    private String title;
    private String id;
    private String movieName;
    
    public ResourceNotFoundException( String id, String overview, String movieName, String title) {
        super(String.format("%s not found with %s : '%s'", id, overview, movieName,title));
        this.overview = overview;
        this.title = title;
        this.id = id;
        this.movieName=movieName;
    }

}
