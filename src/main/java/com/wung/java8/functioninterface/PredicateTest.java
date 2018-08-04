/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.functioninterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 谓语函数接口 Predicate<T>
 *
 * 函数式接口：以 {@code @FunctionInterface} 标记的，有且只有一个抽象方法的接口。
 * java 默认提供了一些通用的函数式接口，主要有四种，这里是其一。
 * <p>
 * Predicate<T> 接口的抽象方法：{@code boolean test(T t)}，表示接收一个参数，并返回boolean类型的值。
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

		// 判断奇数的接口（参数只有一个时，lambda表达式的参数部分的小括号可以省略）
		Predicate<Integer> oddPredicate = n -> n%2 == 1;
		System.out.println("输出所有奇数：");
		eval(list, oddPredicate);
		
		// Predicate 提供了几个默认方法：and, or, negate, isEqual
		Predicate<Integer> evenPredicate = n -> (n%2 == 0);
		System.out.println("1 是偶数并且是奇数：" + evenPredicate.and(oddPredicate).test(1));
		System.out.println("1 是偶数或者奇数：" + evenPredicate.or(oddPredicate).test(1));
		System.out.println("1 不是偶数" + evenPredicate.negate().test(1));
		
	}
	
	public static void eval(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer n : list) {
			if (predicate.test(n)) {
				System.out.println(n);
			}
		}
	}
	
}
