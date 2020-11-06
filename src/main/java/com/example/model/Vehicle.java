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
  private int vehicleId;
  private int userId;
  private String nickname;
  private String imageUrl;
  private String registrationMonth;
  private int mileage;
  private String make;
  private String model;
  private int year;
  private String color;
  private String transmissionType;
  private String energyType;

  public Vehicle() {}

  public Vehicle(int userId, String nickname, String imageUrl, String registrationMonth,
                 int mileage, String make, String model, int year,
                 String color, String transmissionType, String energyType) {
    this.userId = userId;
    this.nickname=nickname;
    this.imageUrl = imageUrl;
    this.registrationMonth=registrationMonth;
    this.mileage=mileage;
    this.make=make;
    this.model=model;
    this.year=year;
    this.color=color;
    this.transmissionType=transmissionType;
    this.energyType=energyType;
  }

}
