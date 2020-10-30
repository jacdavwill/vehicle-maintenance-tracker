package com.example.service;

import com.example.dataAccess.*;
import com.example.exceptions.UnauthorizedException;
import com.example.model.Auth;

public abstract class Service {
    /**
   * Helper function to search the database for user_id based on sessionKey
   * @param sessionKey token of current session
   * @return int userId
   */
  protected Integer getUserFromSessionKey(String sessionKey) {
    IAuthDao authDao = new AuthDao();
    return authDao.retrieveAuth(sessionKey).getUserId();
  }

  protected void checkValidSessionKey(String sessionKey) throws UnauthorizedException {
    IAuthDao authDao = new AuthDao();
    Auth auth = authDao.retrieveAuth(sessionKey);
    if (auth != null && !isExpired(auth)) {
      throw new UnauthorizedException("Invalid sessionKey");
    }
  }

  private boolean isExpired(Auth auth) {
    // TODO: update this to do time checking and expiry of sessionKeys
    return false;
  }
}
