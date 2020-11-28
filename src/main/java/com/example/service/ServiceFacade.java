package com.example.service;

import com.example.exceptions.NotFoundException;
import com.example.exceptions.AlreadyExistsException;
import com.example.exceptions.InternalServiceException;
import com.example.exceptions.UnauthorizedException;

import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.client.HttpClientErrorException.NotFound;

//TODO: Implement facade to call and manage services
@Service
public class ServiceFacade {

  @Autowired
  VehicleService vehicleService;
  @Autowired
  NotificationService notificationService;
  @Autowired
  MaintEventService maintEventService;
  @Autowired
  MaintItemService maintItemService;
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
  public List<MaintItem> getAllItems(String authToken, Integer vehicleID) throws UnauthorizedException, NotFoundException {
    return this.maintItemService.getAllMaintItems(authToken, vehicleID);
  }

  public MaintItem getItem(String authToken, Integer vehicleID, Integer itemID) throws UnauthorizedException, NotFoundException {
    return this.maintItemService.getMaintItem(authToken, vehicleID, itemID);
  }

  public Integer addItem(String authToken, Integer vehicleID, MaintItem newItem) throws UnauthorizedException, NotFoundException {
    return this.maintItemService.addMaintItem(authToken, vehicleID, newItem);
  }

  public void updateItem(String authToken, Integer vehicleID, Integer itemID, MaintItem newItem) throws UnauthorizedException, NotFoundException {
    this.maintItemService.updateMaintItem(authToken, vehicleID, itemID, newItem);
  }

  public void deleteItem(String authToken, Integer vehicleID, Integer itemID) throws UnauthorizedException, NotFoundException {
    this.maintItemService.deleteMaintItem(authToken, vehicleID, itemID);
  }

  //---------------------------------------------------------------------------
  // Event services
  public List<MaintEvent> getAllEvents(String authToken, Integer vehicleID)
          throws UnauthorizedException, NotFoundException {
    return this.maintEventService.getAllEvents(authToken, vehicleID);
  }

  public MaintEvent getEvent(String authToken, Integer vehicleID, Integer eventID)
          throws UnauthorizedException, NotFoundException {
    return this.maintEventService.getEvent(authToken, vehicleID, eventID);
  }

  public Integer addEvent(String authToken, Integer vehicleID, MaintEvent newEvent)
          throws UnauthorizedException, NotFoundException {
    return this.maintEventService.addEvent(authToken, vehicleID, newEvent);
  }

  public void updateEvent(String authToken, Integer vehicleID, Integer eventID, MaintEvent newEvent)
          throws UnauthorizedException, NotFoundException {
    this.maintEventService.updateEvent(authToken, vehicleID, eventID, newEvent);
  }

  public void deleteEvent(String authToken, Integer vehicleID, Integer eventID)
          throws UnauthorizedException, NotFoundException{
    this.maintEventService.deleteEvent(authToken, vehicleID, eventID);
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
