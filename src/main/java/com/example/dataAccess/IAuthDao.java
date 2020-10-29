package com.example.dataAccess;

import com.example.model.Auth;

public interface IAuthDao {
  Auth retrieveAuth(Integer userId);
  String createAuth(Auth auth);
  void deleteAuth(Integer userId);
  void updateAuth(Auth updatedAuth);
}
