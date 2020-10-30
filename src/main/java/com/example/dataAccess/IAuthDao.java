package com.example.dataAccess;

import com.example.model.Auth;

public interface IAuthDao {
  Auth retrieveAuth(Integer userId);
  Auth retrieveAuth(String sessionKey);
  String createAuth(Auth auth);
  void deleteAuth(Integer userId);
  void deleteAuth(String sessionKey);
  void updateAuth(Auth updatedAuth);
}
