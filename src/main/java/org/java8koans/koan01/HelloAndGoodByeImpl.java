package org.java8koans.koan01;

public class HelloAndGoodByeImpl implements GoodbyeService, HelloService {

    // ------------ START EDITING HERE ----------------------
    @Override
    public String speak() {
        return GoodbyeService.super.speak() + " " + HelloService.super.speak();
    }
    // ------------ STOP EDITING HERE  ----------------------
}
