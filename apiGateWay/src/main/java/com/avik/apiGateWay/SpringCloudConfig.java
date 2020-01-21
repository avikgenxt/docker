package com.avik.apiGateWay;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
	
	 @Bean
	    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
	        return builder.routes()
	                .route(r -> r.path("/movieInfo/**")
	                		.filters(f -> f.stripPrefix(1))
	                        .uri("lb://MOVIE-INFO-SERVICE")
	                        .id("movieInfo"))
	                .route(r -> r.path("/movieCache/**")
	                		.filters(f -> f.stripPrefix(1))
	                        .uri("lb://MOVIE-INFO-CACHE-SERVICE")
	                        .id("movieCache"))
	                .route(r -> r.path("/movieDB/**")
	                		.filters(f -> f.stripPrefix(1))
	                        .uri("lb://MOVIE-AVIKDB-SERVICE")
	                        .id("movieDB"))
	                .build();
	    }

}
