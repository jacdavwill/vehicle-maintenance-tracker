package com.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Vehicle {
  private Integer vehicleId;
  private Integer userId;
  private String nickname;
  private String imageUrl;
  private String registrationMonth;
  private Integer mileage;
  private String make;
  private String model;
  private Integer year;
  private String color;
  private String transmissionType;
  private String energyType;

  public Vehicle(Integer userId, String nickname, String imageUrl, String registrationMonth,
                 Integer mileage, String make, String model, Integer year,
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

  public Integer getMileage() {
    return mileage;
  }

  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public Integer getYear() {
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

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setRegistrationMonth(String registrationMonth) {
    this.registrationMonth = registrationMonth;
  }

  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setYear(Integer year) {
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

  public Integer getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Integer vehicleId) {
    this.vehicleId = vehicleId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
