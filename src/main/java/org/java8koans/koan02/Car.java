package org.java8koans.koan02;

public abstract class Car {
    private CarType carType;
    private String manufacturer;
    private int price;

    protected Car(CarType carType, String manufacturer) {
        this(carType, manufacturer, 0);
    }

    protected Car(CarType carType, String manufacturer, int price) {
        this.carType = carType;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public CarType getCarType() {
        return carType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCsvString() {
        return getCarType() + ", " + getManufacturer() + ", " + getPrice();
    }

    @Override
    public String toString() {
        return "Car{" +
                "carType=" + carType +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
