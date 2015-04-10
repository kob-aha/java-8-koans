package org.java8koans.koan02;

public final class CarComparators {
    public static int compareByType(Car carA, Car carB) {
        return carA.getCarType().compareTo(carB.getCarType());
    }

    public static int compareByManufacturer(Car carA, Car carB) {
        return carA.getManufacturer().compareTo(carB.getManufacturer());
    }
}
