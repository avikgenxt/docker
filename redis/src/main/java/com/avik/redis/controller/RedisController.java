package com.avik.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avik.redis.resourse.MovieCache;
import com.avik.redis.vo.Movie;

@RestController
@RequestMapping("/movie-cache")
public class RedisController {
	public RedisController(MovieCache movieCache) {
		this.movieCache = movieCache;
	}

	private MovieCache movieCache;

	@RequestMapping(path ="/movieByName/{movieName}")
	@ResponseBody
	public Movie getMovieByName(@PathVariable("movieName") String movieName) {
		return movieCache.getMovieByName(movieName);
	}
	
	@PostMapping("/insertMovie")
	public void insertNewMovie(@RequestBody Movie movie) {
		movieCache.save(movie);
	}
	
	
	
}
