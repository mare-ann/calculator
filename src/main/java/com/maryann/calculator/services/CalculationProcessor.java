package com.maryann.calculator.services;
import com.maryann.calculator.utils.SquareRootAndSquareMultipliersNumbers;
import com.maryann.calculator.utils.TrigonometricFunctions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class can calculate expressions without brackets
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */

public class CalculationProcessor {

    private static final Logger logger = LoggerFactory.getLogger(CalculationProcessor.class);

    /**
     * Function take expression and calculate it, and returns the result as a number type double
     * @param expression  - string representation of an expression, for example: "5+1*10"
     * @return double number, result of expression
     */
    public static double calculate (String expression) {
        logger.trace("Start function CalculationProcessor.calculate()");
        logger.debug("Calculating expression: " + expression);
        String res2;
        if (expression.matches("-?\\d+(\\.\\d+)?")) {//check if expression is just a number
            logger.debug("Operation not found only number {}", expression);
            return Double.parseDouble(expression);
        } else if (expression.contains("sin") || expression.contains("cos") || expression.contains("tan") || expression.contains("cot")){
            res2 = replaceTrigonometricFunction(expression);
        } else if (expression.contains("^") || expression.contains("yroot")) {
            res2 = calculateExpressionWithoutBrackets(expression, "^", "yroot");
        } else if (expression.contains("*") || expression.contains("/")) {
            res2 = calculateExpressionWithoutBrackets(expression, "*", "/");
        } else if (expression.contains("+") || expression.contains("-")) {
            res2 = calculateExpressionWithoutBrackets(expression, "+", "-");
        } else {
            double res = Double.parseDouble(expression);
            //if expression don't have any operation , return result
            return res;
        }
        //recursive call if expression contains some operations
        return calculate(res2);
    }

    /**
     * If expression have trigonometric operation, calculate it,
     * and replace by result
     * @param expression - string representation of an expression, for example: "sin45+1*cos10"
     * @return expression without trigonometric operation
     */
    public static String replaceTrigonometricFunction(String expression) {
        logger.trace("Start function CalculationProcessor.replaceTrigonometricFunction()");
        //create regex
        Pattern p = Pattern.compile("(sin|cos|tan|cot)(-?\\d+(\\.\\d+)?)");
        Matcher m = p.matcher(expression);//find all matches in expression
        while (m.find()){
            //find for groups
            String exp = m.group(0);//find operation; 1+sin45 -> sin45
            String operation = m.group(1);//find name of trigonometric operation; sin45 -> sin
            String value = m.group(2);// its a number; sin45 -> 45
            double res =0;
            double number = Double.parseDouble(value);
            logger.debug("Found function {}", exp);
            switch (operation) {
                case "sin":
                    res = TrigonometricFunctions.sineFunction(number);
                    break;
                case "cos":
                    res = TrigonometricFunctions.cosineFunction(number);
                    break;
                case "tan":
                    res = TrigonometricFunctions.tangentFunction(number);
                    break;
                case "cot":
                    res = TrigonometricFunctions.cottangentFunction(number);
                    break;
            }
            //replace our expression in string
            expression = expression.replace(exp, String.valueOf(res));
        }
        logger.trace("End function CalculationProcessor.replaceTrigonometricFunction()");
        return expression;
    }

    private static String calculateExpressionWithoutBrackets(String expression, String operation1, String operation2) {
        logger.trace("Start function CalculationProcessor.calculateExpressionWithoutBrackets()");
        //in expression replace two minus on plus
        expression = expression.replace("--", "+");
        //return get location first operation, after first character
        int indexOfOper1 = expression.indexOf(operation1, 1);
        //return get location second operation, after first character
        int indexOfOper2 = expression.indexOf(operation2, 1);

        int indexOfCurrentOperation;
        if(indexOfOper1 == -1) { // dont have +
            indexOfCurrentOperation = indexOfOper2;
        } else if (indexOfOper2 == -1) { //dont have -
            indexOfCurrentOperation = indexOfOper1;
        } else { //have "+" and "-", method min give smaller result
            indexOfCurrentOperation = Math.min(indexOfOper2, indexOfOper1);
        }
        int indA, indB;
        //return, start index of small expression
        indA = getStartOfA(expression, indexOfCurrentOperation);
        //return, end index of small expression
        indB = getEndOfB(expression, indexOfCurrentOperation);
        //cut small expression from input string
        String smallExp = expression.substring(indA, indB + 1);
        double res = calculateSingleOperation(smallExp);
        logger.debug("Calculated small expression {} result {} ", smallExp, res);
        String expCutStart = "";
        String expCutEnd = "";
        if (indA != 0 ) {
            //take all before small expression
            expCutStart = expression.substring(0, indA);
        }
        if (indB != expression.length() - 1) {
            //take all after small expression
            expCutEnd = expression.substring(indB + 1);
        }
        logger.trace("End function CalculationProcessor.calculateExpressionWithoutBrackets()");
        //connect all three expression
        return expCutStart + res + expCutEnd;
    }


