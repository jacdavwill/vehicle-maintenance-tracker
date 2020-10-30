package com.example.api;

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
		return facade.getAllVehicles(authToken);

//		return new ResponseEntity<String>("This was a GET vehicles API call", HttpStatus.OK);
	}

	@GetMapping("/api/vehicles/{vehicleId}")
	public Vehicle getVehicleByID(@RequestHeader String authToken, @PathVariable("vehicleId") Integer vehicleId) {
		return facade.getVehicle(authToken, vehicleId);

//		return new ResponseEntity<String>("This was a GET vehicleID API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/vehicles")
	public Vehicle addVehicle(@RequestHeader String authToken, @RequestBody Vehicle vehicle) {
		Integer vehicleId = facade.addVehicle(authToken, vehicle);
		vehicle.setVehicleId(vehicleId);

		return vehicle;
	}
	
	@PutMapping("/api/vehicles")
	public ResponseEntity<String> editVehicle(@RequestHeader String authToken, @RequestBody Vehicle vehicle) {
		facade.updateVehicle(authToken, vehicle);

		return new ResponseEntity<String>("This was a PUT editVehicle API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/vehicles/{vehicleId}")
	public ResponseEntity<String> deleteVehicle(@RequestHeader String authToken, @PathVariable("vehicleId") Integer vehicleId) {
		facade.deleteVehicle(authToken, vehicleId);

		return new ResponseEntity<String>("Vehicle deleted successfully", HttpStatus.OK);
	}
	
}
