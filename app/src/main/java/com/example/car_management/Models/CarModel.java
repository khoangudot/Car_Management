package com.example.car_management.Models;

import java.math.BigDecimal;

public class CarModel {
    private int CarId;
    private String CarPlateNo;
    private String CarOwnerName;
    private String CarType;
    private BigDecimal CarPrice;
    private String CarDescription;

    public CarModel() {
    }

    public CarModel(int carId, String carPlateNo, String carOwnerName, String carType, BigDecimal carPrice, String carDescription) {
        CarId = carId;
        CarPlateNo = carPlateNo;
        CarOwnerName = carOwnerName;
        CarType = carType;
        CarPrice = carPrice;
        CarDescription = carDescription;
    }

    public int getCarId() {
        return CarId;
    }

    public void setCarId(int carId) {
        CarId = carId;
    }

    public String getCarPlateNo() {
        return CarPlateNo;
    }

    public void setCarPlateNo(String carPlateNo) {
        CarPlateNo = carPlateNo;
    }

    public String getCarOwnerName() {
        return CarOwnerName;
    }

    public void setCarOwnerName(String carOwnerName) {
        CarOwnerName = carOwnerName;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public BigDecimal getCarPrice() {
        return CarPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        CarPrice = carPrice;
    }

    public String getCarDescription() {
        return CarDescription;
    }

    public void setCarDescription(String carDescription) {
        CarDescription = carDescription;
    }
}
