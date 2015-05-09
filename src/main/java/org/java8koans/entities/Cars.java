package org.java8koans.entities;

import org.java8koans.koan02.*;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class contains static methods for operating on {@link org.java8koans.koan02.Car} objects.
 */
public final class Cars {

    public static final String CSV_SEPARATOR = ",";

    private static final Map<CarType, CarFactory<? extends Car>> carFactories;

    static {
        carFactories = new EnumMap<>(CarType.class);

        carFactories.put(CarType.LUXURY, LuxuryCar::new);
        carFactories.put(CarType.MINI, MiniCar::new);
        carFactories.put(CarType.RACE, RaceCar::new);
    }

    public static Optional<Car> loadFromCSVLine(String line) {

        Optional<Car> retVal = Optional.empty();
        String[] csvData = line.split(CSV_SEPARATOR);

        if (csvData != null && csvData.length > 1) {
            String carType = csvData[0].trim();
            String carManufacturer = csvData[1].trim();
            int carPrice = Integer.parseInt(csvData[2].trim());

            CarFactory<? extends Car> carFactory = carFactories.get(CarType.valueOf(carType));
            retVal = Optional.of(carFactory.create(carManufacturer, carPrice));
        }

        return retVal;
    }

}
