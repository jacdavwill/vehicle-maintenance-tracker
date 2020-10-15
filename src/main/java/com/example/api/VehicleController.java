package com.example.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

	@GetMapping("/api/vehicles")
	public ResponseEntity<String> getListOfVehicles() {
		return new ResponseEntity<String>("This was a GET vehicles API call", HttpStatus.OK);
	}

	@GetMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<String> getVehicleByID(@PathVariable("vehicleid") String vehicleID) {
		return new ResponseEntity<String>("This was a GET vehicleID API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/vehicles")
	public ResponseEntity<String> addVehicle() {
		return new ResponseEntity<String>("This was a POST addVehicle API call", HttpStatus.OK);
	}
	
	@PutMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<String> editVehicle(@PathVariable("vehicleid") String vehicleID) {
		return new ResponseEntity<String>("This was a PUT editVehicle API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<String> updateUser(@PathVariable("vehicleid") String vehicleID) {
		return new ResponseEntity<String>("This was a POST register API call", HttpStatus.OK);
	}
	
}
