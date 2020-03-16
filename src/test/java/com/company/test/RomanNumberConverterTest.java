package com.company.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class RomanNumberConverterTest {


    private RomanNumberConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RomanNumberConverter();
    }

    @Nested
    @DisplayName("Single number")
    class SingleNumber {

        @ParameterizedTest(name = "Letter {0} is {1}")
        @CsvSource(value = {
            "I 1",
            "V 5",
            "X 10",
            "L 50",
            "C 100",
            "D 500",
            "M 1000",
        }, delimiter = ' ')
        void convert_validLetters(String input, int output) {
            assertEquals(output, converter.convert(input));
        }

        @Test
        void converter_invalidLetter() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                converter.convert("A");
            });
            assertEquals("Following roman letter not supported: A", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Two numbers")
    class TwoNumbers {

        @Test
        void convert_lastNumberSame() {
            assertEquals(2, converter.convert("II"));
        }

        @Test
        void convert_lastNumberBigger() {
            assertEquals(4, converter.convert("IV"));
        }

        @Test
        void convert_lastNumberSmaller() {
            assertEquals(6, converter.convert("VI"));
        }

    }

    @Nested
    @DisplayName("Three numbers")
    class ThreeNumbers {

        @Test
        void convert_same() {
            assertEquals(3, converter.convert("III"));
        }

        @Test
        void convert_16() {
            assertEquals(16, converter.convert("XVI"));
        }

        @Test
        void convert_12() {
            assertEquals(12, converter.convert("XII"));
        }

        @Test
        void convert_14() {
            assertEquals(14, converter.convert("XIV"));
        }

        @Test
        void convert_19() {
            assertEquals(19, converter.convert("XIX"));
        }

    }

    @Nested
    @DisplayName("Four numbers")
    class FourNumbers {

        @Test
        void convert_29() {
            assertEquals(29, converter.convert("XXIX"));
        }

    }

    @Nested
    @DisplayName("Complex cases")
    class ComplexCase {

        @Test
        void convert_1234() {
            assertEquals(1234, converter.convert("MCCXXXIV"));
        }

        @Test
        void convert_111() {
            assertEquals(1111, converter.convert("MCXI"));
        }

        @Test
        void convert_4999() {
            assertEquals(4999, converter.convert("MMMMCMXCIX"));
        }

    }
}
