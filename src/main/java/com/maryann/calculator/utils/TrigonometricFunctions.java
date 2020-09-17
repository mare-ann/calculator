package com.maryann.calculator.utils;

public class TrigonometricFunctions {

    public static double sineFunction (double valueDegrees) {
        double resultSinValue;
        // convert degrees to radians
        double radiansSin = Math.toRadians(valueDegrees);
        // sin() method to get the sine value
        resultSinValue = Math.sin(radiansSin);
        return resultSinValue;
    }

    public static double cosineFunction (double valueDegrees) {
        double resultCosValue;
        // convert degrees to radians
        double radiansCos = Math.toRadians(valueDegrees);
        // cos() method to get the cosine value
        resultCosValue = Math.cos(radiansCos);
        return resultCosValue;
    }

    public static double tangentFunction (double valueDegrees) {
        double resultTanValue;
        // convert degrees to radians
        double radiansTan = Math.toRadians(valueDegrees);
        // tan() method to get the tangent value
        resultTanValue = Math.tan(radiansTan);
        return resultTanValue;
    }

    public static double cottangentFunction (double valueDegrees) {
        double resultCotValue;
        resultCotValue = 1/tangentFunction(valueDegrees);
        return resultCotValue;
    }








}
