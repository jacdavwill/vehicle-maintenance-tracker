package com.example.service;

import com.example.dataAccess.IAuthDao;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Auth;

public abstract class Service {
    /**
   * Helper function to search the database for user_id based on sessionKey
   * @param sessionKey token of current session
   * @return int userId
   */
  protected int getUserFromSessionKey(String sessionKey) { // What does this return? userId? email?
    IAuthDao authDao; // = new AuthDAO; TODO: instantiate interface
    return authDao.retrieveAuth(sessionKey).getUserId();
  }

  protected void checkValidSessionKey(String sessionKey) throws UnauthorizedException {
    IAuthDao authDao; // = new AuthDAO; TODO
    Auth auth = authDao.retrieveAuth(sessionKey);
    if (auth != null) { // TODO: update this to do time checking and expiry of sessionKeys
      throw new UnauthorizedException("Invalid sessionKey");
    }
  }
}
