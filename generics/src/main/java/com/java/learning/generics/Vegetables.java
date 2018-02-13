package com.java.learning.generics;

/**
 * @author pradyumnm
 *
 */
public enum Vegetables implements Entities{
    Potato(1),Tomato(2),Onion(3);

    int value;

    Vegetables(int i) {
        value=i;
    }

    public Integer getValue() {
        return value;
}

}