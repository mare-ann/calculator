package com.maryann.calculator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class can clean spaces from string
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */

public class DeleteOfExtraCharacters {

    private static final Logger logger = LoggerFactory.getLogger(DeleteOfExtraCharacters.class);

    public static String cleanSpaces (String expression) {
        logger.trace("Start function DeleteOfExtraCharacters.cleanSpaces()");
        String expressionAfterClean = expression.replaceAll("\\s+", "");
        logger.debug("Cleaned spaces: " + expressionAfterClean);
        logger.trace("End function DeleteOfExtraCharacters.cleanSpaces()");
        return expressionAfterClean;
    }

}
