package com.example.car_management.Models;

public class CarType {
    private String TypeName;

    public CarType() {
    }

    public CarType(String typeName) {
        TypeName = typeName;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }
}
