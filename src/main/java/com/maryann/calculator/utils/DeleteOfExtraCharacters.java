package com.maryann.calculator.utils;

public class DeleteOfExtraCharacters {

    public static String cleanSpaces (String expression) {
        String expressionAfterClean = expression.replaceAll("\\s+", "");
        return expressionAfterClean;
    }
}
