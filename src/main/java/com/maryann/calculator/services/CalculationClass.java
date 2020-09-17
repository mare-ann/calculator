package com.maryann.calculator.services;

import com.maryann.calculator.utils.SquareRootAndSquareMultipliersNumbers;
import com.maryann.calculator.utils.TrigonometricFunctions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculationClass {

    public static double calculate (String expression) {
        String res2;
        if (expression.matches("-?\\d+(\\.\\d+)?")) {
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
        Pattern p = Pattern.compile("(sin|cos|tan|cot)(-?\\d+(\\.\\d+)?)");
        Matcher m = p.matcher(expression);
        while (m.find()){
            String exp = m.group(0);
            String operation = m.group(1);
            String value = m.group(2);
            double res =0;
            double number = Double.parseDouble(value);
            if (operation.equals("sin")){
                 res = TrigonometricFunctions.sineFunction(number);
            }else if (operation.equals("cos")){
                 res = TrigonometricFunctions.cosineFunction(number);
            }else if (operation.equals("tan")){
                 res = TrigonometricFunctions.tangentFunction(number);
            }else if (operation.equals("cot")){
                 res = TrigonometricFunctions.cottangentFunction(number);
            }
            expression = expression.replace(exp, String.valueOf(res));
        }
        return expression;
    }

    private static String calculateExpressionWithoutBrackets(String expression, String operation1, String operation2) {
        expression = expression.replace("--", "+");
        int contMult = expression.indexOf(operation1, 1);
        int contDiv = expression.indexOf(operation2, 1);

        int firstIndMulOrDiv = 0;
        if(contMult == -1) { // ÌÂÏ‡∫ +
            firstIndMulOrDiv = contDiv;
        } else if (contDiv == -1) { //ÌÂÏ‡∫ -
            firstIndMulOrDiv = contMult;
        } else { // ∫ ≥ "+" ≥ "-"
            if(contDiv < contMult) {
                firstIndMulOrDiv = contDiv;
            } else {
                firstIndMulOrDiv = contMult;
            }
        }
        int indA, indB;
        indA = getStartOfA(expression, firstIndMulOrDiv);
        indB = getEndOfB(expression, firstIndMulOrDiv);

        String smallExp = expression.substring(indA, indB + 1);
        double res = calculateSingleOperation(smallExp);
        String expCutStart = "";
        String expCutEnd = "";
        if (indA != 0 ) {
            expCutStart = expression.substring(0, indA);
        }
        if (indB != expression.length() - 1) {
            expCutEnd = expression.substring(indB + 1, expression.length());
        }
        return expCutStart + res + expCutEnd;
    }


    private static int getStartOfA(String expression, int firstIndMulOrDiv) {
        String a = "";
        for (int i = firstIndMulOrDiv - 1; i >= 0 ; i--) {
            char c = expression.charAt(i);
            if ((c >= '0' && c <= '9') || c == '.') {
                a = c + a;
            } else if(c == '-') {
                if(i == 0) {
                    a = c + a;
                } else if (expression.charAt(i - 1) == '+' || expression.charAt(i - 1) == '-') {
                    a = c + a;
                } else {
                    return i + 1;
                }
            } else {
                return i + 1;
            }
        }
        return firstIndMulOrDiv - a.length();
    }

    private static int getEndOfB(String expression, int firstIndMulOrDiv) {
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
        return firstIndMulOrDiv + b.length();
    }

    private static double calculateSingleOperation (String a, String b, String operation) {
        double aa = Double.parseDouble(a);
        double bb = Double.parseDouble(b);
        if (operation.equals("^")) {
            return SquareRootAndSquareMultipliersNumbers.powNumbers(aa, bb);
        } else if (operation.equals("yroot")) {
            return SquareRootAndSquareMultipliersNumbers.getRoots(aa, bb);
        } else if (operation.equals("*")) {
            return aa * bb;
        } else if (operation.equals("/")) {
            return aa / bb;
        } else if (operation.equals("+")) {
            return aa + bb;
        } else if (operation.equals("-")) {
            return aa - bb;
        } else {
            throw new IllegalArgumentException("Operation " + operation + " NOT recognized!");
        }
    }

    private static double calculateSingleOperation (String expression) {
        if (expression.contains("^")) {
            String a, b, oper;
            oper = "^";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1, expression.length());
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("yroot")) {
            String a, b, oper;
            oper = "yroot";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + oper.length(), expression.length());
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("*")) {
            String a, b, oper;
            oper = "*";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1, expression.length());
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("/")) {
            String a, b, oper;
            oper = "/";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1, expression.length());
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("+")) {
            String a, b, oper;
            oper = "+";
            int index = expression.indexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1, expression.length());
            return calculateSingleOperation(a, b, oper);
        } else if (expression.contains("-")) {
            String a, b, oper;
            oper = "-";
            int index = expression.lastIndexOf(oper);
            a = expression.substring(0, index);
            b = expression.substring(index + 1, expression.length());
            return calculateSingleOperation(a, b, oper);
        } else {
            throw new IllegalArgumentException("Can't calculate expression " + expression);
        }
    }
}
