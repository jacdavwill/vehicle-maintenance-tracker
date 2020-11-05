package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@AllArgsConstructor
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

  public Vehicle(int vehicleId, int userId, String nickname, String imageUrl, 
                 String registrationMonth, int mileage, String make, String model,
                 int year, String color, String transmissionType, String energyType) {
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

  public int getUserId() {
    return this.userId;
  }

  public int getVehicleId() {
    return this.vehicleId;
  }
  public Vehicle() {}

  public String getNickname() {
    return nickname;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getRegistrationMonth() {
    return registrationMonth;
  }

  public int getMileage() {
    return mileage;
  }

  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public String getColor() {
    return color;
  }

  public String getTransmissionType() {
    return transmissionType;
  }

  public String getEnergyType() {
    return energyType;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setVehicleId(int vehicleId) {
    this.vehicleId = vehicleId;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setRegistrationMonth(String registrationMonth) {
    this.registrationMonth = registrationMonth;
  }

  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setTransmissionType(String transmissionType) {
    this.transmissionType = transmissionType;
  }

  public void setEnergyType(String energyType) {
    this.energyType = energyType;
  }
}
