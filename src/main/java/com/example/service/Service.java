package com.example.service;

import com.example.dataAccess.*;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Auth;

import com.example.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Service {
  
  @Autowired
  private AuthDao authDao;
  @Autowired
  private VehicleDao vehicleDao;
  
    /**
   * Helper function to search the database for user_id based on authToken. Insures
     * that the authToken is valid.
   * @param authToken token of current session
   * @return int userId
   */
  protected int getUserIdFromAuthToken(String authToken) throws UnauthorizedException {
    checkValidAuthToken(authToken);
    return authDao.retrieveAuth(authToken).getUserId();
  }

  /**
   * Helper function to search database for a vehicle and make sure that the vehicle found is
   * associated with the userid provided.
   * @param userId The userId that should be associated with the vehicle
   * @param vehicleId The id of the vehicle being checked
   * @throws UnauthorizedException Thrown if the userId is not associated with the vehicle
   * @throws NotFoundException Thrown if the vehicle is not found
   */
  protected void checkValidVehicle(Integer userId, Integer vehicleId) throws UnauthorizedException, NotFoundException {
    Vehicle vehicle = vehicleDao.retrieveVehicle(vehicleId);
    if (vehicle == null) {
      throw new NotFoundException("Vehicle not found");
    }
    if (vehicle.getUserId() != userId) {
      throw new UnauthorizedException();
    }
  }

  protected void checkValidAuthToken(String authToken) throws UnauthorizedException {
    Auth auth = authDao.retrieveAuth(authToken);
    if (auth == null || isExpired(auth)) {
      throw new UnauthorizedException("Invalid authToken");
    }
  }

  protected Boolean isFieldSet(String str) {
    return (str != null && !str.equals(""));
  }

  private boolean isExpired(Auth auth) {
    // TODO: update this to do time checking and expiry of authTokens
    return false;
  }
  
}
