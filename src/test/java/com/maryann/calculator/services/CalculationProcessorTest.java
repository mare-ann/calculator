package com.maryann.calculator.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculationProcessorTest {

    @Test
    public void shouldGetCorrectResult() {
        Assert.assertEquals("Plus operation not works", 2.00000000011, CalculationProcessor.calculate("1.00000000001+1.0000000001"), 0.0);
        Assert.assertEquals("Plus operation not works", 2.0, CalculationProcessor.calculate("1.0+1"), 0.0);
        Assert.assertEquals("Plus operation not works", 0.0, CalculationProcessor.calculate("-1+1.0"), 0.0);
        Assert.assertEquals("Minus operation not works", -2.0, CalculationProcessor.calculate("-1-1"), 0.0);
        Assert.assertEquals("Divide operation not works", Double.POSITIVE_INFINITY, CalculationProcessor.calculate("1/0"), 0.0);
        Assert.assertEquals("Divide operation not works", Double.NaN, CalculationProcessor.calculate("0/0"), 0.0);
        Assert.assertEquals("Divide operation not works", 0.0, CalculationProcessor.calculate("0/1"), 0.0);
        Assert.assertEquals("Divide operation not works", 0.707106781186548, CalculationProcessor.calculate("sin45"),0.0) ;
        Assert.assertEquals("Divide operation not works", 0.707106781186548, CalculationProcessor.calculate("cos45"),0.0) ;
        Assert.assertEquals("Divide operation not works", 3.0, CalculationProcessor.calculate("27.0yroot3"),0.0) ;
        Assert.assertEquals("Divide operation not works", 25.0, CalculationProcessor.calculate("5^2"),0.0) ;

    }

    @Test(expected = NumberFormatException.class)
    public void testError() {
        CalculationProcessor.calculate("1-a");
    }

    @Test(expected = NumberFormatException.class)
    public void testWordError() {
        CalculationProcessor.calculate("calculate");
    }

    @Test
    public void replaceTrigonometricFunction() {
        Assert.assertEquals("Function return expression  ", "1+1.0", CalculationProcessor.replaceTrigonometricFunction("1+sin90.0"));
        Assert.assertEquals("Function return expression  ", "1+0.0", CalculationProcessor.replaceTrigonometricFunction("1+cos90.0"));
        Assert.assertEquals("Function return expression  ", "1+1.0", CalculationProcessor.replaceTrigonometricFunction("1+tan45.0"));
        Assert.assertEquals("Function return expression  ", "1+1.0", CalculationProcessor.replaceTrigonometricFunction("1+cot45.0"));
        Assert.assertEquals("Function return expression  ", "45.0", CalculationProcessor.replaceTrigonometricFunction("45.0"));
        Assert.assertEquals("Function return expression  ", "(20.5+1.0)", CalculationProcessor.replaceTrigonometricFunction("(20.5+sin90.0)"));
    }
}