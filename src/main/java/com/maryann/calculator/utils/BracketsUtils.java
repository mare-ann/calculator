package com.maryann.calculator.utils;

import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BracketsUtils {

    private static final Logger logger = LoggerFactory.getLogger(BracketsUtils.class);

    public static boolean containsBrackets (String expression) {
        logger.trace("Start function BracketsUtils.containsBrackets()");
        boolean result = true;
        if (!expression.contains("(") && !expression.contains("[") ){
            result = false;
        }
        logger.trace("End function BracketsUtils.containsBrackets()");
        return result;
    }

    public static boolean balanceChecker (String expression) {
        logger.trace("Start function BracketsUtils.balanceChecker()");
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
            result = false;
        }
        logger.trace("End function BracketsUtils.balanceChecker()");
        return result;
    }

    public static String lastBrackets (String expression) {
        logger.trace("Start function BracketsUtils.lastBrackets()");
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
        logger.trace("End function BracketsUtils.lastBrackets()");
        return result;
    }


}
