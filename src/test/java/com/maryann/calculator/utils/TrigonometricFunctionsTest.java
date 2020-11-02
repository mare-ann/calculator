package com.maryann.calculator.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TrigonometricFunctionsTest {

    @Test
    public void sineFunction() {
        Assert.assertEquals("Return incorrect result", 0.707106781186548, TrigonometricFunctions.sineFunction(45.0), 0.0);
        Assert.assertEquals("Return incorrect result", -1.0, TrigonometricFunctions.sineFunction(270.0), 0.0);
        Assert.assertEquals("Return incorrect result", 0.017452406437284, TrigonometricFunctions.sineFunction(1), 0.0);
        Assert.assertEquals("Return incorrect result", 1.0, TrigonometricFunctions.sineFunction(90.0), 0.0);
        Assert.assertEquals("Return incorrect result", 0.0, TrigonometricFunctions.sineFunction(180.0), 0.0);
        Assert.assertEquals("Return incorrect result", 0, TrigonometricFunctions.sineFunction(0), 0.0);
    }

    @Test
    public void cosineFunction() {
        Assert.assertEquals("Return incorrect result", 0.0, TrigonometricFunctions.cosineFunction(90.0), 0.0);
        Assert.assertEquals("Return incorrect result", 0.956304755963035, TrigonometricFunctions.cosineFunction(17.0), 0.0);
        Assert.assertEquals("Return incorrect result", 0.996194698091746, TrigonometricFunctions.cosineFunction(5), 0.0);
        Assert.assertEquals("Return incorrect result", -1.0, TrigonometricFunctions.cosineFunction(180.0), 0.0);
        Assert.assertEquals("Return incorrect result", -1.0, TrigonometricFunctions.cosineFunction(540.0), 0.0);
        Assert.assertEquals("Return incorrect result", 1, TrigonometricFunctions.cosineFunction(0), 0.0);
    }

    @Test
    public void tangentFunction() {
        Assert.assertEquals("Return incorrect result", -0.404026225835157, TrigonometricFunctions.tangentFunction(158.0), 0.0);
        Assert.assertEquals("Return incorrect result", 0, TrigonometricFunctions.tangentFunction(0), 0.0);

    }

    @Test (expected = RuntimeException.class)
    public void checkTan90() {
        TrigonometricFunctions.tangentFunction(90.0);
    }

    @Test (expected = RuntimeException.class)
    public void checkTan270() {
        TrigonometricFunctions.tangentFunction(270.0);
    }

    @Test
    public void cottangentFunction() {
        Assert.assertEquals("Return incorrect result", 3.0776835371752562, TrigonometricFunctions.cottangentFunction(18.0), 0.0);
        Assert.assertEquals("Return incorrect result", 1.428148006742114, TrigonometricFunctions.cottangentFunction(35.0), 0.0);
        Assert.assertEquals("Return incorrect result", 4.704630109478457, TrigonometricFunctions.cottangentFunction(12), 0.0);
    }

    @Test (expected = RuntimeException.class)
    public void checkCottan0() {
        TrigonometricFunctions.cottangentFunction(0.0);
    }

    @Test (expected = RuntimeException.class)
    public void checkCottan180() {
        TrigonometricFunctions.cottangentFunction(180.0);
    }

    @Test (expected = RuntimeException.class)
    public void checkCottan360() {
        TrigonometricFunctions.cottangentFunction(360.0);
    }

}