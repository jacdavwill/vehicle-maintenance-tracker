package com.example.api;

import com.example.model.Vehicle;
import com.example.service.ServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

	@GetMapping("/api/vehicles")
	public ResponseEntity<String> getListOfVehicles(@RequestHeader String sessionKey) {
		ServiceFacade.getAllVehicles(sessionKey);

		return new ResponseEntity<String>("This was a GET vehicles API call", HttpStatus.OK);
	}

	@GetMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<String> getVehicleByID(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID) {
		ServiceFacade.getVehicle(sessionKey, vehicleID);

		return new ResponseEntity<String>("This was a GET vehicleID API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/vehicles")
	public ResponseEntity<String> addVehicle(@RequestHeader String sessionKey, @RequestBody Vehicle vehicle) {
		ServiceFacade.addVehicle(sessionKey, vehicle);

		return new ResponseEntity<String>("This was a POST addVehicle API call", HttpStatus.OK);
	}
	
	@PutMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<String> editVehicle(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
											  @RequestBody Vehicle vehicle) {
		ServiceFacade.updateVehicle(sessionKey, vehicleID, vehicle);

		return new ResponseEntity<String>("This was a PUT editVehicle API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<String> deleteVehicle(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID) {
		ServiceFacade.deleteVehicle(sessionKey, vehicleID);

		return new ResponseEntity<String>("This was a POST register API call", HttpStatus.OK);
	}
	
}
