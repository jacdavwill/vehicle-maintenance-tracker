package com.example.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintEvent {
  private int maintEventId;
  private int maintItemId;
  private Date eventDate;
  private int mileage;
  private String location;
  private String company;
  private String description;

  public MaintEvent(int maintEventId, int maintItemId, Date eventDate, 
    int mileage, String location, String company, String description) {
      this.maintEventId = maintEventId;
      this.maintItemId = maintItemId;
      this.eventDate = eventDate;
      this.mileage = mileage;
      this.location = location;
      this.company = company;
      this.description = description;
  }
}
