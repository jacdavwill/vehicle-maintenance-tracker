package com.example.service;

import com.example.model.*;

import java.util.List;

import com.example.dataAccess.*;
import com.example.exceptions.UnauthorizedException;

// TODO: Finish services
public class VehicleService extends Service {

  /**
   * Searches the database to associate the sessionKey with user_id Searches the
   * database for all vehicles associated with user
   * 
   * @param sessionKey token of current session
   * @return list of vehicles or error if auth token not accepted
   * @throws UnauthorizedException
   */
  public List<Vehicle> getAllVehicles(String sessionKey) throws UnauthorizedException {
    this.checkValidSessionKey(sessionKey);
    String userId = this.getUserFromSessionKey(sessionKey);
    IVehicleDao vehicleDao; // = new VehicleDao(); TODO: instantiate interface
    return vehicleDao.retrieveVehicles(userId);
  }

  /**
   * Searches database for specific vehicle that matches vehicleId
   * @param sessionKey token of current session
   * @param vehicleId ID of vehicle to search database for
   * @return vehicle with matching ID, or error if invalid token or not found
   */
  public Vehicle getVehicle(String sessionKey, String vehicleId) throws UnauthorizedException {
    this.checkValidSessionKey(sessionKey);
    String userId = this.getUserFromSessionKey(sessionKey);
    IVehicleDao vehicleDao; // = new VehicleDao(); TODO: instantiate interface
    Vehicle vehicle = vehicleDao.retrieveVehicle(vehicleId);
    if (userId != vehicle.getUserId()) {
      throw new UnauthorizedException();
    }
    return vehicle;
  }

  /**
   * Adds new vehicle to database
   * @param sessionKey token of current session
   * @param newVehicle vehicle object of new vehicle
   * @return String vehicleId on success, error if invalid
   */
  public String addVehicle(String sessionKey, Vehicle newVehicle) throws UnauthorizedException {
    this.checkValidSessionKey(sessionKey);
    String userId = this.getUserFromSessionKey(sessionKey);
    newVehicle.setUserId(userId);
    IVehicleDao vehicleDao; // = new VehicleDao(); TODO: instantiate interface

    try {
      vehicleDao.createVehicle(newVehicle);
      return newVehicle.getVehicleId();
    } catch (Exception e) {
      return "error";
    }
  }

  /**
   * Updates entry in database with corresponding vehicleId to updatedVehicle
   * @param sessionKey token of current session
   * @param vehicleId ID of vehicle to search database for
   * @param updatedVehicle vehicle object of updated vehicle
   * @return success or error message
   */
  public String editVehicle(String sessionKey, String vehicleId, Vehicle updatedVehicle) throws UnauthorizedException {
    Vehicle oldVehicle = getVehicle(sessionKey, vehicleId);

    String nickname = updatedVehicle.getNickname();
    if (nickname != null && !nickname.equals("")) {
      oldVehicle.setNickname(nickname);
    }
    String imageURL = updatedVehicle.getImageURL();
    if (imageURL != null && !imageURL.equals("")) {
      oldVehicle.setImageURL(imageURL);
    }
    String registrationMonth = updatedVehicle.getRegistrationMonth();
    if (registrationMonth != null && !registrationMonth.equals("")) {
      oldVehicle.setRegistrationMonth(registrationMonth);
    } 
    Integer mileage = updatedVehicle.getMileage();
    if (mileage != null) {
      oldVehicle.setMileage(mileage);
    }
    String make = updatedVehicle.getMake();
    if (make != null && !make.equals("")) {
      oldVehicle.setMake(make);
    }
    String model = updatedVehicle.getModel();
    if (model != null && !model.equals("")) {
      oldVehicle.setModel(model);
    }
    Integer year = updatedVehicle.getYear();
    if (year != null) {
      oldVehicle.setYear(year);
    }
    String color = updatedVehicle.getColor();
    if (color != null && !color.equals("")) {
      oldVehicle.setColor(color);
    }
    String transmissionType = updatedVehicle.getTransmissionType();
    if (transmissionType != null && !transmissionType.equals("")) {
      oldVehicle.setTransmissionType(transmissionType);
    }
    String energyType = updatedVehicle.getEnergyType();
    if (energyType != null && !energyType.equals("")) {
      oldVehicle.setEnergyType(energyType);
    }

    IVehicleDao vehicleDao; // = new VehicleDao(); TODO: instantiate interface
    try {
      vehicleDao.updateVehicle(oldVehicle);
      return "success";
    } catch (Exception e) {
      return "error";
    }
  }

  /**
   * Deletes entry in database with corresponding vehicleId
   * @param sessionKey token of current session
   * @param vehicleId ID of vehicle to search database for
   * @return success or error message
   */
  public String deleteVehicle(String sessionKey, String vehicleId) {
    Vehicle vehicle = getVehicle(sessionKey, vehicleId);
    if (vehicle == null) {
      return "error";
    }
    IVehicleDao vehicleDao; // = new VehicleDao(); TODO: instantiate interface
    try {
      vehicleDao.deleteVehicle(vehicle.getVehicleId());
      return "success";
    }
    catch (Exception e) {
      return "error";
    }
  }
}
