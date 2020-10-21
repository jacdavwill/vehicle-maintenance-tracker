package com.example.model;

import lombok.Data;

@Data
public class User {
  private int userId;
  private String email;
  private String password;
  private String salt;
  private String displayName;
  private String phone;
}
