package com.example.api;

import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Notification;
import com.example.service.ServiceFacade;
import java.util.List;
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

  @GetMapping("/api/notifications")
  public ResponseEntity<List<Notification>> getNotifications(@RequestHeader String authToken) {
    try {
      List<Notification> notifications = serviceFacade.getAllNotifications(authToken);
      return new ResponseEntity<>(notifications, HttpStatus.OK);
    } catch(UnauthorizedException e){
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping("/api/notifications/{notificationid}")
  public ResponseEntity<Notification> getNotificationByID(@RequestHeader String authToken, @PathVariable("notificationid") Integer notificationID) {
    try {
      Notification notification = serviceFacade.getNotification(authToken, notificationID);
      return new ResponseEntity<>(notification, HttpStatus.OK);
    } catch(UnauthorizedException e){
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    } catch(NotFoundException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/api/notifications/{notificationid}")
  public ResponseEntity<String> deleteNotification(@RequestHeader String authToken, @PathVariable("notificationid") Integer notificationID) {
    try {
      serviceFacade.deleteNotification(authToken, notificationID);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch(UnauthorizedException e){
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    } catch(NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
