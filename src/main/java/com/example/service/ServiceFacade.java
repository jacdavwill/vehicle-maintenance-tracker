package com.example.service;

import com.example.exceptions.UnauthorizedException;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: Implement facade to call and manage services
@Service
public class ServiceFacade {

  @Autowired
  VehicleService vehicleService;

  UserAccountService userService;
  MaintenanceItemService maintItemService;
  MaintenanceEventService maintEventService;
  NotificationService notificationService;

  public ServiceFacade() {
    userService = new UserAccountService();
    maintItemService = new MaintenanceItemService();
    maintEventService = new MaintenanceEventService();
    notificationService = new NotificationService();
  }

  //---------------------------------------------------------------------------
  // User Services
  public static String register(User user) throws Exception {
    return userService.register(user.getEmail(), user.getPassword(), user.getDisplayName(), user.getPhoneNumber());
  }

  public static String login(User user) throws Exception {
    return userService.login(user.getEmail(), user.getPassword());
  }

  public static void invalidateAuthToken(String authToken) {
    userService.deleteAuthToken(authToken);
  }

  public static String requestReset(String email) {
    return userService.requestPasswordReset(email);
  }

  public static String updateUser(String token, User user) { // TODO: split token into resetToken and authToken
    return userService.updateUser(resetToken, authToken, user.getEmail(), 
      user.getPassword(), user.getDisplayName(), user.getPhoneNumber());
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
    return maintItemService.getAllItems(authToken, vehicleID);
  }

  public static MaintItem getItem(String authToken, String vehicleID, String itemID) {
    return maintItemService.getItem(authToken, vehicleID, itemID);
  }

  public static String addItem(String authToken, String vehicleID, MaintItem newItem) {
    return maintItemService.addItem(authToken, vehicleID, newItem);
  }

  public static String updateItem(String authToken, String vehicleID, String itemID, MaintItem newItem) {
    return maintItemService.updateItem(authToken, vehicleID, itemID, newItem);
  }

  public static String deleteItem(String authToken, String vehicleID, String itemID) {
    return maintItemService.deleteItem(authToken, vehicleID, itemID);
  }

  //---------------------------------------------------------------------------
  // Event services
  public static List<MaintEvent> getAllEvents(String authToken, String vehicleID) {
    return maintEventService.getAllEvents(authToken, vehicleID);
  }

  public static MaintEvent getEvent(String authToken, String vehicleID, String eventID) {
    return maintEventService.getEvent(authToken, vehicleID, eventID);
  }

  public static String addEvent(String authToken, String vehicleID, MaintEvent newEvent) {
    return maintEventService.addEvent(authToken, vehicleID, newEvent);
  }

  public static String updateEvent(String authToken, String vehicleID, String eventID, MaintEvent newEvent) {
    return maintEventService.updateEvent(authToken, vehicleID, eventID, newEvent);
  }

  public static String deleteEvent(String authToken, String vehicleID, String eventID) {
    return maintEventService.deleteEvent(authToken, vehicleID, eventID);
  }

  //---------------------------------------------------------------------------
  // Notification services
  public static List<Notification> getAllNotifications(String authToken, String vehicleID) {
    return notificationService.getAllNotifications(authToken, vehicleID);
  }

  public static Notification getNotification(String authToken, String vehicleID, String notificationID) {
    return notificationService.getNotification(authToken, vehicleID, notificationID);
  }

  public static String deleteNotification(String authToken, String vehicleID, String notificationID) {
    return notificationService.deleteNotification(authToken, vehicleID, notificationID);
  }

}
