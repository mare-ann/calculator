package com.maryann.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.maryann.calculator.services.ExpressionTransformer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;


/**
 * Main Console Application
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */

public class ConsoleCalculator {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleCalculator.class);


    public static void main(String[] args) {
        logger.trace("Start function MainCalculator.main()");
        logger.info("Starting the program");

        logger.debug("Requesting to the user to enter data");
        System.out.println("Enter your expression: ");
        ExpressionTransformer trans = new ExpressionTransformer();

        while (true) {
            logger.debug("Reading information from user with scanner ");
            Scanner sc = new Scanner(System.in);
            String expression = sc.nextLine();
            logger.info("User entered: \n" + expression);
            try {
                double res = trans.transformIntoNumber(expression);
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(' ');
                String prityResult = new DecimalFormat("###,###.####", otherSymbols).format(res);
                logger.info("Result: " + prityResult);
                System.out.println("Your result: " + prityResult);
            } catch (NumberFormatException e) {
                logger.error("user entered incorrect number format", e);
                System.out.println("Failed to process expression, incorrect number format. Try again.");
            }
        }
    }
}
