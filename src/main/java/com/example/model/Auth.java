package com.example.model;

import java.util.Date;
import lombok.Data;

@Data
public class Auth {
  private int userId;
  private String sessionKey;
  private Date createTime;
}
