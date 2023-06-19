package com.rama.app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Calculator calculator=new Calculator();
        System.out.println( calculator.addition(1,4) );
        System.out.println( calculator.substraction(9,4) );
        System.out.println( calculator.multiplication(3,4) );
        System.out.println( calculator.division(12,4) );


        System.out.println( "Hello World!" );
    }
}
