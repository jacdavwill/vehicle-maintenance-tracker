package com.example.api;

import com.example.model.Notification;
import com.example.service.ServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class NotificationController {
  Notification notification1 = new Notification(1, 1, false, "Finished");
  Notification notification2 = new Notification(2, 2, true, "Not Done");


  @GetMapping("/api/notifications/{vehicleid}")
  public ResponseEntity<ArrayList<Notification>> getNotifications(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID) {
    ServiceFacade.getAllNotifications(sessionKey, vehicleID);

    ArrayList<Notification> notifications = new ArrayList<>();
    notifications.add(notification1);
    notifications.add(notification2);

    return new ResponseEntity<ArrayList<Notification>>(notifications, HttpStatus.OK);
  }

  @GetMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<Notification> getNotificationByID(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID, @PathVariable("notificationid") String notificationID) {
    ServiceFacade.getNotification(sessionKey, vehicleID, notificationID);

    return new ResponseEntity<Notification>(notification1, HttpStatus.OK);
  }

  @DeleteMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<String> deleteNotification(@RequestHeader String sessionKey, @PathVariable("vehicleid") String vehicleID, @PathVariable("notificationid") String notificationID) {
    ServiceFacade.deleteNotification(sessionKey, vehicleID, notificationID);

    return new ResponseEntity<String>("This was a DELETE notification by ID API call", HttpStatus.OK);
  }

}
