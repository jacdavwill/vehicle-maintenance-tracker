package com.example.service;

import com.example.dataAccess.IVehicleDao;
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
   * Helper function to search the database for user_id based on authToken
   * @param authToken token of current session
   * @return String user_id
   */
  private String getUserFromAuthToken(String authToken) {
    return "";
  }

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
    Integer userId = this.getUserFromSessionKey(sessionKey);
    return vehicleDao.retrieveVehicles(userId);
  }

  /**
   * Searches database for specific vehicle that matches vehicleId
   * @param sessionKey token of current session
   * @param vehicleId ID of vehicle to search database for
   * @return vehicle with matching ID, or error if invalid token or not found
   */
  public Vehicle getVehicle(String sessionKey, String vehicleId) throws UnauthorizedException, InternalServiceException {
    this.checkValidSessionKey(sessionKey);
    Integer userId = this.getUserFromSessionKey(sessionKey);
    try {
      Vehicle vehicle = vehicleDao.retrieveVehicle(Integer.parseInt(vehicleId));
      if (!vehicle.getUserId().equals(userId)) {
        throw new UnauthorizedException();
      }
      return vehicle;

    } catch(NumberFormatException e) {
        throw new InternalServiceException();
    }
  }

  /**
   * Adds new vehicle to database
   * @param sessionKey token of current session
   * @param newVehicle vehicle object of new vehicle
   * @return String vehicleId on success, error if invalid
   */
  public String addVehicle(String sessionKey, Vehicle newVehicle) throws UnauthorizedException {
    this.checkValidSessionKey(sessionKey);
    Integer userId = this.getUserFromSessionKey(sessionKey);
    newVehicle.setUserId(userId);

    vehicleDao.createVehicle(newVehicle);
    return Integer.toString(newVehicle.getVehicleId());
  }

  /**
   * Updates entry in database with corresponding vehicleId to updatedVehicle
   * 
   * @param sessionKey     token of current session
   * @param vehicleId      ID of vehicle to search database for
   * @param updatedVehicle vehicle object of updated vehicle
   * @return success or error message
   * @throws InternalServiceException
   */
  public String updateVehicle(String sessionKey, String vehicleId, Vehicle updatedVehicle)
      throws UnauthorizedException, InternalServiceException {

    Vehicle oldVehicle = getVehicle(sessionKey, vehicleId);

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
    if (this.isFieldSet(transmissionType) {
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
   * @param sessionKey token of current session
   * @param vehicleId  ID of vehicle to search database for
   * @return success or error message
   * @throws InternalServiceException
   */
  public String deleteVehicle(String sessionKey, String vehicleId)
      throws UnauthorizedException, InternalServiceException {
    Vehicle vehicle = getVehicle(sessionKey, vehicleId);

    if (vehicle == null) {
      return "error";
    }

    vehicleDao.deleteVehicle(vehicle.getVehicleId());
    return "success";
  }
}
