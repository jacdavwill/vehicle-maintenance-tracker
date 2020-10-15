package com.example.model;

public class Vehicle {
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

  public Vehicle(String nickname, String imageURL, String registrationMonth,
                 Integer mileage, String make, String model, Integer year,
                 String color, String transmissionType, String energyType) {
    this.nickname=nickname;
    this.imageURL=imageURL;
    this.registrationMonth=registrationMonth;
    this.mileage=mileage;
    this.make=make;
    this.model=model;
    this.year=year;
    this.color=color;
    this.transmissionType=transmissionType;
    this.energyType=energyType;
  }

  public String getNickname() {
    return nickname;
  }

  public String getImageURL() {
    return imageURL;
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
}
