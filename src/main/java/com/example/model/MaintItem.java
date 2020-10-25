package com.example.model;

import java.util.Date;
import java.util.Random;

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

  public MaintItem(int vehicleId, int frequencyMonths, int frequencyMiles,
    String description, Date lastCompletedDate, int lastCompletedMileage) {
      this.maintItemId = new Random().nextInt(Integer.MAX_VALUE);
      this.vehicleId = vehicleId;
      this.frequencyMonths = frequencyMonths;
      this.frequencyMiles = frequencyMiles;
      this.description = description;
      this.lastCompletedDate = lastCompletedDate;
      this.lastCompletedMileage = lastCompletedMileage;
  }

  public void setId(int id) {
    this.maintItemId = id;
  }

}
