package com.java.learning.generics;

/**
 * @author pradyumnm
 *
 */
public interface Entities {
    public <R extends Object> R getValue();
}