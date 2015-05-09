package org.java8koans.koan02;

public class RaceCar extends Car {
    public RaceCar(String manufacturer) {
        this(manufacturer, 0);
    }

    public RaceCar(String manufacturer, int price) {
        super(CarType.RACE, manufacturer, price);
    }
}
