package com.example.model;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Auth {
  private int userId;
  private String authToken;
  private Date createTime;

  public Auth(int userId, String authToken, Date createTime) {
    this.userId = userId;
    this.authToken = authToken;
    this.createTime = createTime;
  }

  public Auth(int userId, String authToken) {
    this.userId = userId;
    this.authToken = authToken;
    this.createTime = new Date(System.currentTimeMillis()); // TODO: Change to correct date format
  }

  public Auth(int userId) {
    this.userId = userId;
    this.authToken = UUID.randomUUID().toString();
    this.createTime = new Date(System.currentTimeMillis());
  }

  public int getUserId() {
    return this.userId;
  }

  public String getAuthToken() {
    return authToken;
  }

  public Date getCreateTime() {
    return createTime;
  }

}