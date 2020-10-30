package com.example.model;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Auth {
  
  private Integer userId;
  private String authToken;
  private Date createTime;

  Auth(Integer userId, String authToken, Date createTime) {
    this.userId = userId;
    this.authToken = authToken;
    this.createTime = createTime;
  }

  Auth(Integer userId, String authToken) {
    this.userId = userId;
    this.authToken = authToken;
    this.createTime = new Date(System.currentTimeMillis());
  }

  public Auth(Integer userId) {
    this.userId = userId;
    this.authToken = UUID.randomUUID().toString();
    this.createTime = new Date(System.currentTimeMillis());
  }

  public Integer getUserId() {
    return this.userId;
  }

  public String getAuthToken() {
    return authToken;
  }

  public Date getCreateTime() {
    return createTime;
  }

}
