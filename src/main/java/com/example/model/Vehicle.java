package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
  private Integer vehicleID;
  private Integer userID;
  private String nickname;
  private String imageURL;
  private String registrationMonth;
  private Integer mileage;
  private String make;
  private String model;
  private Integer year;
  private String color;
  private String transmissionType;
  private String energyType;
}
