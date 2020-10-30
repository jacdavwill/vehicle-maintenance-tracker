package com.example.api;

import com.example.model.MaintEvent;
import com.example.model.MaintItem;
import com.example.service.ServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: Finish Setting Up API Endpoints
@RestController
public class MaintenanceController {

	//Maintenance Items
	@GetMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<String> getMaintenanceItemsForVehicle(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID) {
		ServiceFacade.getAllItems(authToken, vehicleID);

		return new ResponseEntity<String>("This was a GET maintenance items for vehicle API call", HttpStatus.OK);
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> getMaintenanceItemsByItemID(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID,
															  @PathVariable("itemid") String itemID) {
		ServiceFacade.getItem(authToken, vehicleID, itemID);

		return new ResponseEntity<String>("This was a GET maintenance items by id API call", HttpStatus.OK);
	}
	
	@PostMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<String> addMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID,
													 @RequestBody MaintItem newItem) {
		ServiceFacade.addItem(authToken, vehicleID, newItem);

		return new ResponseEntity<String>("This was a POST maintenance items API call", HttpStatus.OK);
	}
	
	@PutMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> editMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID,
													  @PathVariable("itemid") String itemID, @RequestBody MaintItem newItem) {
		ServiceFacade.updateItem(authToken, vehicleID, itemID, newItem);

		return new ResponseEntity<String>("This was a PUT maintenance items API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> deleteMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID,
														@PathVariable("itemid") String itemID) {
		ServiceFacade.deleteItem(authToken, vehicleID, itemID);

		return new ResponseEntity<String>("This was a POST maintenance items API call", HttpStatus.OK);
	}

	//---------------------------------------------------------------------
	//Maintenance Events
	@GetMapping("/api/maintenance/events/{vehicleid}")
	public ResponseEntity<String> getMaintenanceEventsForVehicle(@RequestHeader String authToken,
																 @PathVariable("vehicleid") String vehicleID) {
		ServiceFacade.getAllEvents(authToken, vehicleID);

		return new ResponseEntity<String>("This was a GET maintenance events for vehicle API call", HttpStatus.OK);
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> getMaintenanceEventsForEventID(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID,
																 @PathVariable("eventid") String eventID) {
		ServiceFacade.getEvent(authToken, vehicleID, eventID);

		return new ResponseEntity<String>("This was a GET maintenance events by id API call", HttpStatus.OK);
	}
	
	@PostMapping("/maintenance/events/{vehicleid}")
	public ResponseEntity<String> addMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID,
													  @RequestBody MaintEvent newEvent) {
		ServiceFacade.addEvent(authToken, vehicleID, newEvent);

		return new ResponseEntity<String>("This was a POST maintenance events API call", HttpStatus.OK);
	}
	
	@PutMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> editMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID,
													   @PathVariable("eventid") String eventID, @RequestBody MaintEvent newEvent) {
		ServiceFacade.updateEvent(authToken, vehicleID, eventID, newEvent);

		return new ResponseEntity<String>("This was a PUT maintenance events API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> deleteMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID,
														 @PathVariable("eventid") String eventID) {
		ServiceFacade.deleteEvent(authToken, vehicleID, eventID);

		return new ResponseEntity<String>("This was a POST maintenance events API call", HttpStatus.OK);
	}
	
}
