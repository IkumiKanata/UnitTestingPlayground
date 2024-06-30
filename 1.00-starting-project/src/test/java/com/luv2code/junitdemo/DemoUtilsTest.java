package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach() {
        //the reason why we are creating the object here is because we want to create a new object before each test method. This is to avoid the object being shared between test methods
        demoUtils = new DemoUtils();
        System.out.println("Before each test method");
    }
    @BeforeAll
    static void init() {
        System.out.println("Before all test methods");
    }

    @AfterEach
    void cleanUp() {
        System.out.println("After each test method");
    }

    @AfterAll
    static void cleanUpAll() {
        System.out.println("After all test methods");
    }

    @Test
    void testEqualsAndNotEquals() {

        System.out.println("Running test for equals and not equals");

        //call the method we want to test
        assertEquals(7, demoUtils.add(5, 2), "5 + 2 should be 7");
        assertNotEquals(1, demoUtils.add(5, 2));
    }

    @Test
    void testNullAndNotNull() {

        System.out.println("Running test for null and not null");

//        //create an object of the class we want to test
//        DemoUtils demoUtils = new DemoUtils();

        //call the method we want to test
        assertNull(demoUtils.checkNull(null), "Should be null");
        assertNotNull(demoUtils.checkNull("Hello"), "Should not be null");
    }


}
