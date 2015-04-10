package org.java8koans.koan01;

public interface SpeakService {
    public final String DEFAULT_SPEAK_SENTENCE = "Speaking from " + SpeakService.class.getSimpleName();

    // ------------ START EDITING HERE ----------------------
    default String speak() {
        return DEFAULT_SPEAK_SENTENCE;
    }
    // ------------ STOP EDITING HERE  ----------------------
}
