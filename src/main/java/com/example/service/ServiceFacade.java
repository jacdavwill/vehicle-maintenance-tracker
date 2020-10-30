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

  public static void invalidateAuthToken(String authToken) {
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
  public List<Vehicle> getAllVehicles(String authToken) {

    return this.vehicleService.getAllVehicles(null);
  }

  public Vehicle getVehicle(String authToken, Integer vehicleId) {
    return this.vehicleService.getVehicle(null, vehicleId);
  }

  public Integer addVehicle(String authToken, Vehicle vehicle) {
    return this.vehicleService.addVehicle(null, vehicle);
  }

  public void updateVehicle(String authToken, Vehicle vehicle) {
    this.vehicleService.editVehicle(null, vehicle);
  }

  public void deleteVehicle(String authToken, Integer vehicleId) {
    this.vehicleService.deleteVehicle(null, vehicleId);
  }

  //---------------------------------------------------------------------------
  // Item Services
  public static List<MaintItem> getAllItems(String authToken, String vehicleID) {
    return null;
  }

  public static MaintItem getItem(String authToken, String vehicleID, String itemID) {
    return null;
  }

  public static String addItem(String authToken, String vehicleID, MaintItem newItem) {
    return null;
  }

  public static String updateItem(String authToken, String vehicleID, String itemID, MaintItem newItem) {
    return null;
  }

  public static String deleteItem(String authToken, String vehicleID, String itemID) {
    return null;
  }

  //---------------------------------------------------------------------------
  // Event services
  public static List<MaintEvent> getAllEvents(String authToken, String vehicleID) {
    return null;
  }

  public static MaintEvent getEvent(String authToken, String vehicleID, String eventID) {
    return null;
  }

  public static String addEvent(String authToken, String vehicleID, MaintEvent newEvent) {
    return null;
  }

  public static String updateEvent(String authToken, String vehicleID, String eventID, MaintEvent newEvent) {
    return null;
  }

  public static String deleteEvent(String authToken, String vehicleID, String eventID) {
    return null;
  }

  //---------------------------------------------------------------------------
  // Notification services
  public static List<Notification> getAllNotifications(String authToken, String vehicleID) {
    return null;
  }

  public static Notification getNotification(String authToken, String vehicleID, String notificationID) {
    return null;
  }

  public static String deleteNotification(String authToken, String vehicleID, String notificationID) {
    return null;
  }

}
