package org.java8koans.koan02;

public class MiniCar extends Car {
    public MiniCar(String manufacturer) {
        this(manufacturer, 0);
    }

    public MiniCar(String manufacturer, int price) {
        super(CarType.MINI, manufacturer, price);
    }
}
