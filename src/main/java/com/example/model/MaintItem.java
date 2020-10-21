package com.example.model;

import java.util.Date;
import lombok.Data;

@Data
public class MaintItem {
  private int maintItemId;
  private int vehicleId;
  private int frequencyMonths;
  private int frequencyMiles;
  private String description;
  private Date lastCompletedDate;
  private int lastCompletedMileage;
}
