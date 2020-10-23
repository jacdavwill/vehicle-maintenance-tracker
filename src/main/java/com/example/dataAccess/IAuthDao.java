package com.example.dataAccess;

import com.example.model.Auth;

public interface IAuthDao {
  Auth retrieveAuth(String sessionKey);
  void createAuth(Auth newAuth);
  void deleteAuth(String sessionKey);
  void updateAuth(Auth updatedAuth);
}
