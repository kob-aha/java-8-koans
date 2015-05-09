package org.java8koans.koan02;

public class LuxuryCar extends Car {
    public LuxuryCar(String manufacturer) {
        this(manufacturer, 0);
    }

    public LuxuryCar(String manufacturer, int price) {
        super(CarType.LUXURY, manufacturer, price);
    }
}
