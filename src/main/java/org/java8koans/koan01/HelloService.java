package org.java8koans.koan01;

public interface HelloService extends SpeakService {
    public final String DEFAULT_HELLO_SENTENCE = "Speaking from " + HelloService.class.getSimpleName();

    // ------------ START EDITING HERE ----------------------
    default String speak() {
        return DEFAULT_HELLO_SENTENCE;
    }
    // ------------ STOP EDITING HERE  ----------------------
}
