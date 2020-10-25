package com.example.service;

import com.example.exceptions.UnauthorizedException;
import com.example.model.*;
import java.util.List;

//TODO: Implement facade to call and manage services
public class ServiceFacade {

  private ServiceFacade() {}

  //---------------------------------------------------------------------------
  // User Services
  public static String register(User user) throws Exception {
    return new UserAccountService()
      .register(user.getEmail(), user.getPassword(), user.getDisplayName(), user.getPhoneNumber());
  }

  public static String login(User user) throws Exception {
    return new UserAccountService().login(user.getEmail(), user.getPassword());
  }

  public static void invalidateAuthToken(String sessionKey) {
    new UserAccountService().deleteSessionKey(sessionKey);
  }

  public static String requestReset(String email) {
    return new UserAccountService().requestPasswordReset(email);
  }

  public static String updateUser(String token, User user) { // TODO: split token into resetToken and sessionKey
    return new UserAccountService().updateUser(resetToken, sessionKey, user.getEmail(), 
      user.getPassword(), user.getDisplayName(), user.getPhoneNumber());
  }

  //---------------------------------------------------------------------------
  // Vehicle Service
  public static List<Vehicle> getAllVehicles(String sessionKey) throws UnauthorizedException {
    return new VehicleService().getAllVehicles(sessionKey);
  }

  public static Vehicle getVehicle(String sessionKey, String vehicleId) throws UnauthorizedException {
    return new VehicleService().getVehicle(sessionKey, vehicleId);
  }

  public static String addVehicle(String sessionKey, Vehicle newVehicle) throws UnauthorizedException {
    return new VehicleService().addVehicle(sessionKey, newVehicle);
  }

  public static String updateVehicle(String sessionKey, String vehicleId, Vehicle updatedVehicle) throws UnauthorizedException {
    return new VehicleService().updateVehicle(sessionKey, vehicleId, updatedVehicle);
  }

  public static String deleteVehicle(String sessionKey, String vehicleId) throws UnauthorizedException {
    return new VehicleService().deleteVehicle(sessionKey, vehicleId);
  }

  //---------------------------------------------------------------------------
  // Item Services
  public static List<MaintItem> getAllItems(String sessionKey, String vehicleID) {
    return new MaintenanceItemService().getAllItems(sessionKey, vehicleID);
  }

  public static MaintItem getItem(String sessionKey, String vehicleID, String itemID) {
    return new MaintenanceItemService().getItem(sessionKey, vehicleID, itemID);
  }

  public static String addItem(String sessionKey, String vehicleID, MaintItem newItem) {
    return new MaintenanceItemService().addItem(sessionKey, vehicleID, newItem);
  }

  public static String updateItem(String sessionKey, String vehicleID, String itemID, MaintItem newItem) {
    return new MaintenanceItemService().updateItem(sessionKey, vehicleID, itemID, newItem);
  }

  public static String deleteItem(String sessionKey, String vehicleID, String itemID) {
    return new MaintenanceItemService().deleteItem(sessionKey, vehicleID, itemID);
  }

  //---------------------------------------------------------------------------
  // Event services
  public static List<MaintEvent> getAllEvents(String sessionKey, String vehicleID) {
    return new MaintenanceEventService().getAllEvents(sessionKey, vehicleID);
  }

  public static MaintEvent getEvent(String sessionKey, String vehicleID, String eventID) {
    return new MaintenanceEventService().getEvent(sessionKey, vehicleID, eventID);
  }

  public static String addEvent(String sessionKey, String vehicleID, MaintEvent newEvent) {
    return new MaintenanceEventService().addEvent(sessionKey, vehicleID, newEvent);
  }

  public static String updateEvent(String sessionKey, String vehicleID, String eventID, MaintEvent newEvent) {
    return new MaintenanceEventService().updateEvent(sessionKey, vehicleID, eventID, newEvent);
  }

  public static String deleteEvent(String sessionKey, String vehicleID, String eventID) {
    return new MaintenanceEventService().deleteEvent(sessionKey, vehicleID, eventID);
  }

  //---------------------------------------------------------------------------
  // Notification services
  public static List<Notification> getAllNotifications(String sessionKey, String vehicleID) {
    return new NotificationService().getAllNotifications(sessionKey, vehicleID);
  }

  public static Notification getNotification(String sessionKey, String vehicleID, String notificationID) {
    return new NotificationService().getNotification(sessionKey, vehicleID, notificationID);
  }

  public static String deleteNotification(String sessionKey, String vehicleID, String notificationID) {
    return new NotificationService().deleteNotification(sessionKey, vehicleID, notificationID);
  }

}
