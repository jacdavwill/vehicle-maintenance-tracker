package com.example.api;

import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Vehicle;
import com.example.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
public class VehicleController {
	@Autowired
	ServiceFacade serviceFacade;

	@GetMapping("/api/vehicles")
	public ResponseEntity<List<Vehicle>> getListOfVehicles(@RequestHeader String authToken) {
		try {
			List<Vehicle> vehicleList = serviceFacade.getAllVehicles(authToken);
			return new ResponseEntity<>(vehicleList, HttpStatus.OK);

		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<Vehicle> getVehicleByID(@RequestHeader String authToken,
			@PathVariable("vehicleid") Integer vehicleID) {
		try {
			Vehicle vehicle = serviceFacade.getVehicle(authToken, vehicleID);
			return new ResponseEntity<>(vehicle, HttpStatus.OK);
		
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping("/api/vehicles")
	public ResponseEntity<Map<String, Integer>> addVehicle(@RequestHeader String authToken, @RequestBody Vehicle vehicle) {
		try {
			int vehicleId = serviceFacade.addVehicle(authToken, vehicle);
			Map<String, Integer> result = new HashMap<>();
			result.put("vehicleId", vehicleId);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}
	
	@PutMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<String> editVehicle(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleId,
											  @RequestBody Vehicle vehicle) {
		try {
			serviceFacade.updateVehicle(authToken, vehicleId, vehicle);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<String> deleteVehicle(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID) {
		try {
			serviceFacade.deleteVehicle(authToken, vehicleID);
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
}