    private static int getStartOfA(String expression, int indexOfCurrentOperation) {
        logger.trace("Start function CalculationProcessor.getStartOfA()");
        //create new obj
        StringBuilder a = new StringBuilder();
        for (int i = indexOfCurrentOperation - 1; i >= 0 ; i--) {
            char c = expression.charAt(i);
            if ((c >= '0' && c <= '9') || c == '.') {
                a.insert(0, c);
            } else if(c == '-') {
                if(i == 0) {
                    a.insert(0, c);
                } else if (expression.charAt(i - 1) == '+' || expression.charAt(i - 1) == '-') {
                    a.insert(0, c);
                } else {
                    return i + 1;
                }
            } else {
                return i + 1;
            }
        }
        logger.trace("End function CalculationProcessor.getStartOfA()");
        return indexOfCurrentOperation - a.length();
    }

    private static int getEndOfB(String expression, int indexOfCurrentOperation) {
        logger.trace("Start function CalculationProcessor.getEndOfB()");
        int operationIndex = indexOfCurrentOperation;
        String b = "";
        if (expression.charAt(operationIndex + 1) == '-') {
            b = "-";
            operationIndex++;
        }

        if (expression.contains("yroot")) {
            operationIndex = operationIndex + "yroot".length();
        }

        for (int i = operationIndex + 1; i < expression.length() ; i++) {
            char c = expression.charAt(i);
            if ((c >= '0' && c <= '9') || c == '.') {
                b = b + c;
            } else {
                return i - 1;
            }
        }
        logger.trace("End function CalculationProcessor.getEndOfB()");
        return operationIndex + b.length();
    }

    private static double calculateSingleOperation (String a, String b, String operation) {
        logger.trace("Start function CalculationProcessor.calculateSingleOperation(with three value)");
        double aa = Double.parseDouble(a);
        double bb = Double.parseDouble(b);
        switch (operation) {
            case "^":
                return SquareRootAndSquareMultipliersNumbers.powNumbers(aa, bb);
            case "yroot":
                return SquareRootAndSquareMultipliersNumbers.getRoots(aa, bb);
            case "*":
                return aa * bb;
            case "/":
                return aa / bb;
            case "+":
                return aa + bb;
            case "-":
                return aa - bb;
            default:
                logger.error("Operation {} have IllegalArgumentException and not recognized!", operation);
                throw new IllegalArgumentException("Operation " + operation + " NOT recognized!");
        }

    }

    private static double calculateSingleOperation (String expression) {
        logger.trace("Start function CalculationProcessor.calculateSingleOperation(with one value)");
        if (expression.contains("^")) {
            String a, b, oper;
            oper = "^";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1);
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("yroot")) {
            String a, b, oper;
            oper = "yroot";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + oper.length());
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("*")) {
            String a, b, oper;
            oper = "*";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1);
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("/")) {
            String a, b, oper;
            oper = "/";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1);
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("+")) {
            String a, b, oper;
            oper = "+";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1);
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("-")) {
            String a, b, oper;
            oper = "-";
            int index = expression.lastIndexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1);
            return calculateSingleOperation(a, b, oper);
        } else {
            logger.error("Expression {} have IllegalArgumentException", expression);
            throw new IllegalArgumentException("Can't calculate expression " + expression);
        }
    }
}
