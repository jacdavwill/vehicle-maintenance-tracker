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

  protected void checkValidVehicle(Integer userId, Integer vehicleId) throws UnauthorizedException, NotFoundException {
    Vehicle vehicle = vehicleDao.retrieveVehicle(vehicleId);
    if (vehicle == null) {
      throw new NotFoundException("Vehicle not found");
    }
    if (vehicle.getUserId() != userId) {
      throw new UnauthorizedException();
    }
  }

  private void checkValidAuthToken(String authToken) throws UnauthorizedException {
    Auth auth = authDao.retrieveAuth(authToken);
    if (auth == null || isExpired(auth)) {
      throw new UnauthorizedException("Invalid authToken");
    }
  }

  private boolean isExpired(Auth auth) {
    // TODO: update this to do time checking and expiry of authTokens
    return false;
  }
  
}
