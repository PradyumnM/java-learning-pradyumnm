package com.java.learning.generics;

/*
@author pradyumnm
*
*/
public class App {

    public static void main(String[] args){
        Entities e= EnumInstanceGenerator.getEnumInstance(Fruits.class,2);
        System.out.println(e);

    }
}