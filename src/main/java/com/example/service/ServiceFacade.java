package com.example.service;

import com.example.exceptions.InternalServiceException;
import com.example.exceptions.NotFoundException;
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

  // ---------------------------------------------------------------------------
  // User Services
  public String register(User user) throws Exception {
    return userService.register(user.getEmail(), user.getPassword(), user.getDisplayName(), user.getPhoneNumber());
  }

  public String login(User user) throws Exception {
    return userService.login(user.getEmail(), user.getPassword());
  }

  public void invalidateAuthToken(String authToken) {
    userService.deleteAuthToken(authToken);
  }

  public String requestReset(String email) {
    return userService.requestPasswordReset(email);
  }

  public String updateUser(String resetToken, String authToken, User user)
      throws UnauthorizedException, InternalServiceException {
    return userService.updateUser(resetToken, authToken, user.getEmail(), user.getPassword(), user.getDisplayName(),
        user.getPhoneNumber());
  }

  // ---------------------------------------------------------------------------
  // Vehicle Service
  public List<Vehicle> getAllVehicles(String authToken) throws UnauthorizedException {
    return this.vehicleService.getAllVehicles(authToken);
  }

  public Vehicle getVehicle(String authToken, Integer vehicleId) throws UnauthorizedException {
    return this.vehicleService.getVehicle(authToken, vehicleId);
  }

  public Integer addVehicle(String authToken, Vehicle vehicle) throws UnauthorizedException {
    return this.vehicleService.addVehicle(authToken, vehicle);
  }

  public void updateVehicle(String authToken, Integer vehicleId, Vehicle vehicle) throws UnauthorizedException {
    this.vehicleService.updateVehicle(authToken, vehicleId, vehicle);
  }

  public void deleteVehicle(String authToken, Integer vehicleId) throws UnauthorizedException {
    this.vehicleService.deleteVehicle(authToken, vehicleId);
  }

  // ---------------------------------------------------------------------------
  // Item Services
  public List<MaintItem> getAllItems(String authToken, String vehicleID)
      throws NotFoundException, UnauthorizedException {
    return this.maintItemService.getAllItems(authToken, vehicleID);
  }

  public MaintItem getItem(String authToken, String vehicleID, String itemID)
      throws NotFoundException, UnauthorizedException {
    return maintItemService.getItem(authToken, vehicleID, itemID);
  }

  public String addItem(String authToken, String vehicleID, MaintItem newItem)
      throws NotFoundException, UnauthorizedException {
    return maintItemService.addItem(authToken, vehicleID, newItem);
  }

  public String updateItem(String authToken, String vehicleID, String itemID, MaintItem newItem)
      throws NotFoundException, UnauthorizedException {
    return maintItemService.updateItem(authToken, vehicleID, itemID, newItem);
  }

  public String deleteItem(String authToken, String vehicleID, String itemID)
      throws NotFoundException, UnauthorizedException {
    return maintItemService.deleteItem(authToken, vehicleID, itemID);
  }

  //---------------------------------------------------------------------------
  // Event services
  public List<MaintEvent> getAllEvents(String authToken, String vehicleID) {
    return maintEventService.getAllEvents(authToken, vehicleID);
  }

  public MaintEvent getEvent(String authToken, String vehicleID, String eventID) {
    return maintEventService.getEvent(authToken, vehicleID, eventID);
  }

  public String addEvent(String authToken, String vehicleID, MaintEvent newEvent) {
    return maintEventService.addEvent(authToken, vehicleID, newEvent);
  }

  public String updateEvent(String authToken, String vehicleID, String eventID, MaintEvent newEvent) {
    return maintEventService.updateEvent(authToken, vehicleID, eventID, newEvent);
  }

  public String deleteEvent(String authToken, String vehicleID, String eventID) {
    return maintEventService.deleteEvent(authToken, vehicleID, eventID);
  }

  //---------------------------------------------------------------------------
  // Notification services
  public List<Notification> getAllNotifications(String authToken, String vehicleID) {
    return notificationService.getAllNotifications(authToken, vehicleID);
  }

  public Notification getNotification(String authToken, String vehicleID, String notificationID) {
    return notificationService.getNotification(authToken, vehicleID, notificationID);
  }

  public String deleteNotification(String authToken, String vehicleID, String notificationID) {
    return notificationService.deleteNotification(authToken, vehicleID, notificationID);
  }

}
