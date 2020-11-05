package com.maryann.calculator.services;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionTransformerTest {

    @Test
    public void transformIntoNumber() {
        ExpressionTransformer expressionTransformer = new ExpressionTransformer();
        Assert.assertEquals(3.14159, expressionTransformer.transformIntoNumber("pi"), 0.00001);
        Assert.assertEquals(0.7071, expressionTransformer.transformIntoNumber("SIN45"), 0.00001);
        Assert.assertEquals(0.7071, expressionTransformer.transformIntoNumber("SIN(45)"), 0.00001);
        Assert.assertEquals(0.7071, expressionTransformer.transformIntoNumber("SIN((45))"), 0.00001);
        Assert.assertEquals(0.7071, expressionTransformer.transformIntoNumber("  SIN   ((4   5))"), 0.00001);
    }

    @Test(expected = RuntimeException.class)
    public void transformException() {
        ExpressionTransformer expressionTransformer = new ExpressionTransformer();
        expressionTransformer.transformIntoNumber("SIN((45)");
    }
}