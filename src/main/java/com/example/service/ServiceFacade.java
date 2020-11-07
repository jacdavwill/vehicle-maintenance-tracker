package com.example.service;

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
  @Autowired
  NotificationService notificationService;
  @Autowired
  MaintenanceEventService maintenanceEventService;
  @Autowired
  MaintenanceItemService maintenanceItemService;
  @Autowired
  UserAccountService userAccountService;

  // ---------------------------------------------------------------------------
  // User Services
  public Auth register(User user) {
    return null;
  }

  public Auth login(User user) {
    return null;
  }

  public void invalidateAuthToken(String authToken) {
    return;
  }

  public String requestReset(String email) {
    return null;
  }

  public String updateUser(String token, User user) {
    return null;
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
  public List<MaintEvent> getAllEvents(String authToken, Integer vehicleID) {
    return null;
  }

  public MaintEvent getEvent(String authToken, Integer vehicleID, Integer eventID) {
    return null;
  }

  public String addEvent(String authToken, Integer vehicleID, MaintEvent newEvent) {
    return null;
  }

  public String updateEvent(String authToken, Integer vehicleID, Integer eventID, MaintEvent newEvent) {
    return null;
  }

  public String deleteEvent(String authToken, Integer vehicleID, Integer eventID) {
    return null;
  }

  //---------------------------------------------------------------------------
  // Notification services
  public List<Notification> getAllNotifications(String authToken, Integer vehicleID) {
    return null;
  }

  public Notification getNotification(String authToken, Integer vehicleID, Integer notificationID) {
    return this.notificationService.getNotification(authToken, notificationID);
  }

  public String deleteNotification(String authToken, Integer vehicleID, Integer notificationID) {
    return null;
  }

}
