package com.example.vehiclemaintenancetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.*")
public class VehicleMaintenanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleMaintenanceTrackerApplication.class, args);
	}

}
