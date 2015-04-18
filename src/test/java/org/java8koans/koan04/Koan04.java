package org.java8koans.koan04;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.java8koans.entities.CarManufacturers;
import org.java8koans.entities.Cars;
import org.java8koans.koan02.Car;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Koan04 - Streams
 *
 * Resource list:
 *
 *   http://www.javacodegeeks.com/2014/05/java-8-features-tutorial.html#streams
 *   https://docs.oracle.com/javase/tutorial/collections/streams/index.html
 *   http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
 *   https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
 */
public class Koan04 {

    private static List<Car> carList = null;

    /**
     * This method loads all the car used in this test class from a CSV file.
     * The method is an example of how to use Streams for loading data from files and not just for collections.
     *
     * @throws Exception in case the was any exception loading the cars CSV file.
     */
    @BeforeClass
    public static void loadData() throws Exception {

        Path carsDataPath = Paths.get(Koan04.class.getClassLoader().getResource("cars-data.csv").toURI());

        Assert.assertNotNull("Error loading cars data. Path is null.", carsDataPath);

        // Reads all the CSV lines into memory as a Stream
        try (Stream<String> lines = Files.lines(carsDataPath, StandardCharsets.UTF_8)) {

            // Converts the csv lines stream to a simple list of Car instances by using Cars.loadFromCSVLine.
            // As you can see, we use Optional which is another new feature that will be discussed in a later Koan.
            carList = lines.map(Cars::loadFromCSVLine).
                    filter(optional -> optional.isPresent()).
                    map(optional -> optional.get()).
                    collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Error loading cars data file. Error: " + e.getMessage());
        }

        Assert.assertNotNull("Cars list is null", carList);
        Assert.assertNotEquals("Cars list is empty", 0, carList.size());
    }

    @Test
    public void getDistinctEntries() {

        // Stream is a way for us to chain multiple operations together.
        // Please note that as long as we call methods that return a Stream value there is no real computation.

        Stream<String> carManufacturersStream = null;

        // Initialize carManufacturersStream so that it will return distinct car manufacturer names.
        // Hint: you should start with "carList.stream()" and than use Stream's map and distinct methods.
        // More info here:
        //      https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#map-java.util.function.Function-
        //      https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#distinct--

        // ------------ START EDITING HERE ----------------------
        carManufacturersStream = carList.stream().
                map(car -> car.getManufacturer()).
                distinct();
        // ------------ STOP EDITING HERE  ----------------------

        Assert.assertNotNull("Manufacturers stream should not be null", carManufacturersStream);

        // Calling count starts the "real" computation and execute our chained operations.
        Assert.assertEquals("Distinct manufacturers stream contains duplicate values.", 5, carManufacturersStream.count());
    }

    @Test
    public void filterCarsByManufacturer() {

        Stream<Car> toyotaCarsStream = carList.stream();

        // Initialize toyotaCarsStream with a stream containing cars manufactured by Toyota
        // Hint: use filter method (https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-)

        // ------------ START EDITING HERE ----------------------
        toyotaCarsStream = toyotaCarsStream.filter(car -> CarManufacturers.TOYOTA.name().equals(car.getManufacturer()));
        // ------------ STOP EDITING HERE  ----------------------


        List<Car> toyotaCarsList = toyotaCarsStream.collect(Collectors.toList());
        Assert.assertEquals("Toyota cars stream does not contain the expected amount of element",
                2, toyotaCarsList.size());

        // The following variable iterates over all the cars in toyotaCarsStream and validates their manufacturer is Toyota.
        // REMARK: I needed to make sure the number of elements match in addition to the following verification, in order to
        // make sure toyotaCarsStream is not empty, as if it does allMatch will return true although the stream does not contain
        // the expected elements.
        Boolean isAllToyota = toyotaCarsList.stream().
                map(car -> CarManufacturers.TOYOTA.name().equals(car.getManufacturer())).
                allMatch(isToyota -> isToyota);

        Assert.assertTrue("Toyota cars stream contains cars from other manufacturers", isAllToyota);
    }

    @Test
    public void groupCarsByManufacturer() {


        Map<String, List<Car>> groupByManufacturers = null;

        // Initialize groupByManufacturers with a map from car manufacturer to his matching cars list.
        // Hint: use one of the collectors from https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html

        // ------------ START EDITING HERE ----------------------
        groupByManufacturers = carList.stream().
                collect(Collectors.groupingBy(Car::getManufacturer));
        // ------------ STOP EDITING HERE  ----------------------

        Assert.assertNotNull("Map should not be null", groupByManufacturers);

        Multimap<String, Car> expectedMap = ArrayListMultimap.create();

        // Create expected map using java7 style
        for (Car car : carList) {
            expectedMap.put(car.getManufacturer(), car);
        }

        boolean allMatchExpected = groupByManufacturers.entrySet().stream().
                allMatch(entry -> {
                    final String manufacturer = entry.getKey();
                    boolean allEntriesExist = entry.getValue().stream().
                            allMatch(val -> expectedMap.containsEntry(manufacturer, val));

                    return allEntriesExist;
                });

        Assert.assertTrue("There are elements that do not exist in the expected map", allMatchExpected);

    }

    @Test
    public void intStreamIntro() {
        // In this test method we will use IntStream practice some of the Stream operations.
        // For more info see https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html

        IntStream range = null;
        int sumTo1000 = 0;

        // Initialize range with a stream of number between 1 and 1000 (inclusive)

        // ------------ START EDITING HERE ----------------------
        range = IntStream.range(1, 1001);
        // ------------ STOP EDITING HERE  ----------------------

        Assert.assertNotNull("Range should not be null", range);

        Integer[] expectedArray = new Integer[1000];
        for (int i = 1; i <= expectedArray.length; i++) {
            expectedArray[i-1] = new Integer(i);
        }

        Object[] actualRangeArray = range.boxed().toArray();
        Assert.assertArrayEquals("Range does not match expected", expectedArray, actualRangeArray);

        // Calculate the sum of numbers from 1 to 1000 and initialize sumTo1000 with the result.
        // Make sure to reinitialize range as it is already been used.
        // Hint: use IntStream.range

        // ------------ START EDITING HERE ----------------------
        range = IntStream.range(1, 1001);
        sumTo1000 = range.sum();
        // ------------ STOP EDITING HERE  ----------------------

        Assert.assertEquals("Sum does not match expected", 500500, sumTo1000);
    }

}