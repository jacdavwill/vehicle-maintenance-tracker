package com.example.vehiclemaintenancetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com.*")
public class VehicleMaintenanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleMaintenanceTrackerApplication.class, args);
	}

	@Bean
  	public WebMvcConfigurer corsConfigurer() {
    	return new WebMvcConfigurer() {
      	@Override
      	public void addCorsMappings(CorsRegistry registry) {
        	registry.addMapping("/**")
							.allowedOrigins("*")
							.allowedHeaders("*")
							.allowedMethods("*");
      	}
	  };
	}
}