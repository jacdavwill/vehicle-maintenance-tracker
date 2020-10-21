package com.example.model;

import lombok.Data;

@Data
public class Notification {
  private int notificationId;
  private int maintItemId;
  private boolean pastDue;
  private String status;
}
