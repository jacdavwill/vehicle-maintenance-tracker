package com.example.api;

import com.example.model.MaintEvent;
import com.example.model.MaintItem;
import com.example.service.ServiceFacade;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

// TODO: Finish Setting Up API Endpoints
@RestController
public class MaintenanceController {
	@Autowired
	ServiceFacade serviceFacade;

	LocalDate currDate = LocalDate.now();
	MaintItem item1 = new MaintItem(1, 1, 6, 5000, "Oil change", currDate, 95000);
	MaintItem item2 = new MaintItem(2, 1, 3, 2500, "Tires rotation", currDate, 95000);
	MaintEvent event1 = new MaintEvent(1, 1, currDate, 95000, "Home", "none","Oil change");
	MaintEvent event2 = new MaintEvent(2, 1, currDate, 95000, "Baker Street", "Les Schwab","Tires rotation");



	//Maintenance Items
	@GetMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<ArrayList<MaintItem>> getMaintenanceItemsForVehicle(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID) {

		serviceFacade.getAllItems(authToken, vehicleID);

		ArrayList<MaintItem> maintItems = new ArrayList<MaintItem>();
		maintItems.add(item1);
		maintItems.add(item2);


		return new ResponseEntity<ArrayList<MaintItem>>(maintItems, HttpStatus.OK);
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<MaintItem> getMaintenanceItemsByItemID(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
															  @PathVariable("itemid") Integer itemID) {
		serviceFacade.getItem(authToken, vehicleID, itemID);

		return new ResponseEntity<MaintItem>(item1, HttpStatus.OK);
	}
	
	@PostMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<MaintItem> addMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
													 @RequestBody MaintItem newItem) {
		serviceFacade.addItem(authToken, vehicleID, newItem);

		return new ResponseEntity<MaintItem>(item2, HttpStatus.OK);
	}
	
	@PutMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> editMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
													  @PathVariable("itemid") Integer itemID, @RequestBody MaintItem newItem) {
		serviceFacade.updateItem(authToken, vehicleID, itemID, newItem);

		return new ResponseEntity<String>("This was a PUT maintenance items API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> deleteMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
														@PathVariable("itemid") Integer itemID) {
		serviceFacade.deleteItem(authToken, vehicleID, itemID);

		return new ResponseEntity<String>("This was a DELETE maintenance items API call", HttpStatus.OK);
	}

	//---------------------------------------------------------------------
	//Maintenance Events
	@GetMapping("/api/maintenance/events/{vehicleid}")
	public ResponseEntity<ArrayList<MaintEvent>> getMaintenanceEventsForVehicle(@RequestHeader String authToken,
																 @PathVariable("vehicleid") Integer vehicleID) {
		serviceFacade.getAllEvents(authToken, vehicleID);

		ArrayList<MaintEvent> maintEvents = new ArrayList<MaintEvent>();
		maintEvents.add(event1);
		maintEvents.add(event2);

		return new ResponseEntity<ArrayList<MaintEvent>>(maintEvents, HttpStatus.OK);
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<MaintEvent> getMaintenanceEventsForEventID(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
																 @PathVariable("eventid") Integer eventID) {
		serviceFacade.getEvent(authToken, vehicleID, eventID);

		return new ResponseEntity<MaintEvent>(event1, HttpStatus.OK);
	}
	
	@PostMapping("/maintenance/events/{vehicleid}")
	public ResponseEntity<MaintEvent> addMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
													  @RequestBody MaintEvent newEvent) {
		serviceFacade.addEvent(authToken, vehicleID, newEvent);

		return new ResponseEntity<MaintEvent>(event2, HttpStatus.OK);
	}
	
	@PutMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> editMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
													   @PathVariable("eventid") Integer eventID, @RequestBody MaintEvent newEvent) {
		serviceFacade.updateEvent(authToken, vehicleID, eventID, newEvent);

		return new ResponseEntity<String>("This was a PUT maintenance events API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> deleteMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
														 @PathVariable("eventid") Integer eventID) {
		serviceFacade.deleteEvent(authToken, vehicleID, eventID);

		return new ResponseEntity<String>("This was a DELETE maintenance events API call", HttpStatus.OK);
	}
	
}
