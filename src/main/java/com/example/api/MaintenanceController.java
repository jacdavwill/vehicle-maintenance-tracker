package com.example.api;

import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.MaintEvent;
import com.example.model.MaintItem;
import com.example.service.ServiceFacade;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

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
	public ResponseEntity<List<MaintEvent>> getMaintenanceEventsForVehicle(@RequestHeader String authToken,
																		   @PathVariable("vehicleid") Integer vehicleID) {
		try {
			List<MaintEvent> maintEvents = serviceFacade.getAllEvents(authToken, vehicleID);
			return new ResponseEntity<>(maintEvents, HttpStatus.OK);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	// :vehicles == {vehicles} ???
	@GetMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<MaintEvent> getMaintenanceEventsForEventID(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
																 @PathVariable("eventid") Integer eventID) {
		try {
			MaintEvent maintEvent = serviceFacade.getEvent(authToken, vehicleID, eventID);
			return new ResponseEntity<>(maintEvent, HttpStatus.OK);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/api/maintenance/events/{vehicleid}")
	public ResponseEntity<Map<String,Integer>> addMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
																	@RequestBody MaintEvent newEvent) {
		try {
			System.out.println("NewEvent:" + newEvent);
			Integer maintEventId = serviceFacade.addEvent(authToken, vehicleID, newEvent);
			Map<String, Integer> result = new HashMap<>();
			result.put("maintEventId", maintEventId);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PutMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> editMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
													   @PathVariable("eventid") Integer eventID, @RequestBody MaintEvent newEvent) {
		try {
			serviceFacade.updateEvent(authToken, vehicleID, eventID, newEvent);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/maintenance/events/{vehicleid}/{eventid}")
	public ResponseEntity<String> deleteMaintenanceEvent(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
														 @PathVariable("eventid") Integer eventID) {
		try {
			serviceFacade.deleteEvent(authToken, vehicleID, eventID);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
