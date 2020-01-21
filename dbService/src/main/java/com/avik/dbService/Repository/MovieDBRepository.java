package com.avik.dbService.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avik.dbService.Do.Movie;

@Repository
public interface MovieDBRepository extends CrudRepository<Movie, String> {
	
	  @Query(value = "SELECT * FROM Movie WHERE Movie = ?1", nativeQuery = true)
	  Movie findByMovieIds(String original_title);
	
}
