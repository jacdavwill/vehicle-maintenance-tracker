package com.example.api;

import com.example.exceptions.UnauthorizedException;
import com.example.model.Vehicle;
import com.example.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

	@Autowired
	ServiceFacade facade;

	@GetMapping("/api/vehicles")
	public List<Vehicle> getListOfVehicles(@RequestHeader String authToken) {
		try {
			return facade.getAllVehicles(authToken);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}

//		return new ResponseEntity<String>("This was a GET vehicles API call", HttpStatus.OK);
	}

	@GetMapping("/api/vehicles/{vehicleId}")
	public Vehicle getVehicleByID(@RequestHeader String authToken, @PathVariable("vehicleId") Integer vehicleId) {
		try {
			return facade.getVehicle(authToken, vehicleId);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}

//		return new ResponseEntity<String>("This was a GET vehicleID API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/vehicles")
	public Vehicle addVehicle(@RequestHeader String authToken, @RequestBody Vehicle vehicle) {
		try {
			Integer vehicleId = facade.addVehicle(authToken, vehicle);
			vehicle.setVehicleId(vehicleId);
			return vehicle;

		} catch (UnauthorizedException e) {
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PutMapping("/api/vehicles")
	public ResponseEntity<String> editVehicle(@RequestHeader String authToken, 
		@PathVariable("vehicleId") Integer vehicleId, @RequestBody Vehicle vehicle) {
			
		try {
			facade.updateVehicle(authToken, vehicleId, vehicle);
			return new ResponseEntity<>("This was a PUT editVehicle API call", HttpStatus.OK);

		} catch (UnauthorizedException e) {
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@DeleteMapping("/api/vehicles/{vehicleId}")
	public ResponseEntity<String> deleteVehicle(@RequestHeader String authToken, @PathVariable("vehicleId") Integer vehicleId) {
		try {
			facade.deleteVehicle(authToken, vehicleId);
			return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.OK);

		} catch (UnauthorizedException e) {
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}

	}
	
}
