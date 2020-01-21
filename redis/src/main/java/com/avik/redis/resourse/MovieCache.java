package com.avik.redis.resourse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.avik.redis.vo.Movie;

@Repository
public class MovieCache{
	@Autowired
	private RedisTemplate<String, Movie> redisTemplate;
	
	private HashOperations<String, String, Movie> hashOperations;

	public MovieCache(RedisTemplate<String, Movie> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
		this.hashOperations=redisTemplate.opsForHash();
	}
	
	public void save(Movie movie) {
		hashOperations.put("MOVIE", movie.getId(),movie);
		hashOperations.put("MOVIE", movie.getOriginal_title(),movie);
	}
	
	public Map<String, Movie> findAll(){
		return hashOperations.entries("MOVIE");
	}
	
	public void update(Movie movie) {
		save(movie);
	}
	
	public void delete(String id) {
		hashOperations.delete("MOVIE", id);
	}
	
	public Movie getMovie(String id) {
		return (Movie)hashOperations.get("MOVIE",id);
	}

	public Movie getMovieByName(String original_title) {
		return (Movie)hashOperations.get("MOVIE",original_title);
	}
	
	
}
