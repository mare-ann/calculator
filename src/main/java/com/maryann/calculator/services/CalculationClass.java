package com.maryann.calculator.services;

public class CalculationClass {

    public static int calculate (String expression) {
        String res2;
        if (expression.matches("-?\\d+")){
            return Integer.parseInt(expression);
        } else if (expression.contains("*") || expression.contains("/")) {
            res2 = calculateExpressionWithoutBrackets(expression, "*", "/");
        } else if (expression.contains("+") || expression.contains("-")) {
            res2 = calculateExpressionWithoutBrackets(expression, "+", "-");
        } else {
            return Integer.parseInt(expression);
        }
        return calculate(res2);
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
        int res = calculateSingleOperation(smallExp);
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
            if (c >= '0' && c <= '9') {
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
        String b = "";
        if (expression.charAt(firstIndMulOrDiv + 1) == '-') {
            b = "-";
            firstIndMulOrDiv++;
        }

        for (int i = firstIndMulOrDiv + 1; i < expression.length() ; i++) {
            char c = expression.charAt(i);
            if (c >= '0' && c <= '9') {
                b = b + c;
            } else {
                return i - 1;
            }
        }
        return firstIndMulOrDiv + b.length();
    }

    private static int calculateSingleOperation (String a, String b, String operation) {
        int aa = Integer.parseInt(a);
        int bb = Integer.parseInt(b);
        if (operation.equals("*")) {
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

    private static int calculateSingleOperation (String expression) {
        if (expression.contains("*")) {
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
