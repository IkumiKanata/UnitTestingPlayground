package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@TestMethodOrder(MethodOrderer..class)
class DemoUtilsTest {

    //https://chatgpt.com/share/3cfb877e-ca06-4595-84c8-520f1548279c
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
    @DisplayName("Test for add method")
    void testEqualsAndNotEquals() {

        System.out.println("Running test for equals and not equals");

        //call the method we want to test
        assertEquals(7, demoUtils.add(5, 2), "5 + 2 should be 7");
        assertNotEquals(1, demoUtils.add(5, 2));
    }

    @Test
    @DisplayName("Test for multiply method")
    void testMultiply() {

        System.out.println("Running test for multiply");

        //call the method we want to test
        assertEquals(10, demoUtils.multiply(5, 2), "5 * 2 should be 10");
        assertNotEquals(1, demoUtils.multiply(5, 2));
    }

    @Test
//    @DisplayName("Test for checkNull method")
    void testNullAndNotNull() {

        System.out.println("Running test for null and not null");

//        //create an object of the class we want to test
//        DemoUtils demoUtils = new DemoUtils();

        //call the method we want to test
        assertNull(demoUtils.checkNull(null), "Should be null");
        assertNotNull(demoUtils.checkNull("Hello"), "Should not be null");
    }

    @Test
    @DisplayName("Test for SameAndNotSame method")
    void testSameAndNotSame() {
        String str = "luv2code";
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Should be same");
        assertNotSame(str, demoUtils.getAcademy(), "Should not be same");
    }

    @Test
    @DisplayName("Test for Greater method")
    void testIsGreater() {
        assertTrue(demoUtils.isGreater(5, 2), "5 is greater than 2");
        assertFalse(demoUtils.isGreater(2, 5), "2 is not greater than 5");
    }

    @Test
    void testArrayEquals() {
        String[] expected = {"A", "B", "C"};
        String[] expected2 = {"A", "B"};
        assertArrayEquals(expected, demoUtils.getFirstThreeLettersOfAlphabet(), "Should be equal");
    }

    @Test
    void testListEquals() {

        var list = List.of("luv", "2", "code");

        assertIterableEquals(demoUtils.getAcademyInList(), list, "Should be equal");
        assertLinesMatch(list, demoUtils.getAcademyInList());
    }

    @Test
    void testException() {
        assertThrows(Exception.class, () -> demoUtils.throwException(-1));
        assertDoesNotThrow(() -> demoUtils.throwException(1));
    }

    @Test
    void testTimeout() {
        assertTimeoutPreemptively(
                java.time.Duration.ofSeconds(5),
                () -> demoUtils.checkTimeout()
        );
    }


}
