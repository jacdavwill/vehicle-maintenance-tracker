package com.example.api;

import com.example.service.ServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

  @GetMapping("/api/notifications/{vehicleid}")
  public ResponseEntity<String> getNotifications(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID) {
    ServiceFacade.getAllNotifications(sessionKey, vehicleID);

    return new ResponseEntity<String>("This was a GET notification API call", HttpStatus.OK);
  }

  @GetMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<String> getNotificationByID(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID, @PathVariable("notificationid") String notificationID) {
    ServiceFacade.getNotification(sessionKey, vehicleID, notificationID);

    return new ResponseEntity<String>("This was a GET notification by ID API call", HttpStatus.OK);
  }

  @DeleteMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<String> deleteNotification(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID, @PathVariable("notificationid") String notificationID) {
    ServiceFacade.deleteNotification(sessionKey, vehicleID, notificationID);

    return new ResponseEntity<String>("This was a DELETE notification by ID API call", HttpStatus.OK);
  }

}
