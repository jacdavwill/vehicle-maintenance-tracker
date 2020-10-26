package com.example.api;

import com.example.model.MaintEvent;
import com.example.model.MaintItem;
import com.example.service.ServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

// TODO: Finish Setting Up API Endpoints
@RestController
public class MaintenanceController {
	Date currTime = Date.from(Instant.now());
	MaintItem item1 = new MaintItem(1, 1, 6, 5000, "Oil change", currTime, 95000);
	MaintItem item2 = new MaintItem(2, 1, 3, 2500, "Tires rotation", currTime, 95000);
	MaintEvent event1 = new MaintEvent(1, 1, currTime, 95000, "Home", "none","Oil change");
	MaintEvent event2 = new MaintEvent(2, 1, currTime, 95000, "Baker Street", "Les Schwab","Tires rotation");



	//Maintenance Items
	@GetMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<ArrayList<MaintItem>> getMaintenanceItemsForVehicle(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID) {

		ServiceFacade.getAllItems(sessionKey, vehicleID);

		ArrayList<MaintItem> maintItems = new ArrayList<MaintItem>();
		maintItems.add(item1);
		maintItems.add(item2);


		return new ResponseEntity<ArrayList<MaintItem>>(maintItems, HttpStatus.OK);
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<MaintItem> getMaintenanceItemsByItemID(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
															  @PathVariable("itemid") String itemID) {
		ServiceFacade.getItem(sessionKey, vehicleID, itemID);

		return new ResponseEntity<MaintItem>(item1, HttpStatus.OK);
	}
	
	@PostMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<MaintItem> addMaintenanceItem(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
													 @RequestBody MaintItem newItem) {
		ServiceFacade.addItem(sessionKey, vehicleID, newItem);

		return new ResponseEntity<MaintItem>(item2, HttpStatus.OK);
	}
	
	@PutMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> editMaintenanceItem(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
													  @PathVariable("itemid") String itemID, @RequestBody MaintItem newItem) {
		ServiceFacade.updateItem(sessionKey, vehicleID, itemID, newItem);

		return new ResponseEntity<String>("This was a PUT maintenance items API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> deleteMaintenanceItem(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
														@PathVariable("itemid") String itemID) {
		ServiceFacade.deleteItem(sessionKey, vehicleID, itemID);

		return new ResponseEntity<String>("This was a POST maintenance items API call", HttpStatus.OK);
	}

	//---------------------------------------------------------------------
	//Maintenance Events
	@GetMapping("/api/maintenance/events/{vehicleid}")
	public ResponseEntity<ArrayList<MaintEvent>> getMaintenanceEventsForVehicle(@RequestHeader String sessionKey,
																 @PathVariable("vehicleid") String vehicleID) {
		ServiceFacade.getAllEvents(sessionKey, vehicleID);

		ArrayList<MaintEvent> maintEvents = new ArrayList<MaintEvent>();
		maintEvents.add(event1);
		maintEvents.add(event2);

		return new ResponseEntity<ArrayList<MaintEvent>>(maintEvents, HttpStatus.OK);
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<MaintEvent> getMaintenanceEventsForEventID(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
																 @PathVariable("eventid") String eventID) {
		ServiceFacade.getEvent(sessionKey, vehicleID, eventID);

		return new ResponseEntity<MaintEvent>(event1, HttpStatus.OK);
	}
	
	@PostMapping("/maintenance/events/{vehicleid}")
	public ResponseEntity<MaintEvent> addMaintenanceEvent(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
													  @RequestBody MaintEvent newEvent) {
		ServiceFacade.addEvent(sessionKey, vehicleID, newEvent);

		return new ResponseEntity<MaintEvent>(event2, HttpStatus.OK);
	}
	
	@PutMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> editMaintenanceEvent(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
													   @PathVariable("eventid") String eventID, @RequestBody MaintEvent newEvent) {
		ServiceFacade.updateEvent(sessionKey, vehicleID, eventID, newEvent);

		return new ResponseEntity<String>("This was a PUT maintenance events API call", HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> deleteMaintenanceEvent(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID,
														 @PathVariable("eventid") String eventID) {
		ServiceFacade.deleteEvent(sessionKey, vehicleID, eventID);

		return new ResponseEntity<String>("This was a POST maintenance events API call", HttpStatus.OK);
	}
	
}
