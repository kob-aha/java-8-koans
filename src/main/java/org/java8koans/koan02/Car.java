package org.java8koans.koan02;

public abstract class Car {
    private CarType carType;
    private String manufacturer;

    protected Car(CarType carType, String manufacturer) {
        this.carType = carType;
        this.manufacturer = manufacturer;
    }

    public CarType getCarType() {
        return carType;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
