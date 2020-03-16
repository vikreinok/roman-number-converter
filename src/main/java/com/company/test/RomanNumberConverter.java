package com.company.test;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 *
 */
public class RomanNumberConverter {

    Map<String, Integer> hardcoded = ImmutableMap.<String, Integer>builder()
        .put("I", 1)
        .put("V", 5)
        .put("X", 10)
        .put("L", 50)
        .put("C", 100)
        .put("D", 500)
        .put("M", 1000)
        .build();


    public int convert(String input) {

        Integer result = 0;

        String[] romNumbers = input.trim().replace(" ", "").split("");

        Integer nextNr = 0;
        for (int i = 0; i < romNumbers.length; i++) {
            String romNrLetter = getAndValidateRomanNrLetter(romNumbers[i]);

            Integer currentNr = hardcoded.get(romNrLetter);

            if (i + 1 < romNumbers.length) {
                nextNr = hardcoded.get(getAndValidateRomanNrLetter(romNumbers[i + 1]));
                System.err.println("currentNr " + currentNr + " nextNr " + nextNr );

                if (currentNr >= nextNr) {
//                    if (i + 2 < romNumbers.length) {
//                        Integer nextNextNr = hardcoded.get(getAndValidateRomanNrLetter(romNumbers[i + 2]));
//                        System.err.println("currentNr " + currentNr + " nextNr " + nextNr + " nextNextNr " + nextNextNr);
//                        if (nextNr < nextNextNr) {
//                            result -= currentNr;
//                        }
//                    }
                    result += currentNr;
                } else {
                    result += (nextNr - currentNr);
                    System.err.println("result " + result);
                    i++;
                }
            } else {
                result += currentNr;
            }

            System.err.println("result " + result);
        }

        return result;
    }

    String getAndValidateRomanNrLetter(String romNumber) {
        String romNrLetter = romNumber;
        romNrLetter = romNrLetter.toUpperCase();
        if (!hardcoded.containsKey(romNrLetter)) {
            throw new IllegalArgumentException("Following roman letter not supported: " + romNrLetter);
        }
        return romNrLetter;
    }
}
