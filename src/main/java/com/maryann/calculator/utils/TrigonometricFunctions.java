package com.maryann.calculator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class calculates trigonometric functions
 * and return double number
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */

public class TrigonometricFunctions {

    private static final Logger logger = LoggerFactory.getLogger(TrigonometricFunctions.class);

    public static double sineFunction (double valueDegrees) {
        logger.trace("Start function TrigonometricFunctions.sineFunction()");
        double resultSinValue;
        // convert degrees to radians
        logger.debug("Getting convert sin{} to radians", valueDegrees);
        double radiansSin = Math.toRadians(valueDegrees);
        logger.debug("Got converted sin{} to radians value: {}", valueDegrees, radiansSin);
        // sin() method to get the sine value
        logger.debug("Getting the sin{} value ", radiansSin);
        resultSinValue = Math.sin(radiansSin);
        resultSinValue = roundTo15Digits(resultSinValue);
        logger.debug("sin{} = {}", radiansSin, resultSinValue);
        logger.trace("End function TrigonometricFunctions.sineFunction()");
        return resultSinValue;
    }

    public static double cosineFunction (double valueDegrees) {
        logger.trace("Start function TrigonometricFunctions.cosineFunction()");
        double resultCosValue;
        // convert degrees to radians
        logger.debug("Getting convert cos{} to radians", valueDegrees);
        double radiansCos = Math.toRadians(valueDegrees);
        logger.debug("Got converted cos{} to radians value: {}", valueDegrees, radiansCos);
        logger.debug("Getting the cos{} value ", radiansCos);
        resultCosValue = Math.cos(radiansCos);
        resultCosValue = roundTo15Digits(resultCosValue);
        logger.debug("cos{} = {}", radiansCos, resultCosValue);
        logger.trace("End function TrigonometricFunctions.cosineFunction()");
        return resultCosValue;
    }

    public static double tangentFunction (double valueDegrees) {
        logger.trace("Start function TrigonometricFunctions.tangentFunction()");
        double resultTanValue;
        if (valueDegrees == 90.0 || valueDegrees == 270.0) {
            throw new RuntimeException("Error");
        }
        // convert degrees to radians
        logger.debug("Getting convert tan{} to radians", valueDegrees);
        double radiansTan = Math.toRadians(valueDegrees);
        logger.debug("Got converted tan{} to radians value: {}", valueDegrees, radiansTan);
        logger.debug("Getting the tan{} value ", radiansTan);
        // tan() method to get the tangent value
        resultTanValue = Math.tan(radiansTan);
        resultTanValue = roundTo15Digits(resultTanValue);
        logger.debug("tan{} = {}", radiansTan, resultTanValue);
        logger.trace("End function TrigonometricFunctions.tangentFunction()");
        return resultTanValue;
    }

    public static double cottangentFunction (double valueDegrees) {
        logger.trace("Start function TrigonometricFunctions.cottangentFunction()");
        double resultCotValue;
        logger.debug("Getting the cot{} value ", valueDegrees);
        if (valueDegrees == 0.0 || valueDegrees == 180.0 || valueDegrees == 360.0) {
            throw new RuntimeException("Error");
        }
        double tangentFunction = tangentFunction(valueDegrees);
        resultCotValue = 1/ tangentFunction;
        logger.debug("cot{} = {}", valueDegrees, resultCotValue);
        logger.trace("End function TrigonometricFunctions.cottangentFunction()");
        return resultCotValue;
    }

    private static double roundTo15Digits(double res) {
        int places = 15;
        return Math.round(res * Math.pow(10, places)) / Math.pow(10, places);
    }






}
