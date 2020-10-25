package com.example.model;

import java.util.UUID;

import lombok.Data;

@Data
public class User {
  private String userId;
  private String email;
  private String password;
  private String salt;
  private String displayName;
  private String phone;

  public User(String email, String password, String salt, String displayName, String phone) {
    this.userId = UUID.randomUUID().toString();
    this.email = email;
    this.password = password;
    this.salt = salt;
    this.displayName = displayName;
    this.phone = phone;
  }

  public String getUserId() {
    return this.userId;
  }

  public String getPassword() {
    return this.password;
  }

  public String getSalt() {
    return this.salt;
  }

  public String getEmail() {
    return this.email;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public String getPhoneNumber() {
    return this.phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void updatePassword(String salt, String password) {
    this.salt = salt;
    this.password = password;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
