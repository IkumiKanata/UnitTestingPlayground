package com.luv2code.junitdemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    @Test
    void testAdd() {

        //create an object of the class we want to test
        DemoUtils demoUtils = new DemoUtils();

        //call the method we want to test
        assertEquals(7, demoUtils.add(5, 2), "5 + 2 should be 7");
        assertNotEquals(1, demoUtils.add(5, 2));
    }

    @Test
    void testNullAndNotNull() {
        //create an object of the class we want to test
        DemoUtils demoUtils = new DemoUtils();

        //call the method we want to test
        assertNull(demoUtils.checkNull(null),"Should be null");
        assertNotNull(demoUtils.checkNull("Hello"),"Should not be null");
    }


}
