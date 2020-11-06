package com.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Auth {
  public int userId;
  public String authToken;
  public Date createTime;

  public Auth() {}

  public Auth(int userId, String authToken) {
    this.userId = userId;
    this.authToken = authToken;
    this.createTime = new Date(System.currentTimeMillis()); // TODO: Change to correct date format
  }

}