package com.example.api;

import com.example.model.Vehicle;
import com.example.service.ServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class VehicleController {

	Vehicle vehicle1 = new Vehicle(1, "user1@example.com", "Mystery Machine", "https://rb.gy/sdbape",
					"Oct", 245320, "Dodge", "A100", 1964, "Green",
					"Automatic", "Gas");
	Vehicle vehicle2 = new Vehicle(2, "user1@example.com", "Silver Bullet", "https://rb.gy/jkweqr",
					"Jan", 102911, "Toyota", "Corolla", 2007, "Silver",
					"Automatic", "Gas");

	@GetMapping("/api/vehicles")
	public ResponseEntity<ArrayList<Vehicle>> getListOfVehicles(@RequestHeader String sessionKey) {
		ServiceFacade.getAllVehicles(sessionKey);

		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		vehicleList.add(vehicle1);
		vehicleList.add(vehicle2);

		return new ResponseEntity<ArrayList<Vehicle>>(vehicleList, HttpStatus.OK);
	}

	@GetMapping("/api/vehicles/{vehicleid}")
	public ResponseEntity<Vehicle> getVehicleByID(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID) {
		ServiceFacade.getVehicle(sessionKey, vehicleID);

		return new ResponseEntity<Vehicle>(vehicle1, HttpStatus.OK);
	}
	
	@PostMapping("/api/vehicles")
	public ResponseEntity<Vehicle> addVehicle(@RequestHeader String sessionKey, @RequestBody Vehicle vehicle) {
		ServiceFacade.addVehicle(sessionKey, vehicle);

		return new ResponseEntity<Vehicle>(vehicle2, HttpStatus.OK);
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
