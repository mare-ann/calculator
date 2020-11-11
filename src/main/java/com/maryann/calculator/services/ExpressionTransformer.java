package com.maryann.calculator.services;

import com.maryann.calculator.utils.BracketsUtils;
import com.maryann.calculator.utils.DeleteOfExtraCharacters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class can calculate expressions with brackets,
 * gets expression from brackets and using {@link CalculationProcessor} to get the result
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */

@Service
public class ExpressionTransformer {

    private static final Logger logger = LoggerFactory.getLogger(ExpressionTransformer.class);

    /**
     * Function take expression , delete all spaces, check balance brackets
     * and with to use class {@link CalculationProcessor} return result
     * @param expression - string expression
     * @return double number
     */
    public double transformIntoNumber(String expression) {
        logger.trace("Start function ExpressionTransformer.transformIntoNumber()");
        expression = DeleteOfExtraCharacters.cleanSpaces(expression);
        boolean hasBracket = BracketsUtils.balanceChecker(expression);

        if (hasBracket) {
            logger.info("Balance is ok");
        } else {
            logger.warn("Balance is not ok");
            throw new RuntimeException("Balance is not ok");
        }

        expression = expression.toLowerCase();
        expression = expression.replace("pi", String.valueOf(Math.PI));
        logger.info("Expression after cleaning: " + expression);
        double res = doCalculation(expression);
        logger.info("Expression calculated successfully");
        logger.trace("End function ExpressionTransformer.transformIntoNumber()");
        return res;
    }

    private double doCalculation(String expression) {
        logger.trace("Start function ExpressionTransformer.doCalculation()");
        logger.debug("Calculating expression: " + expression);
        if (!BracketsUtils.containsBrackets(expression) ) {
            double calculated = CalculationProcessor.calculate(expression);
            logger.debug("Returned result for expression {} = {}", expression, calculated);
            logger.trace("End function ExpressionTransformer.doCalculation()");
            return calculated;
        } else { // has brackets
            String expressionInBrackets = BracketsUtils.lastBrackets(expression);
            logger.debug("Extracted {} from {}", expressionInBrackets, expression);
            double calculated = CalculationProcessor.calculate(expressionInBrackets);
            logger.debug("Res for {} is {}", expressionInBrackets,calculated);
            int indA = expression.lastIndexOf(expressionInBrackets);
            char bracket = expression.charAt(indA - 1);
            if (bracket == '[' && calculated < 0) {
                logger.debug("Found [ ] brackets");
                calculated *= (-1);
            }

            int length = expressionInBrackets.length();
            int indB = indA + length;
            String newExpression ;
            String one = "";
            String two = "";
            if (indA !=1 ) {
                one = expression.substring(0, indA - 1);
            }
            if (indB !=expression.length() - 1) {
                two = expression.substring(indB + 1);
            }
            newExpression = one + calculated + two;
            logger.debug("New expression is: {}", newExpression);
            return doCalculation(newExpression);
        }
    }


}
