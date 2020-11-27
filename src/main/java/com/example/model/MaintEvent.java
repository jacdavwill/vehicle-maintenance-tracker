package com.example.model;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintEvent {
  private Integer maintEventId;
  private Integer maintItemId;
  private LocalDate eventDate;
  private Integer mileage;
  private String location;
  private String company;
  private String description;
}
