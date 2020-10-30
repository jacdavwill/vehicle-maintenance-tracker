package com.example.service;

import com.example.dataAccess.VehicleDao;
import com.example.exceptions.InternalServiceException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Finish services
@Service
public class VehicleService extends com.example.service.Service {

  @Autowired
  VehicleDao vehicleDao;

  /**
   * Searches the database to associate the authToken with user_id Searches the
   * database for all vehicles associated with user
   * 
   * @param authToken token of current session
   * @return list of vehicles or error if auth token not accepted
   * @throws UnauthorizedException
   */
  public List<Vehicle> getAllVehicles(String authToken) throws UnauthorizedException {
    this.checkValidAuthToken(authToken);
    Integer userId = this.getUserFromAuthToken(authToken);
    return vehicleDao.retrieveVehicles(userId);
  }

  /**
   * Searches database for specific vehicle that matches vehicleId
   * @param authToken token of current session
   * @param vehicleId ID of vehicle to search database for
   * @return vehicle with matching ID, or error if invalid token or not found
   */
  public Vehicle getVehicle(String authToken, Integer vehicleId) throws UnauthorizedException {
    this.checkValidAuthToken(authToken);
    Integer userId = this.getUserFromAuthToken(authToken);
    Vehicle vehicle = vehicleDao.retrieveVehicle(vehicleId);
    if (!vehicle.getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }
    return vehicle;
  }

  /**
   * Adds new vehicle to database
   * @param authToken token of current session
   * @param newVehicle vehicle object of new vehicle
   * @return String vehicleId on success, error if invalid
   */
  public Integer addVehicle(String authToken, Vehicle newVehicle) throws UnauthorizedException {
    this.checkValidAuthToken(authToken);
    Integer userId = this.getUserFromAuthToken(authToken);
    newVehicle.setUserId(userId);

    vehicleDao.createVehicle(newVehicle);
    return newVehicle.getVehicleId();
  }

  /**
   * Updates entry in database with corresponding vehicleId to updatedVehicle
   * 
   * @param authToken     token of current session
   * @param vehicleId      ID of vehicle to search database for
   * @param updatedVehicle vehicle object of updated vehicle
   * @return success or error message
   * @throws InternalServiceException
   */
  public String updateVehicle(String authToken, Integer vehicleId, Vehicle updatedVehicle)
      throws UnauthorizedException {

    Vehicle oldVehicle = this.getVehicle(authToken, vehicleId);

    String nickname = updatedVehicle.getNickname();
    if (this.isFieldSet(nickname)) {
      oldVehicle.setNickname(nickname);
    }
    String imageUrl = updatedVehicle.getImageUrl();
    if (this.isFieldSet(imageUrl)) {
      oldVehicle.setImageUrl(imageUrl);
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
    if (this.isFieldSet(make)) {
      oldVehicle.setMake(make);
    }
    String model = updatedVehicle.getModel();
    if (this.isFieldSet(model)) {
      oldVehicle.setModel(model);
    }
    Integer year = updatedVehicle.getYear();
    if (year != null) {
      oldVehicle.setYear(year);
    }
    String color = updatedVehicle.getColor();
    if (this.isFieldSet(color)) {
      oldVehicle.setColor(color);
    }
    String transmissionType = updatedVehicle.getTransmissionType();
    if (this.isFieldSet(transmissionType)) {
      oldVehicle.setTransmissionType(transmissionType);
    }
    String energyType = updatedVehicle.getEnergyType();
    if (this.isFieldSet(energyType)) {
      oldVehicle.setEnergyType(energyType);
    }

    vehicleDao.updateVehicle(oldVehicle);
    return "success";
  }

  public boolean isFieldSet(String str) {
    return (str != null && !str.equals(""));
  }

  /**
   * Deletes entry in database with corresponding vehicleId
   * 
   * @param authToken token of current session
   * @param vehicleId  ID of vehicle to search database for
   * @return success or error message
   * @throws InternalServiceException
   */
  public String deleteVehicle(String authToken, Integer vehicleId)
      throws UnauthorizedException {
    Vehicle vehicle = this.getVehicle(authToken, vehicleId);

    if (vehicle == null) {
      return "error";
    }

    vehicleDao.deleteVehicle(vehicle.getVehicleId());
    return "success";
  }
}
