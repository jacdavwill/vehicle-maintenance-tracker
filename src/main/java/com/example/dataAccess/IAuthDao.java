package com.example.dataAccess;

import com.example.model.Auth;

public interface IAuthDao {
  Auth retrieveAuth(int userId);
  void createAuth(Auth newAuth);
  void deleteAuth(int userId);
  void updateAuth(Auth updatedAuth);
}
