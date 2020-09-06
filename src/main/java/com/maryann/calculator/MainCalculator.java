package com.maryann.calculator;

import com.maryann.calculator.services.TranslationClass;

import java.util.Scanner;

public class MainCalculator {

    public static void main(String[] args) {
        System.out.println("Enter your expression: ");
        TranslationClass trans = new TranslationClass();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String expression = sc.nextLine();
            try {
                trans.translationIntoNumber(expression);
            } catch (NumberFormatException e) {
                System.out.println("Failed to process expression, incorrect number format. Try again.");
            }
        }
    }
}
