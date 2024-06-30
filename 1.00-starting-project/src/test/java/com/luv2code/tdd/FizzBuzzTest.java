package com.luv2code.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {


    @Test
    @DisplayName("Test for number 3")
    void testForDivisibleBy3() {
        String result = FizzBuzz.play(3);
        assertEquals("Fizz", result);
    }

    @Test
    @DisplayName("Test for number 5")
    void testForDivisibleBy5() {
        String result = FizzBuzz.play(5);
        assertEquals("Buzz", result);
    }

    @Test
    @DisplayName("Test for number 15")
    void testForDivisibleBy3And5() {
        String result = FizzBuzz.play(15);
        assertEquals("FizzBuzz", result);
    }

    @Test
    @DisplayName("Test for number 7")
    void testForNumberNotDivisibleBy3Or5() {
        String result = FizzBuzz.play(7);
        assertEquals("7", result);
    }

    @Test
    @DisplayName("Test for number 0")
    void testForNumberZero() {
        String result = FizzBuzz.play(0);
        assertEquals("FizzBuzz", result);
    }

    @Test
    @DisplayName("Test for number -3")
    void testForNegativeNumberDivisibleBy3() {
        String result = FizzBuzz.play(-3);
        assertEquals("Fizz", result);
    }

    @Test
    @DisplayName("Test for number -5")
    void testForNegativeNumberDivisibleBy5() {
        String result = FizzBuzz.play(-5);
        assertEquals("Buzz", result);
    }

    @Test
    @DisplayName("Test for number -15")
    void testForNegativeNumberDivisibleBy3And5() {
        String result = FizzBuzz.play(-15);
        assertEquals("FizzBuzz", result);
    }

    @Test
    @DisplayName("Test for number -7")
    void testForNegativeNumberNotDivisibleBy3Or5() {
        String result = FizzBuzz.play(-7);
        assertEquals("-7", result);
    }

    @DisplayName("Testing for small-test-data")
    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    void testForSmallTestData(int value, String expected) {
        assertEquals(expected, FizzBuzz.play(value));
    }

    @DisplayName("Testing for medium-test-data")
    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    void testForMediumTestData(int value, String expected) {
        assertEquals(expected, FizzBuzz.play(value));
    }

    @DisplayName("Testing for large-test-data")
    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    void testForLargeTestData(int value, String expected) {
        assertEquals(expected, FizzBuzz.play(value));
    }
}
