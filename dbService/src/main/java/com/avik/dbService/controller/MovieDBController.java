package com.avik.dbService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avik.dbService.Do.Movie;
import com.avik.dbService.Repository.MovieDBRepository;

@RestController
@RequestMapping("/movie-db/")
@EnableJpaRepositories("com.avik.dbService.Repository")
@EntityScan( basePackages = {"com.avik"} )
public class MovieDBController {
	
	@Autowired
	MovieDBRepository movieDBRepository;

	@GetMapping("/movieByName")
	@ResponseBody
	public Movie getMovieByName(@PathVariable("moviename") String movieName) {
		return movieDBRepository.findByMovieIds(movieName);
	}
	
	@GetMapping("/allMovie")
	@ResponseBody
	public List<Movie> allMovie() {
		return (List<Movie>) movieDBRepository.findAll();
	}
	
	@PostMapping("/insertMovie")
	public void insertNewMovie(@RequestBody Movie movie) {
		movieDBRepository.save(movie);
	}
	
	@GetMapping("/insertMovieTest")
	public void insertMovieTest() {
		Movie movie = new Movie();
		movie.setId("500");
		movie.setOriginal_title("500");
		movie.setOverview("500");
		movie.setTitle("500");
		movieDBRepository.save(movie);
	}
}
