package org.java8koans.koan02;

import org.java8koans.entities.CarManufacturers;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * Koan02 - Method reference
 *
 * Resource list:
 *   https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html (ignore the lambda expression part for now)
 *   http://www.javacodegeeks.com/2014/05/java-8-features-tutorial.html#method_references
 */
public class Koan02 {

    @Test
    public void testConstructorReference() {
        CarFactory<Car> factory = null;

        // Assign factory a value so it will be able to create a MiniCar instance. Use constructor reference.
        // For more info see "Method and Constructor References" section in http://winterbe.com/posts/2014/03/16/java-8-tutorial/

        // ------------ START EDITING HERE ----------------------
        factory = MiniCar::new;
        // ------------ STOP EDITING HERE  ----------------------

        assertNotNull("Factory should not be null", factory);

        Car miniCar = factory.create(CarManufacturers.TOYOTA.name(), 0);

        assertEquals("Car type is different than expected",
                CarType.MINI,
                miniCar.getCarType());

        // Now Assign factory a value so it will be able to create a LuxuryCar instance.

        // ------------ START EDITING HERE ----------------------
        factory = LuxuryCar::new;
        // ------------ STOP EDITING HERE  ----------------------

        Car luxuryCar = factory.create(CarManufacturers.JAGUAR.name(), 0);

        assertEquals("Car type is different than expected",
                CarType.LUXURY,
                luxuryCar.getCarType());
    }

    @Test
    public void testStaticReference() {
        Car carA = new MiniCar(CarManufacturers.TOYOTA.name());
        Car carB = new LuxuryCar(CarManufacturers.JAGUAR.name());

        Car[] carsArr = new Car[] {carB, carA};
        Comparator<Car> compareByType = null;

        // Assign compareByType a static method reference taken from CarComparators to compare cars by car type.

        // ------------ START EDITING HERE ----------------------
        compareByType = CarComparators::compareByType;
        // ------------ STOP EDITING HERE  ----------------------

        Arrays.sort(carsArr, compareByType);

        assertArrayEquals("Array is not sorted by car type as expected",
                new Car[] {carA, carB},
                carsArr);

        Comparator<Car> compareByManufacturer = null;
        carsArr = new Car[] {carA, carB};

        // Assign compareByManufacturer a static method reference taken from CarComparators to compare cars by manufacturer.

        // ------------ START EDITING HERE ----------------------
        compareByManufacturer = CarComparators::compareByManufacturer;
        // ------------ STOP EDITING HERE  ----------------------

        Arrays.sort(carsArr, compareByManufacturer);

        assertArrayEquals("Array is not sorted by manufacturer as expected",
                new Car[] {carB, carA},
                carsArr);

        Comparator<Car> compareChain = null;


        Car carC = new LuxuryCar("Lexus");

        carsArr = new Car[] {carC, carB, carA, null};

        // Assign compareChain a value so that the array will be sorted by type followed by manufacturer.
        // Use the previous method reference and also make sure null values will be placed first (without adding null validations).
        //
        // For more info see:
        //      https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
        //      https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#nullsFirst-java.util.Comparator-

        // ------------ START EDITING HERE ----------------------
        compareChain = Comparator.nullsFirst(compareByType).thenComparing(compareByManufacturer);
        // ------------ STOP EDITING HERE  ----------------------

        Arrays.sort(carsArr, compareChain);

        assertArrayEquals("Array is not sorted as expected",
                new Car[] {null, carA, carB, carC},
                carsArr);
    }

    @Test
    public void testInstanceReference() {

        // Test method resource list:
        //      http://www.javacodegeeks.com/2013/03/introduction-to-functional-interfaces-a-concept-recreated-in-java-8.html
        //      https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html

        Car c = new MiniCar(CarManufacturers.TOYOTA.name());
        Supplier<String> carManufacturerExtractor = null;

        // Init carManufacturerExtractor with a method reference to get a car manufacturer

        // ------------ START EDITING HERE ----------------------
        carManufacturerExtractor = c::getManufacturer;
        // ------------ STOP EDITING HERE  ----------------------

        assertEquals("Manufacturer is different than expected",
                CarManufacturers.TOYOTA.name(),
                carManufacturerExtractor.get());
    }

    @Test
    public void testMethodTypeReference() {
        Car carA = new LuxuryCar(CarManufacturers.JAGUAR.name());
        Car carB = new MiniCar(CarManufacturers.TOYOTA.name());

        String[] manufacturers = new String[] {carB.getManufacturer(), null, carA.getManufacturer()};
        Comparator<String> manufactureComparator = null;

        // Sort the manufacturers array using String method reference. null values should be placed last.

        // ------------ START EDITING HERE ----------------------
        manufactureComparator = Comparator.nullsLast(String::compareTo);
        // ------------ STOP EDITING HERE  ----------------------

        Arrays.sort(manufacturers, manufactureComparator);

        assertArrayEquals("Array is not sorted as expected",
                new String[] {carA.getManufacturer(), carB.getManufacturer(), null},
                manufacturers);
    }
}