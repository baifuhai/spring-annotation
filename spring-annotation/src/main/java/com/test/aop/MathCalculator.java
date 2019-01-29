package com.test.aop;

public class MathCalculator {

    public int add(int i, int j){
        System.out.println("MathCalculator...add...");
        return i + j;
    }

    public int div(int i, int j){
        System.out.println("MathCalculator...div...");
        return i / j;
    }

}
