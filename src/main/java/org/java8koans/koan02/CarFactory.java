package org.java8koans.koan02;

@FunctionalInterface
public interface CarFactory<C extends Car> {
    public C create(String manufacturer, int price);
}
