package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
  private int notificationId;
  private int maintItemId;
  private boolean pastDue;
  private String status;

  public Notification(int notificationId, int maintItemId, 
    boolean pastDue, String status) {
      this.notificationId = notificationId;
      this.maintItemId = maintItemId;
      this.pastDue = pastDue;
      this.status = status;
 }}
