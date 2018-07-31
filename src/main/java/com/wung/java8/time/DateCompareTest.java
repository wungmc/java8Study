/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.LocalDate;

/**
 * 日期比较.
 * equals, isBefore, isAfter
 *
 * @author wung 2018/7/31.
 */
public class DateCompareTest {
	
	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate day = LocalDate.of(2018, 7,30);
		System.out.println(today.equals(day));
		System.out.println(today.isEqual(day));
		
		System.out.println(today.isBefore(day));
		System.out.println(today.isAfter(day));
		
		// out
		// false
		// false
		// false
		// true
	}
	
}
