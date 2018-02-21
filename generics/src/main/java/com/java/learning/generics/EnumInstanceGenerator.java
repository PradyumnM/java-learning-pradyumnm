package com.java.learning.generics;

public class EnumInstanceGenerator {
	   public static <T, R extends Entities> R getEnumInstance(Class<R> r, T t) {

	        Entities[] obj = r.getEnumConstants();
	        for (Entities e : obj){
	            if (e.getValue().equals(t)) {
	                return (R) e;
	            }
	      }

	        return null;
	}
}
