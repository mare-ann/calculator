package com.maryann.calculator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        logger.debug("cos{} = {}", radiansCos, resultCosValue);
        logger.trace("End function TrigonometricFunctions.cosineFunction()");
        return resultCosValue;
    }

    public static double tangentFunction (double valueDegrees) {
        logger.trace("Start function TrigonometricFunctions.tangentFunction()");
        double resultTanValue;
        // convert degrees to radians
        logger.debug("Getting convert tan{} to radians", valueDegrees);
        double radiansTan = Math.toRadians(valueDegrees);
        logger.debug("Got converted tan{} to radians value: {}", valueDegrees, radiansTan);
        logger.debug("Getting the tan{} value ", radiansTan);
        // tan() method to get the tangent value
        resultTanValue = Math.tan(radiansTan);
        logger.debug("tan{} = {}", radiansTan, resultTanValue);
        logger.trace("End function TrigonometricFunctions.tangentFunction()");
        return resultTanValue;
    }

    public static double cottangentFunction (double valueDegrees) {
        logger.trace("Start function TrigonometricFunctions.cottangentFunction()");
        double resultCotValue;
        logger.debug("Getting the cot{} value ",tangentFunction(valueDegrees) );
        resultCotValue = 1/tangentFunction(valueDegrees);
        logger.debug("tan{} = {}",tangentFunction(valueDegrees) , resultCotValue);
        logger.trace("End function TrigonometricFunctions.cottangentFunction()");
        return resultCotValue;
    }








}
