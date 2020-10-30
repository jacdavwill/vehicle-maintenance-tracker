package com.example.dataAccess;

import com.example.model.User;

public interface IUserDao {
  User retrieveUser(Integer userId);
  User retrieveUser(String email);
  Integer createUser(User user);
  void deleteUser(Integer userId);
  void updateUser(User updatedUser);
}
