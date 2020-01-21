package com.avik.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.avik.movieinfoservice.models.Movie;
import com.avik.movieinfoservice.models.MovieResult;

@RestController
@RequestMapping("/movies/")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    @Qualifier("withEureka")
    private RestTemplate restTemplate;
    
    @Autowired
    @Qualifier("withoutEureka")
    private RestTemplate restTemplateExternal;
    
    @RequestMapping(path ="movieName/{movieName}")
    public Movie getMovieInfo(@PathVariable("movieName") String movieName) {
    	
    	Movie movie;
    	movie=restTemplate.getForObject("http://movie-info-cache-service/movie-cache/movieByName/"+movieName, Movie.class);
    	
    	if(movie==null) {
    		MovieResult movieResult = restTemplateExternal.getForObject("https://api.themoviedb.org/3/search/movie?api_key=" +  apiKey+"&language=en-US&query="+movieName, MovieResult.class);
    		restTemplate.postForEntity("http://movie-info-cache-service/movie-cache/insertMovie", 
    				new Movie(movieResult.getResults()[0].getOverview(), movieResult.getResults()[0].getTitle(),movieResult.getResults()[0].getId(),movieResult.getResults()[0].getOriginal_title() ), null);
    		
			
			 restTemplate.postForEntity("http://movie-avikdb-service/movie-db/insertMovie", new Movie(movieResult.getResults()[0].getOverview(),
			 movieResult.getResults()[0].getTitle(),movieResult.getResults()[0].getId(),
			 movieResult.getResults()[0].getOriginal_title() ), null);
			
            return new Movie(movieResult.getResults()[0].getOverview(), movieResult.getResults()[0].getTitle(),movieResult.getResults()[0].getId(),movieResult.getResults()[0].getOriginal_title() );
    	}else {
    		return movie;
    	}
    			

    }

}
