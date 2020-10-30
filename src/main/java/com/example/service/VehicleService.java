package com.example.service;
import com.example.dataAccess.VehicleDao;
import com.example.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Finish services
@Service
public class VehicleService {

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
   * Searches the database to associate the authToken with user_id
   * Searches the database for all vehicles associated with user
   * @param authToken token of current session
   * @return list of vehicles or error if auth token not accepted
   */
  public List<Vehicle> getAllVehicles(String authToken) {
    return vehicleDao.retrieveVehicles(12);
  }

  /**
   * Searches database for specific vehicle that matches vehicleID
   * @param authToken token of current session
   * @param vehicleID ID of vehicle to search database for
   * @return vehicle with matching ID, or error if invalid token or not found
   */
  public Vehicle getVehicle(String authToken, Integer vehicleID) {
    return vehicleDao.retrieveVehicle(vehicleID);
  }

  /**
   * Adds new vehicle to database
   * @param authToken token of current session
   * @param newVehicle vehicle object of new vehicle
   * @return String vehicleID on success, error if invalid
   */
  public Integer addVehicle(String authToken, Vehicle newVehicle) {
    return vehicleDao.createVehicle(newVehicle);

  }

  /**
   * Updates entry in database with corresponding vehicleID to updatedVehicle
   * @param authToken token of current session
   * @param updatedVehicle vehicle object of updated vehicle
   * @return success or error message
   */
  public void editVehicle(String authToken, Vehicle updatedVehicle) {
    vehicleDao.updateVehicle(updatedVehicle);
  }

  /**
   * Deletes entry in database with corresponding vehicleID
   * @param authToken token of current session
   * @param vehicleId ID of vehicle to search database for
   */
  public void deleteVehicle(String authToken, Integer vehicleId) {
    vehicleDao.deleteVehicle(vehicleId);
  }
}
