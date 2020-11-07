package com.example.service;

import com.example.dataAccess.*;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Auth;

public abstract class Service {
    /**
   * Helper function to search the database for user_id based on authToken
   * @param authToken token of current session
   * @return int userId
   */
  protected Integer getUserFromAuthToken(String authToken) {
    IAuthDao authDao = new AuthDao();
    return authDao.retrieveAuth(authToken).getUserId();
  }

  protected void checkValidAuthToken(String authToken) throws UnauthorizedException {
    IAuthDao authDao = new AuthDao();
    Auth auth = authDao.retrieveAuth(authToken);
    if (auth != null && !isExpired(auth)) {
      throw new UnauthorizedException("Invalid authToken");
    }
  }

  private boolean isExpired(Auth auth) {
    // TODO: update this to do time checking and expiry of authTokens
    return false;
  }
}