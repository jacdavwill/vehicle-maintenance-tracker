package com.example.service;

import com.example.exceptions.NotFoundException;
import com.example.exceptions.AlreadyExistsException;
import com.example.exceptions.InternalServiceException;
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
  @Autowired
  NotificationService notificationService;
  @Autowired
  MaintenanceEventService maintenanceEventService;
  @Autowired
  MaintenanceItemService maintenanceItemService;
  @Autowired
  UserAccountService userService;

  // ---------------------------------------------------------------------------
  // User Services
  public String register(User user) throws InternalServiceException, UnauthorizedException, AlreadyExistsException {
    return userService.register(user.getEmail(), user.getPassword(), user.getDisplayName(), user.getPhone());
  }

  public String login(User user) throws InternalServiceException, UnauthorizedException {
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
        user.getPhone());
  }

  // ---------------------------------------------------------------------------
  // Vehicle Service
  public List<Vehicle> getAllVehicles(String authToken) throws UnauthorizedException {
    return this.vehicleService.getAllVehicles(authToken);
  }

  public Vehicle getVehicle(String authToken, Integer vehicleId) throws UnauthorizedException, NotFoundException {
    return this.vehicleService.getVehicle(authToken, vehicleId);
  }

  public Integer addVehicle(String authToken, Vehicle vehicle) throws UnauthorizedException {
    return this.vehicleService.addVehicle(authToken, vehicle);
  }

  public void updateVehicle(String authToken, int vehicleId, Vehicle vehicle)
      throws UnauthorizedException, NotFoundException {
    this.vehicleService.updateVehicle(authToken, vehicleId, vehicle);
  }

  public void deleteVehicle(String authToken, Integer vehicleId) throws UnauthorizedException, NotFoundException {
    this.vehicleService.deleteVehicle(authToken, vehicleId);
  }

  //---------------------------------------------------------------------------
  // Item Services
  public List<MaintItem> getAllItems(String authToken, Integer vehicleID) {
    return null;
  }

  public MaintItem getItem(String authToken, Integer vehicleID, Integer itemID) {
    return null;
  }

  public String addItem(String authToken, Integer vehicleID, MaintItem newItem) {
    return null;
  }

  public String updateItem(String authToken, Integer vehicleID, Integer itemID, MaintItem newItem) {
    return null;
  }

  public String deleteItem(String authToken, Integer vehicleID, Integer itemID) {
    return null;
  }

  //---------------------------------------------------------------------------
  // Event services
  public List<MaintEvent> getAllEvents(String authToken, Integer vehicleID)
          throws UnauthorizedException {
    return this.maintenanceEventService.getAllEvents(authToken, vehicleID);
  }

  public MaintEvent getEvent(String authToken, Integer vehicleID, Integer eventID)
          throws UnauthorizedException, NotFoundException {
    return this.maintenanceEventService.getEvent(authToken, vehicleID, eventID);
  }

  public Integer addEvent(String authToken, Integer vehicleID, MaintEvent newEvent)
          throws UnauthorizedException {
    return this.maintenanceEventService.addEvent(authToken, vehicleID, newEvent);
  }

  public void updateEvent(String authToken, Integer vehicleID, Integer eventID, MaintEvent newEvent)
          throws UnauthorizedException, NotFoundException {
    this.maintenanceEventService.updateEvent(authToken, vehicleID, eventID, newEvent);
  }

  public void deleteEvent(String authToken, Integer vehicleID, Integer eventID)
          throws UnauthorizedException, NotFoundException{
    this.maintenanceEventService.deleteEvent(authToken, vehicleID, eventID);
  }

  //---------------------------------------------------------------------------
  // Notification services
  public List<Notification> getAllNotifications(String authToken, Integer vehicleID) {
    return null;
  }

  public Notification getNotification(String authToken, Integer vehicleID, Integer notificationID) throws UnauthorizedException, NotFoundException{
    return this.notificationService.getNotification(authToken, notificationID);
  }

  public String deleteNotification(String authToken, Integer vehicleID, Integer notificationID) {
    return null;
  }

}
