package org.java8koans.koan04;

import org.java8koans.entities.CarManufacturers;
import org.java8koans.koan02.Car;
import org.java8koans.koan02.LuxuryCar;
import org.java8koans.koan02.MiniCar;
import org.java8koans.koan02.RaceCar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains a main method used for generating cars data CSV used in {@link org.java8koans.koan04.Koan04}
 */
public class GenerateDataFile {

    public static void main(String[] args) throws IOException {

        List<Car> cars = Arrays.asList(new MiniCar(CarManufacturers.TOYOTA.name()),
                new LuxuryCar(CarManufacturers.TOYOTA.name()),
                new MiniCar(CarManufacturers.SUZUKI.name()),
                new LuxuryCar(CarManufacturers.JAGUAR.name()),
                new MiniCar(CarManufacturers.KIA.name()),
                new RaceCar(CarManufacturers.JAGUAR.name()),
                new RaceCar(CarManufacturers.FERRARI.name()));

        Path dataFile = FileSystems.getDefault().getPath("src" + File.separator + "test" + File.separator + "resources" +
            File.separator + "cars-data.csv");

        try (final BufferedWriter writer = Files.newBufferedWriter(dataFile)) {

            cars.forEach(car -> {
                try {
                    writer.append(car.getCsvString());
                    writer.newLine();
                } catch (IOException e) {
                    System.err.println("Error writing car " + car + " to file. Root cause: ");
                    e.printStackTrace();
                }
            });

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Finished generating data file");
    }
}