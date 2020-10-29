package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private Integer userId;
  private String email;
  private String password;
  private String salt;
  private String displayName;
  private String phone;

  public User(String email, String password, String salt, String displayName, String phone) {
    this.email = email;
    this.password = password;
    this.salt = salt;
    this.displayName = displayName;
    this.phone = phone;
  }

  public User(Integer userId, String email, String password, String salt, String displayName, String phone) {
    this.userId = userId;
    this.email = email;
    this.password = password;
    this.salt = salt;
    this.displayName = displayName;
    this.phone = phone;
  }

  public Integer getUserId() {
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
