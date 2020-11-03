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
  @Autowired
  NotificationService notificationService;
  @Autowired
  MaintenanceEventService maintenanceEventService;
  @Autowired
  MaintenanceItemService maintenanceItemService;
  @Autowired
  UserAccountService userAccountService;



  //---------------------------------------------------------------------------
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
