package com.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaintEvent {
  private int maintEventId;
  private int maintItemId;
  private Date eventDate;
  private int mileage;
  private String location;
  private String company;
  private String description;
}
