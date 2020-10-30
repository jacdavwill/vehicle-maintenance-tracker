package com.example.dataAccess;

import com.example.model.Auth;

public interface IAuthDao {
  Auth retrieveAuth(Integer userId);
  Auth retrieveAuth(String authToken);
  String createAuth(Auth auth);
  void deleteAuth(Integer userId);
  void deleteAuth(String authToken);
  void updateAuth(Auth updatedAuth);
}
