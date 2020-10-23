package com.example.dataAccess;

import com.example.model.User;

public interface IUserDao {
  User retrieveUser(String userId);
  User retrieveUserFromEmail(String email);
  void createUser(User newUser);
  void deleteUser(String userId);
  void updateUser(User updatedUser);
}
