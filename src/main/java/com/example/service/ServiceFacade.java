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

  public static void invalidateAuthToken(String sessionKey) {
    userService.deleteSessionKey(sessionKey);
  }

  public static String requestReset(String email) {
    return userService.requestPasswordReset(email);
  }

  public static String updateUser(String token, User user) { // TODO: split token into resetToken and sessionKey
    return userService.updateUser(resetToken, sessionKey, user.getEmail(), 
      user.getPassword(), user.getDisplayName(), user.getPhoneNumber());
  }

  //---------------------------------------------------------------------------
  // Vehicle Service
  public List<Vehicle> getAllVehicles(String sessionKey) {

    return this.vehicleService.getAllVehicles(null);
  }

  public Vehicle getVehicle(String sessionKey, Integer vehicleId) {
    return this.vehicleService.getVehicle(null, vehicleId);
  }

  public Integer addVehicle(String sessionKey, Vehicle vehicle) {
    return this.vehicleService.addVehicle(null, vehicle);
  }

  public void updateVehicle(String sessionKey, Vehicle vehicle) {
    this.vehicleService.editVehicle(null, vehicle);
  }

  public void deleteVehicle(String sessionKey, Integer vehicleId) {
    this.vehicleService.deleteVehicle(null, vehicleId);
  }

  //---------------------------------------------------------------------------
  // Item Services
  public static List<MaintItem> getAllItems(String sessionKey, String vehicleID) {
    return maintItemService.getAllItems(sessionKey, vehicleID);
  }

  public static MaintItem getItem(String sessionKey, String vehicleID, String itemID) {
    return maintItemService.getItem(sessionKey, vehicleID, itemID);
  }

  public static String addItem(String sessionKey, String vehicleID, MaintItem newItem) {
    return maintItemService.addItem(sessionKey, vehicleID, newItem);
  }

  public static String updateItem(String sessionKey, String vehicleID, String itemID, MaintItem newItem) {
    return maintItemService.updateItem(sessionKey, vehicleID, itemID, newItem);
  }

  public static String deleteItem(String sessionKey, String vehicleID, String itemID) {
    return maintItemService.deleteItem(sessionKey, vehicleID, itemID);
  }

  //---------------------------------------------------------------------------
  // Event services
  public static List<MaintEvent> getAllEvents(String sessionKey, String vehicleID) {
    return maintEventService.getAllEvents(sessionKey, vehicleID);
  }

  public static MaintEvent getEvent(String sessionKey, String vehicleID, String eventID) {
    return maintEventService.getEvent(sessionKey, vehicleID, eventID);
  }

  public static String addEvent(String sessionKey, String vehicleID, MaintEvent newEvent) {
    return maintEventService.addEvent(sessionKey, vehicleID, newEvent);
  }

  public static String updateEvent(String sessionKey, String vehicleID, String eventID, MaintEvent newEvent) {
    return maintEventService.updateEvent(sessionKey, vehicleID, eventID, newEvent);
  }

  public static String deleteEvent(String sessionKey, String vehicleID, String eventID) {
    return maintEventService.deleteEvent(sessionKey, vehicleID, eventID);
  }

  //---------------------------------------------------------------------------
  // Notification services
  public static List<Notification> getAllNotifications(String sessionKey, String vehicleID) {
    return notificationService.getAllNotifications(sessionKey, vehicleID);
  }

  public static Notification getNotification(String sessionKey, String vehicleID, String notificationID) {
    return notificationService.getNotification(sessionKey, vehicleID, notificationID);
  }

  public static String deleteNotification(String sessionKey, String vehicleID, String notificationID) {
    return notificationService.deleteNotification(sessionKey, vehicleID, notificationID);
  }

}
