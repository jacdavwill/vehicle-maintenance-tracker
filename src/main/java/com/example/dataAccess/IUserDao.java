package com.example.dataAccess;

import com.example.model.User;

public interface IUserDao {
  User retrieveUser(int userId);
  void createUser(User newUser);
  void deleteUser(int userId);
  void updateUser(User updatedUser);
}
