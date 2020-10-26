package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Notification {
  private int notificationId;
  private int maintItemId;
  private boolean pastDue;
  private String status;
}
