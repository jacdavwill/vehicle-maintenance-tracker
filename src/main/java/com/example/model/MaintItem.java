package com.example.model;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintItem {
  private Integer maintItemId;
  private Integer vehicleId;
  private Integer frequencyMonths;
  private Integer frequencyMiles;
  private String description;
  private LocalDate lastCompletedDate;
  private Integer lastCompletedMileage;
}
