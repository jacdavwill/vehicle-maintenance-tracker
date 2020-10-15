package com.example.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

  @GetMapping("/api/notifications/{vehicleid}")
  public ResponseEntity<String> getNotifications(@PathVariable("vehicleid") String vehicleID) {
    return new ResponseEntity<String>("This was a GET notification API call", HttpStatus.OK);
  }

  @GetMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<String> getNotificationByID(@PathVariable("vehicleid") String vehicleID, @PathVariable("notificationid") String notificationID) {
    return new ResponseEntity<String>("This was a GET notification by ID API call", HttpStatus.OK);
  }

  @DeleteMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<String> deleteNotification(@PathVariable("vehicleid") String vehicleID, @PathVariable("notificationid") String notificationID) {
    return new ResponseEntity<String>("This was a DELETE notification by ID API call", HttpStatus.OK);
  }

}
