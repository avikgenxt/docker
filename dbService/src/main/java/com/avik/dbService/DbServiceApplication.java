package com.avik.dbService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
/* @EntityScan( basePackages = {"com.avik"} ) */
public class DbServiceApplication extends SpringBootServletInitializer {
 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DbServiceApplication.class);
    }
 

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}

}
