package org.java8koans.koan03;

import org.java8koans.entities.CarManufacturers;
import org.java8koans.koan02.Car;
import org.java8koans.koan02.LuxuryCar;
import org.java8koans.koan02.MiniCar;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * Koan03 - Lambda expression
 *
 * Resource list:
 *   http://viralpatel.net/blogs/lambda-expressions-java-tutorial/
 *   http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 *
 */
public class Koan03 {

    private Car carA = new MiniCar(CarManufacturers.TOYOTA.name());
    private Car carB = new LuxuryCar(CarManufacturers.JAGUAR.name());

    // Lambda expressions let you provide the implementation of the abstract method of a functional interface directly
    // inline and treat the whole expression as an instance of a functional interface.
    // In the test methods below you will provide implementations to various functional interfaces added to java 8.
    // To read more on java 8 functional interface go to: https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html.

    @Test
    public void testLambdaSupplier() {
        Supplier<Car> carSupplier = null;
        final Car suppliedCar = new MiniCar(CarManufacturers.TOYOTA.name());

        // Assign carSupplier a Lambda expression so that suppliedCar instance will be returned

        // ------------ START EDITING HERE ----------------------
        carSupplier = () -> suppliedCar;
        // ------------ STOP EDITING HERE  ----------------------

        Car returnedCar = carSupplier.get();

        assertTrue("Lambda expression didn't return the expected value", returnedCar == suppliedCar);
    }

    @Test
    public void testLambdaPredicate() {
        Predicate<Car> isToyota = null;

        final Car toyotaMini = new MiniCar(CarManufacturers.TOYOTA.name());
        final Car toyotaLuxury = new LuxuryCar(CarManufacturers.TOYOTA.name());
        final Car jaguar = new LuxuryCar(CarManufacturers.JAGUAR.name());

        // Assign isToyota a Lambda expression that will return true in case the car manufacturer is toyota
        // (you can use CarManufacturers.TOYOTA.name())

        // ------------ START EDITING HERE ----------------------
        isToyota = (car) -> car.getManufacturer() == CarManufacturers.TOYOTA.name();
        // ------------ STOP EDITING HERE  ----------------------

        assertTrue("Lambda expression didn't return true as expected",
                isToyota.test(toyotaMini));
        assertTrue("Lambda expression didn't return true as expected",
                isToyota.test(toyotaLuxury));
        assertFalse("Lambda expression didn't return false as expected",
                isToyota.test(jaguar));
    }

    @Test
    public void testLambdaBiFunction() {
        BiFunction<Car, Car, Integer> manufacturerComparator = null;

        // Assign manufacturerComparator a Lambda expression that will act as a car comparator comparing two car
        // instance by their manufacturer. The expression should return any number bigger than zero, zero or any number smaller than zero
        // in case the first car manufacturer is larger than, equal to or smaller than the second car manufacturer respectively.
        // The implementation should be similar to CarComparators.compareByManufacturer.

        // ------------ START EDITING HERE ----------------------
        manufacturerComparator = (c1, c2) -> c1.getManufacturer().compareTo(c2.getManufacturer());
        // ------------ STOP EDITING HERE  ----------------------

        assertTrue("Using BiFunction lambda expression didn't return the expected value",
                manufacturerComparator.apply(carA, carB) > 0);

        // The above lambda expression can be easily replaced by a method reference.
        // Try to assign manufacturerComparator a method reference to compareByManufacturer.compareByManufacturer
        // and see for yourself.
        // It is usually easier to replace lambda expression with method reference when you want to debug your code.


        Comparator<Car> compareByManufacturer = null;

        // The same lambda expression can also be used as an implementation to a Comparator.
        // Assign compareByManufacturer with the same lambda expression written above and see for yourself.

        // ------------ START EDITING HERE ----------------------
        compareByManufacturer = (c1, c2) -> c1.getManufacturer().compareTo(c2.getManufacturer());
        // ------------ STOP EDITING HERE  ----------------------

        Car[] carsArr = new Car[] {carA, carB};
        Arrays.sort(carsArr, compareByManufacturer);

        assertArrayEquals("Array is not sorted by manufacturer as expected",
                new Car[]{carB, carA},
                carsArr);
    }

    @Test
    public void testLambdaNoParamsVoidReturn() {
        Runnable lambdaNoParamVoidReturn = null;

        // Assign lambdaNoParamVoidReturn a lambda expression that accepts no parameters and return nothing (void).
        // Please note that we use Runnable here just to define a functional interface in this form and nothing more.
        // There is no use of concurrency here.

        // ------------ START EDITING HERE ----------------------
        lambdaNoParamVoidReturn = () -> {};
        // ------------ STOP EDITING HERE  ----------------------

        assertNotNull("Functional interface should be assigned with a value", lambdaNoParamVoidReturn);
    }

    @Test
    public void testLambdaMultipleLines() {
        BiConsumer<Car, Car> carsConsumer = null;
        final CarService carService = mock(CarService.class);

        // Assign carConsumer a lambda expression that will call CarService twice with the expression parameters
        // (every call should use a different parameter).

        // ------------ START EDITING HERE ----------------------
        carsConsumer = (c1, c2) -> {
            carService.consumeCar(c1);
            carService.consumeCar(c2);
        };
        // ------------ STOP EDITING HERE  ----------------------

        carsConsumer.accept(carA, carB);

        verify(carService, times(1)).consumeCar(carA);
        verify(carService, times(1)).consumeCar(carB);
    }
}
