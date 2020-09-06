package com.maryann.calculator.services;

import com.maryann.calculator.utils.BalanceChecker;
import com.maryann.calculator.utils.DeleteOfExtraCharacters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TranslationClass {

    public void translationIntoNumber (String expression) {

        expression = DeleteOfExtraCharacters.cleanSpaces(expression);

        boolean hasBracket = BalanceChecker.balanceChecker(expression);

        if (hasBracket == true) {
            System.out.println("Balance is ok");
        } else {
            System.out.println("Balance is not ok");
            return;
        }

        double res = doCalculation(expression);
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(' ');
        String prityResult = new DecimalFormat("###,###.####", otherSymbols).format(res);
        System.out.println("Your result: " + prityResult);
    }

    private double doCalculation(String expression) {
        if (!BalanceChecker.containsBrackets(expression) ) {
            double calculated = CalculationClass.calculate(expression);
            return calculated;
        } else { // has brackets
            String expressionInBrackets = BalanceChecker.lastBrackets(expression);
            double calculated = CalculationClass.calculate(expressionInBrackets);

            int indA = expression.lastIndexOf(expressionInBrackets);
            char bracket = expression.charAt(indA - 1);
            if (bracket == '[' && calculated < 0) {
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
                two = expression.substring(indB + 1, expression.length());
            }

            newExpression = one + calculated + two;
            return doCalculation(newExpression);
        }
    }


}
