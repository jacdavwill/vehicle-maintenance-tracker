package com.example.model;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class MaintItem {
  private int maintItemId;
  private int vehicleId;
  private int frequencyMonths;
  private int frequencyMiles;
  private String description;
  private LocalDate lastCompletedDate;
  private int lastCompletedMileage;

  public MaintItem(int maintItemId, int vehicleId, int freqMonths, 
    int freqMiles, String description, LocalDate lastCompletedDate,
    int lastCompletedMileage) {
      
      this.maintItemId = maintItemId;
      this.vehicleId = vehicleId;
      this.frequencyMonths = freqMonths;
      this.frequencyMiles = freqMiles;
      this.description = description;
      this.lastCompletedDate = lastCompletedDate;
      this.lastCompletedMileage = lastCompletedMileage;      
	}
}
