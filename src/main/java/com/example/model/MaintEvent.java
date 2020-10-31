package com.example.model;

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
  private Date eventDate;
  private Integer mileage;
  private String location;
  private String company;
  private String description;
}
