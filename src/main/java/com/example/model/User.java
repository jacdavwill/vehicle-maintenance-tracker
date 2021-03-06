package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
  public int userId;
  public String email;
  public String password;
  public String salt;
  public String displayName;
  public String phone;

  public User(String email, String password, String salt, String displayName, String phone) {
    this.email = email;
    this.password = password;
    this.salt = salt;
    this.displayName = displayName;
    this.phone = phone;
  }

  public User(String email, String password, String displayName) {
    this.email = email;
    this.password = password;
    this.displayName = displayName;
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public void updatePassword(String salt, String password) {
    this.salt = salt;
    this.password = password;
  }

}
