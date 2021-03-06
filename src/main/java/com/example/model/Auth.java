package com.example.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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

  public Auth() {
  }

  public Auth(int userId, String authToken) {
    this.userId = userId;
    this.authToken = authToken;
    this.createTime = new Date(System.currentTimeMillis()); // TODO: Change to correct date format
  }

  public int getUserId() { // TODO: This shouldn't be neccessary. Not sure why lombok isn't working for me
    return this.userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Auth auth = (Auth) o;
    return userId == auth.userId &&
            Objects.equals(authToken, auth.authToken);
  }
}
