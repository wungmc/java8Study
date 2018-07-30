/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.defaultmethod;

/**
 *
 * @author wung 2018/7/30.
 */
public interface FourWheeler {
	
	default void print() {
		System.out.println("im a fourWheeler");
	}
	
}
