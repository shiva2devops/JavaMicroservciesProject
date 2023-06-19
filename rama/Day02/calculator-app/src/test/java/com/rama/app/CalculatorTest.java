package com.rama.app;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testAddition(){
        Calculator calculator=new Calculator();
        int a=5;
        int b=6;
        int result= calculator.addition(a,b);
        Assert.assertEquals(result,11);
    }

    @Test
    public void testSubstrction(){
        Calculator calculator=new Calculator();
         float a=12;
         float b=6;
        double result= calculator.substraction(a,b);
        Assert.assertEquals(result,6.0,DELTA);

    }
    @Test
    public void testMultiplication(){
        Calculator calculator=new Calculator();
        float a=12;
        float b=6;
        double result= calculator.multiplication(a,b);
        Assert.assertEquals(result,72.0,DELTA);
    }

    @Test
    public void testDivision(){
        Calculator calculator=new Calculator();
        float a=12;
        float b=6;
        double result= calculator.division(a,b);
        Assert.assertEquals(result,2.0,DELTA);
    }

}
