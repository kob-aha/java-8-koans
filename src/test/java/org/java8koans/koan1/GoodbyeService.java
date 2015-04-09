package org.java8koans.koan1;

public interface GoodbyeService extends SpeakService {
    public final String DEFAULT_GOODBYE_SENTENCE = "Speaking from " + GoodbyeService.class.getSimpleName();

    // ------------ START EDITING HERE ----------------------
    default String speak() {
        return DEFAULT_GOODBYE_SENTENCE;
    }
    // ------------ STOP EDITING HERE  ----------------------
}
