package com.example.api;

import com.example.model.Notification;
import com.example.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

  @Autowired
  ServiceFacade serviceFacade;

  @GetMapping("/api/notifications/{vehicleid}")
  public ResponseEntity<String> getNotifications(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID) {
    ServiceFacade.getAllNotifications(authToken, vehicleID);

    return new ResponseEntity<String>("This was a GET notification API call", HttpStatus.OK);
  }

  @GetMapping("/api/notifications/{vehicleid}/{notificationid}")
  public Notification getNotificationByID(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID, @PathVariable("notificationid") String notificationID) {
    return new Notification(1, 1, false, "This is a fake Notification");
    //    return serviceFacade.getNotification(authToken, vehicleID, notificationID);
//    return new ResponseEntity<String>("This was a GET notification by ID API call", HttpStatus.OK);
  }

  @DeleteMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<String> deleteNotification(@RequestHeader String authToken, @PathVariable("vehicleid") String vehicleID, @PathVariable("notificationid") String notificationID) {
    ServiceFacade.deleteNotification(authToken, vehicleID, notificationID);

    return new ResponseEntity<String>("This was a DELETE notification by ID API call", HttpStatus.OK);
  }

}
