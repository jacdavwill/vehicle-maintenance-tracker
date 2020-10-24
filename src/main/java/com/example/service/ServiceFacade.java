package com.example.service;

import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: Implement facade to call and manage services
@Service
public class ServiceFacade {

  @Autowired
  VehicleService vehicleService;

  //---------------------------------------------------------------------------
  // User Services
  public static Auth register(User user) {
    return null;
  }

  public static Auth login(User user) {
    return null;
  }

  public static void invalidateAuthToken(String sessionKey) {
    return;
  }

  public static String requestReset(String email) {
    return null;
  }

  public static String updateUser(String token, User user) {
    return null;
  }

  //---------------------------------------------------------------------------
  // Vehicle Service
  public static List<Vehicle> getAllVehicles(String sessionKey) {
    return null;
  }

  public Vehicle getVehicle(String sessionKey, String vehicleID) {
    return this.vehicleService.getVehicle(null, vehicleID);
  }

  public static String addVehicle(String sessionKey, Vehicle newVehicle) {
    return null;
  }

  public static String updateVehicle(String sessionKey, String vehicleID, Vehicle newVehicle) {
    return null;
  }

  public static String deleteVehicle(String sessionKey, String vehicleID) {
    return null;
  }

  //---------------------------------------------------------------------------
  // Item Services
  public static List<MaintItem> getAllItems(String sessionKey, String vehicleID) {
    return null;
  }

  public static MaintItem getItem(String sessionKey, String vehicleID, String itemID) {
    return null;
  }

  public static String addItem(String sessionKey, String vehicleID, MaintItem newItem) {
    return null;
  }

  public static String updateItem(String sessionKey, String vehicleID, String itemID, MaintItem newItem) {
    return null;
  }

  public static String deleteItem(String sessionKey, String vehicleID, String itemID) {
    return null;
  }

  //---------------------------------------------------------------------------
  // Event services
  public static List<MaintEvent> getAllEvents(String sessionKey, String vehicleID) {
    return null;
  }

  public static MaintEvent getEvent(String sessionKey, String vehicleID, String eventID) {
    return null;
  }

  public static String addEvent(String sessionKey, String vehicleID, MaintEvent newEvent) {
    return null;
  }

  public static String updateEvent(String sessionKey, String vehicleID, String eventID, MaintEvent newEvent) {
    return null;
  }

  public static String deleteEvent(String sessionKey, String vehicleID, String eventID) {
    return null;
  }

  //---------------------------------------------------------------------------
  // Notification services
  public static List<Notification> getAllNotifications(String sessionKey, String vehicleID) {
    return null;
  }

  public static Notification getNotification(String sessionKey, String vehicleID, String notificationID) {
    return null;
  }

  public static String deleteNotification(String sessionKey, String vehicleID, String notificationID) {
    return null;
  }

}
