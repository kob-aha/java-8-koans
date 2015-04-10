package org.java8koans.koan02;

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

    public static final String TOYOTA_MANUFACTURER = "Toyota";
    public static final String JAGUAR_MANUFACTURER = "Jaguar";

    @Test
    public void testConstructorReference() {
        CarFactory<Car> factory = null;

        // Set factory so it will be able to create a MiniCar instance. Use constructor reference.
        // For more info see "Method and Constructor References" section in http://winterbe.com/posts/2014/03/16/java-8-tutorial/

        // ------------ START EDITING HERE ----------------------
        factory = MiniCar::new;
        // ------------ STOP EDITING HERE  ----------------------

        assertNotNull("Factory should not be null", factory);

        Car miniCar = factory.create(TOYOTA_MANUFACTURER);

        assertEquals("Car type is different than expected",
                CarType.MINI,
                miniCar.getCarType());

        // Now set factory so it will be able to create a LuxuryCar instance.

        // ------------ START EDITING HERE ----------------------
        factory = LuxuryCar::new;
        // ------------ STOP EDITING HERE  ----------------------

        Car luxuryCar = factory.create(JAGUAR_MANUFACTURER);

        assertEquals("Car type is different than expected",
                CarType.LUXURY,
                luxuryCar.getCarType());
    }

    @Test
    public void testStaticReference() {
        Car carA = new MiniCar(TOYOTA_MANUFACTURER);
        Car carB = new LuxuryCar(JAGUAR_MANUFACTURER);

        Car[] carsArr = new Car[] {carB, carA};
        Comparator<Car> compareByType = null;

        // Init compareByType with a static method reference taken from CarComparators to compare cars by car type.

        // ------------ START EDITING HERE ----------------------
        compareByType = CarComparators::compareByType;
        // ------------ STOP EDITING HERE  ----------------------

        Arrays.sort(carsArr, compareByType);

        assertArrayEquals("Array is not sorted by car type as expected",
                new Car[] {carA, carB},
                carsArr);

        Comparator<Car> compareByManufacturer = null;
        carsArr = new Car[] {carA, carB};

        // Init compareByManufacturer with a static method reference taken from CarComparators to compare cars by manufacturer.

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

        // Init compareChain with previous comparator instance so that the array will be sorted by type followed by manufacturer.
        // In addition, null values should be placed first.
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

        Car c = new MiniCar(TOYOTA_MANUFACTURER);
        Supplier<String> carManufacturerExtractor = null;

        // Init carManufacturerExtractor with a method reference to get a car manufacturer

        // ------------ START EDITING HERE ----------------------
        carManufacturerExtractor = c::getManufacturer;
        // ------------ STOP EDITING HERE  ----------------------

        assertEquals("Manufacturer is different than expected",
                TOYOTA_MANUFACTURER,
                carManufacturerExtractor.get());
    }

    @Test
    public void testMethodTypeReference() {
        Car carA = new LuxuryCar(JAGUAR_MANUFACTURER);
        Car carB = new MiniCar(TOYOTA_MANUFACTURER);

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