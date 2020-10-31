package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
  private Integer notificationId;
  private Integer maintItemId;
  private Boolean pastDue;
  private String status;
}
