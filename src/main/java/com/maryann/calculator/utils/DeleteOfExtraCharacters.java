package com.maryann.calculator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
