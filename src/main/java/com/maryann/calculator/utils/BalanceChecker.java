package com.maryann.calculator.utils;

import java.util.Stack;

public class BalanceChecker {

    public static boolean containsBrackets (String expression) {
        boolean result = true;
        if (!expression.contains("(") && !expression.contains("[") ){
            result = false;
        }
        return result;
    }

    public static boolean balanceChecker (String expression) {
        boolean result = true;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == ']') {
                if (stack.empty()) {
                    return false;
                }
                char res = stack.pop();
                if (c == ')' && res == '(') {
                    result = true;
                } else if (c == ']' && res == '[') {
                    result = true;
                } else {
                    return false;
                }
            }
        }

        if (!stack.empty()) {
            return false;
        }
        return result;
    }

    public static String lastBrackets (String expression) {
        String result;
        int lastIndexRoundBracket = expression.lastIndexOf("(");
        int lastIndexSquareBracket = expression.lastIndexOf("[");
        int startFinalIndexBracket = (lastIndexRoundBracket > lastIndexSquareBracket)? lastIndexRoundBracket : lastIndexSquareBracket ;
        char bracket = expression.charAt(startFinalIndexBracket);

        int endFinalIndexBracket ;
        if (bracket == '(') {
            endFinalIndexBracket = expression.indexOf(')', startFinalIndexBracket);
        } else {
            endFinalIndexBracket = expression.indexOf(']', startFinalIndexBracket);
        }

        result = expression.substring(startFinalIndexBracket + 1, endFinalIndexBracket);

        return result;
    }


}
