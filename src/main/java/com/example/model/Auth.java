package com.example.model;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Auth {
  
  private Integer userId;
  private String sessionKey;
  private Date createTime;

  Auth(Integer userId, String sessionKey, Date createTime) {
    this.userId = userId;
    this.sessionKey = sessionKey;
    this.createTime = createTime;
  }

  Auth(Integer userId, String sessionKey) {
    this.userId = userId;
    this.sessionKey = sessionKey;
    this.createTime = new Date(System.currentTimeMillis());
  }

  public Auth(Integer userId) {
    this.userId = userId;
    this.sessionKey = UUID.randomUUID().toString();
    this.createTime = new Date(System.currentTimeMillis());
  }

  public Integer getUserId() {
    return this.userId;
  }

  public String getSessionKey() {
    return sessionKey;
  }

  public Date getCreateTime() {
    return createTime;
  }

}
