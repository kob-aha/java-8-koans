package org.java8koans.koan02;

public interface CarFactory<C extends Car> {
    public C create(String manufacturer);
}
