/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.functioninterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 *
 *
 * @author wung 2018/7/31.
 */
public class PredicateTest {
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		System.out.println("输出所有数：");
		eval(list, n -> true);
		
		System.out.println("输出所有偶数：");
		eval(list, (n) -> (n%2 == 0));
		
		System.out.println("输出所有数：");
		list.forEach(System.out :: println);
		
		System.out.println("输出所有数：");
		list.forEach(PredicateTest :: print);
		list.forEach((n) -> System.out.print(n + 1));
	}
	
	public static void eval(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer n : list) {
			if (predicate.test(n)) {
				System.out.println(n);
			}
		}
	}
	
	public static void print(Integer n) {
		System.out.println(n);
	}
}
