package com.example.model;

import java.util.Date;
import lombok.Data;

@Data
public class Auth {
  
  private int userId;
  private String sessionKey;
  private Date createTime;

  Auth(int userId, String sessionKey, Date createTime) {
    this.userId = userId;
    this.sessionKey = sessionKey;
    this.createTime = createTime;
  }

  Auth(int userId, String sessionKey) {
    this.userId = userId;
    this.sessionKey = sessionKey;
    this.createTime = new Date(System.currentTimeMillis());
  }

  public int getUserId() {
    return this.userId;
  }

  public String getSessionKey() {
    return sessionKey;
  }

  public Date getCreateTime() {
    return createTime;
  }

}
