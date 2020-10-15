package com.maryann.calculator.services;
import com.maryann.calculator.utils.SquareRootAndSquareMultipliersNumbers;
import com.maryann.calculator.utils.TrigonometricFunctions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculationClass {

    private static final Logger logger = LoggerFactory.getLogger(CalculationClass.class);

    public static double calculate (String expression) {
        logger.trace("Start function CalculationClass.calculate()");
        logger.debug("Calculating expression: " + expression);
        String res2;
        if (expression.matches("-?\\d+(\\.\\d+)?")) {
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
            return res;
        }
        return calculate(res2);
    }

    public static String replaceTrigonometricFunction(String expression) {
        logger.trace("Start function CalculationClass.replaceTrigonometricFunction()");
        Pattern p = Pattern.compile("(sin|cos|tan|cot)(-?\\d+(\\.\\d+)?)");
        Matcher m = p.matcher(expression);
        while (m.find()){
            String exp = m.group(0);
            String operation = m.group(1);
            String value = m.group(2);
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
            expression = expression.replace(exp, String.valueOf(res));
        }
        logger.trace("End function CalculationClass.replaceTrigonometricFunction()");
        return expression;
    }

    private static String calculateExpressionWithoutBrackets(String expression, String operation1, String operation2) {
        logger.trace("Start function CalculationClass.calculateExpressionWithoutBrackets()");
        expression = expression.replace("--", "+");
        int contMult = expression.indexOf(operation1, 1);
        int contDiv = expression.indexOf(operation2, 1);

        int firstIndMulOrDiv;
        if(contMult == -1) { // íåìàº +
            firstIndMulOrDiv = contDiv;
        } else if (contDiv == -1) { //íåìàº -
            firstIndMulOrDiv = contMult;
        } else { // º ³ "+" ³ "-"
            firstIndMulOrDiv = Math.min(contDiv, contMult);
        }
        int indA, indB;
        indA = getStartOfA(expression, firstIndMulOrDiv);
        indB = getEndOfB(expression, firstIndMulOrDiv);

        String smallExp = expression.substring(indA, indB + 1);
        double res = calculateSingleOperation(smallExp);
        logger.debug("Calculated small expression {} result {} ", smallExp, res);
        String expCutStart = "";
        String expCutEnd = "";
        if (indA != 0 ) {
            expCutStart = expression.substring(0, indA);
        }
        if (indB != expression.length() - 1) {
            expCutEnd = expression.substring(indB + 1);
        }
        logger.trace("End function CalculationClass.calculateExpressionWithoutBrackets()");
        return expCutStart + res + expCutEnd;
    }


    private static int getStartOfA(String expression, int firstIndMulOrDiv) {
        logger.trace("Start function CalculationClass.getStartOfA()");
        StringBuilder a = new StringBuilder();
        for (int i = firstIndMulOrDiv - 1; i >= 0 ; i--) {
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
        logger.trace("End function CalculationClass.getStartOfA()");
        return firstIndMulOrDiv - a.length();
    }

    private static int getEndOfB(String expression, int firstIndMulOrDiv) {
        logger.trace("Start function CalculationClass.getEndOfB()");
        int operationIndex = firstIndMulOrDiv;
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
        logger.trace("End function CalculationClass.getEndOfB()");
        return operationIndex + b.length();
    }

    private static double calculateSingleOperation (String a, String b, String operation) {
        logger.trace("Start function CalculationClass.calculateSingleOperation(with three value)");
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
        logger.trace("Start function CalculationClass.calculateSingleOperation(with one value)");
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
