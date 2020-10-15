package com.example.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: Finish Setting Up API Endpoints
@RestController
public class MaintenanceController {

	//Maintenance Items
	@GetMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<String> getMaintenanceItemsForVehicle(@PathVariable("vehicleid") String vehicleID) {
		return new ResponseEntity<String>("This was a GET maintenance items for vehicle API call", HttpStatus.OK);
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> getMaintenanceItemsByItemID(@PathVariable("vehicleid") String vehicleID, @PathVariable("itemid") String itemID) {
		return new ResponseEntity<String>("This was a GET maintenance items by id API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<String> addMaintenanceItem(@PathVariable("vehicleid") String vehicleID) {
		return new ResponseEntity<String>("This was a POST maintenance items API call", HttpStatus.OK);
	}
	
	@PutMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> editMaintenanceItem(@PathVariable("vehicleid") String vehicleID, @PathVariable("itemid") String itemID) {
		return new ResponseEntity<String>("This was a PUT maintenance items API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> deleteMaintenanceItem(@PathVariable("vehicleid") String vehicleID, @PathVariable("itemid") String itemID) {
		return new ResponseEntity<String>("This was a POST maintenance items API call", HttpStatus.OK);
	}
	
	//Maintenance Events
	@GetMapping("/api/maintenance/events/{vehicleid}")
	public ResponseEntity<String> getMaintenanceEventsForVehicle(@PathVariable("vehicleid") String vehicleID) {
		return new ResponseEntity<String>("This was a GET maintenance events for vehicle API call", HttpStatus.OK);
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> getMaintenanceEventsForEventID(@PathVariable("vehicleid") String vehicleID, @PathVariable("eventid") String eventID) {
		return new ResponseEntity<String>("This was a GET maintenance events by id API call", HttpStatus.OK);
	}
	
	@PostMapping("/maintenance/events/{vehicleid}")
	public ResponseEntity<String> addMaintenanceEvent(@PathVariable("vehicleid") String vehicleID) {
		return new ResponseEntity<String>("This was a POST maintenance events API call", HttpStatus.OK);
	}
	
	@PutMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> editMaintenanceEvent(@PathVariable("vehicleid") String vehicleID, @PathVariable("eventid") String eventID) {
		return new ResponseEntity<String>("This was a PUT maintenance events API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> deleteMaintenanceEvent(@PathVariable("vehicleid") String vehicleID, @PathVariable("eventid") String eventID) {
		return new ResponseEntity<String>("This was a POST maintenance events API call", HttpStatus.OK);
	}
	
}
