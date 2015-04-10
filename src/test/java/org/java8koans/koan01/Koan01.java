package org.java8koans.koan01;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Koan01 - Default methods
 *
 * Resource list:
 *   https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
 *   http://www.javacodegeeks.com/2014/05/java-8-features-tutorial.html#Interface_Default
 */
public class Koan01 {

    public static final String SPEAK_METHOD_NAME = "speak";

    private SpeakService helloAndGoodByeService;
    private SpeakService speakService;
    private GoodbyeService goodbyeService;

    @Before
    public void init() {
        helloAndGoodByeService = new HelloAndGoodByeImpl();
        goodbyeService = new GoodbyeServiceImpl();
        speakService = new SpeakServiceImpl();
    }

    @Test
    public void testDefaultSpeak() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Add a default method called "speak" with no parameters to SpeakService.
        // The method should simply return the value of SpeakService.DEFAULT_SPEAK_SENTENCE

        Method speak = SpeakService.class.getMethod(SPEAK_METHOD_NAME, new Class[]{}); // Making sure the method exists in the right service
        speak = speakService.getClass().getMethod(SPEAK_METHOD_NAME, new Class[]{});

        assertTrue(String.format("Method %s is not a default method", SPEAK_METHOD_NAME), speak.isDefault());

        Object speakValue = speak.invoke(speakService, new Object[]{});

        assertTrue(String.format("Method %s does not return the expected type", SPEAK_METHOD_NAME), speakValue instanceof String);

        assertEquals(String.format("Method %s does not return the expected value", SPEAK_METHOD_NAME),
                SpeakService.DEFAULT_SPEAK_SENTENCE,
                speakValue);
    }

    @Test
    public void testMultipleDefaultSpeak() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Add another default method called "speak" with no parameters to GoodbyeService.
        // The method should simply return the value of GoodbyeService.DEFAULT_GOODBYE_SENTENCE.

        Method speak = GoodbyeService.class.getMethod(SPEAK_METHOD_NAME, new Class[]{}); // Making sure the method exists in the right service
        speak = goodbyeService.getClass().getMethod(SPEAK_METHOD_NAME, new Class[]{});

        assertTrue(String.format("Method %s is not a default method", SPEAK_METHOD_NAME), speak.isDefault());

        Object speakValue = speak.invoke(goodbyeService, new Object[]{});

        assertTrue(String.format("Method %s does not return the expected type", SPEAK_METHOD_NAME), speakValue instanceof String);

        assertEquals(String.format("Method %s does not return the expected value", SPEAK_METHOD_NAME),
                GoodbyeService.DEFAULT_GOODBYE_SENTENCE,
                speakValue);

        // When a class inherits different implementation of a default method in the interfaces that it extends, the compiler
        // with usually choose the one that is closer to the class (GoodbyeService.speak is taken as it is closer to GoodbyeServiceImpl than SpeakService).
        //
        // IMPORTANT: The above assumes the interfaces are in different inheritance levels in the class hierarchy. See the following test
        // for more info in case both interfaces are in the same level.
    }

    @Test
    public void testMultipleInheritanceIssue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Add a default method called "speak" with no parameters to HelloService.
        // The method should simply return the value of HelloService.DEFAULT_HELLO_SENTENCE.
        // Try to compile the code and see what happens.
        //
        // info: http://www.programcreek.com/2014/12/default-methods-in-java-8-and-multiple-inheritance/
        //
        // Solve the problem by adding a method in HelloAndGoodByeImpl that simply return a concatenation of both
        // GoodbyeService.speak as well as HelloService.speak. The return value should be:
        //
        //      GoodbyeService.DEFAULT_GOODBYE_SENTENCE + " " + HelloService.DEFAULT_HELLO_SENTENCE

        String expectedValue = GoodbyeService.DEFAULT_GOODBYE_SENTENCE + " " + HelloService.DEFAULT_HELLO_SENTENCE;

        Method speak = helloAndGoodByeService.getClass().getMethod(SPEAK_METHOD_NAME, new Class[]{});

        assertFalse(String.format("Method %s is a default method", SPEAK_METHOD_NAME), speak.isDefault());

        Object speakValue = speak.invoke(helloAndGoodByeService, new Object[]{});

        assertTrue(String.format("Method %s does not return the expected type", SPEAK_METHOD_NAME), speakValue instanceof String);

        assertEquals(String.format("Method %s does not return the expected value", SPEAK_METHOD_NAME),
                expectedValue,
                speakValue);

    }
}
