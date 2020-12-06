package com.example.api;

import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.MaintEvent;
import com.example.model.MaintItem;
import com.example.service.ServiceFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// TODO: Finish Setting Up API Endpoints
@RestController
public class MaintenanceController {
	@Autowired
	ServiceFacade serviceFacade;

	//Maintenance Items
	@GetMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<List<MaintItem>> getMaintenanceItemsForVehicle(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID) {

		try {
			List<MaintItem> maintItems =  serviceFacade.getAllItems(authToken, vehicleID);
			return new ResponseEntity<>(maintItems, HttpStatus.OK);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<MaintItem> getMaintenanceItemByItemID(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
															  @PathVariable("itemid") Integer itemID) {
		try {
			MaintItem maintItem = serviceFacade.getItem(authToken, vehicleID, itemID);
			return new ResponseEntity<>(maintItem, HttpStatus.OK);
		} catch(UnauthorizedException e){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch(NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/api/maintenance/items/{vehicleid}")
	public ResponseEntity<MaintItem> addMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
													 @RequestBody MaintItem newItem) {
		try {
			Integer itemId = serviceFacade.addItem(authToken, vehicleID, newItem);
			MaintItem maintItem = serviceFacade.getItem(authToken, vehicleID, itemId);
			return new ResponseEntity<>(maintItem, HttpStatus.OK);
		} catch(UnauthorizedException e){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch(NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> editMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
													  @PathVariable("itemid") Integer itemID, @RequestBody MaintItem newItem) {
		try {
			serviceFacade.updateItem(authToken, vehicleID, itemID, newItem);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(UnauthorizedException e){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch(NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/maintenance/items/{vehicleid}/{itemid}")
	public ResponseEntity<String> deleteMaintenanceItem(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID,
														@PathVariable("itemid") Integer itemID) {
		try {
			serviceFacade.deleteItem(authToken, vehicleID, itemID);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(UnauthorizedException e){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch(NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
		} catch(NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
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
			Integer maintEventId = serviceFacade.addEvent(authToken, vehicleID, newEvent);
			Map<String, Integer> result = new HashMap<>();
			result.put("maintEventId", maintEventId);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch(NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
