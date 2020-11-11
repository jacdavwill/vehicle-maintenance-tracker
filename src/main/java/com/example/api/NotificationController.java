package com.example.api;

import com.example.model.Notification;
import com.example.service.ServiceFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Log4j2
@RestController
public class NotificationController {
  @Autowired
  ServiceFacade serviceFacade;

  Notification notification1 = new Notification(1, 1, false, "Finished");
  Notification notification2 = new Notification(2, 2, true, "Not Done");


  @GetMapping("/api/notifications/{vehicleid}")
  public ResponseEntity<ArrayList<Notification>> getNotifications(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID) {
    serviceFacade.getAllNotifications(authToken, vehicleID);

    ArrayList<Notification> notifications = new ArrayList<>();
    notifications.add(notification1);
    notifications.add(notification2);

    return new ResponseEntity<>(notifications, HttpStatus.OK);
  }

  @GetMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<Notification> getNotificationByID(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID, @PathVariable("notificationid") Integer notificationID) {
    try {
      serviceFacade.getNotification(authToken, vehicleID, notificationID);
    } catch(EmptyResultDataAccessException e){
      log.info("Notification {} does not exist!", notificationID);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch(Exception e){
      log.error("Something unexpected occurred! Throwing 500...", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(notification1, HttpStatus.OK);
  }

  @DeleteMapping("/api/notifications/{vehicleid}/{notificationid}")
  public ResponseEntity<String> deleteNotification(@RequestHeader String authToken, @PathVariable("vehicleid") Integer vehicleID, @PathVariable("notificationid") Integer notificationID) {
    serviceFacade.deleteNotification(authToken, vehicleID, notificationID);

    return new ResponseEntity<String>("This was a DELETE notification by ID API call", HttpStatus.OK);
  }

}
