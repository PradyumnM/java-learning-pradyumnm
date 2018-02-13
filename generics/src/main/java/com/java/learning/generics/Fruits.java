package com.java.learning.generics;

	/**
	 * @author pradyumnm
	 *
	 */
	public enum Fruits implements Entities{
	    Apple(1),Guava(2),Grapes(3);

	    int value;

	    Fruits(int i) {
	        value=i;
	    }

	    public Integer getValue() {
	        return value;
	}

}