package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private int userId;
  private String email;
  private String password;
  private String salt;
  private String displayName;
  private String phone;
}
