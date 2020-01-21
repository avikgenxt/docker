package com.avik.movieinfoservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	@Qualifier("withEureka")
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
	@Primary
	@Qualifier("withoutEureka")
	@Bean
	public RestTemplate restTemplateExternal(){
		return new RestTemplate();
	}
	
}

