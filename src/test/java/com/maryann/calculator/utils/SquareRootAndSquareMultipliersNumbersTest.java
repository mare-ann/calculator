package com.maryann.calculator.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareRootAndSquareMultipliersNumbersTest {

    @Test
    public void powNumbers() {
        Assert.assertEquals(25.0, SquareRootAndSquareMultipliersNumbers.powNumbers(5.0, 2), 0.0);
        Assert.assertEquals(27.0, SquareRootAndSquareMultipliersNumbers.powNumbers(3.0, 3.0), 0.0);
        Assert.assertTrue(SquareRootAndSquareMultipliersNumbers.powNumbers(2.0, 2.0) == 4.0);
    }

    @Test
    public void getRoots() {
        Assert.assertEquals(3.0, SquareRootAndSquareMultipliersNumbers.getRoots(27, 3), 0.0);
        Assert.assertEquals(3.0, SquareRootAndSquareMultipliersNumbers.getRoots(9.0, 2.0), 0.0);
    }
}