package com.example.service;
import com.example.model.Vehicle;

// TODO: Finish services
public class VehicleService {

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
  public String getAllVehicles(String authToken) {
    return "";
  }

  /**
   * Searches database for specific vehicle that matches vehicleID
   * @param authToken token of current session
   * @param vehicleID ID of vehicle to search database for
   * @return vehicle with matching ID, or error if invalid token or not found
   */
  public String getVehicle(String authToken, String vehicleID) {
    return "";
  }

  /**
   * Adds new vehicle to database
   * @param authToken token of current session
   * @param newVehicle vehicle object of new vehicle
   * @return String vehicleID on success, error if invalid
   */
  public String addVehicle(String authToken, Vehicle newVehicle) {
    return "";
  }

  /**
   * Updates entry in database with corresponding vehicleID to updatedVehicle
   * @param authToken token of current session
   * @param vehicleID ID of vehicle to search database for
   * @param updatedVehicle vehicle object of updated vehicle
   * @return success or error message
   */
  public String editVehicle(String authToken, String vehicleID, Vehicle updatedVehicle) {
    return "";
  }

  /**
   * Deletes entry in database with corresponding vehicleID
   * @param authToken token of current session
   * @param vehicleID ID of vehicle to search database for
   * @return success or error message
   */
  public String deleteVehicle(String authToken, String vehicleID) {
    return "";
  }
}
