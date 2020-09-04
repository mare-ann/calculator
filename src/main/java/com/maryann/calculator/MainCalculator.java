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
            trans.translationIntoNumber(expression);
        }
    }
}
