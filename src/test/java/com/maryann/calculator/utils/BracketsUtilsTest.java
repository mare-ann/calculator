package com.maryann.calculator.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BracketsUtilsTest {

    @Test
    public void containsBrackets() {
        Assert.assertFalse(BracketsUtils.containsBrackets("5+6.2"));
        Assert.assertTrue(BracketsUtils.containsBrackets("[(2+2)*2]"));
        Assert.assertTrue(BracketsUtils.containsBrackets("(50+25)+5"));
    }

    @Test
    public void balanceChecker() {
        Assert.assertFalse(BracketsUtils.balanceChecker("(123)(12"));
        Assert.assertTrue(BracketsUtils.balanceChecker("(1+(0))"));
        Assert.assertEquals(true, BracketsUtils.balanceChecker("(1+(0))"));
        Assert.assertTrue(BracketsUtils.balanceChecker("[(5+5)*2.0+[(34.2*3)*9]]"));
        Assert.assertFalse(BracketsUtils.balanceChecker("[[[5+2]-3+2))"));
        Assert.assertFalse(BracketsUtils.balanceChecker("[[5+5]]("));
    }

    @Test
    public void lastBrackets() {
        Assert.assertEquals("6+2", BracketsUtils.lastBrackets("[5+5*2+[8*(6+2)]]"));
        Assert.assertEquals("6+2", BracketsUtils.lastBrackets("[5+5*2+[8*(6+2)]]+250/2+3.0"));
        Assert.assertEquals("6+2", BracketsUtils.lastBrackets("250/2+3.0*[[5+(5*2)]+[8*(6+2)]]"));
    }
}