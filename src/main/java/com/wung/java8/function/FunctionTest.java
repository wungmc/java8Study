/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.function;

import java.util.function.Function;

/**
 * 函数函数接口 Function<T, R>
 *
 * 函数式接口：以 {@code @FunctionInterface} 标记的，有且只有一个抽象方法的接口。
 * java 默认提供了一些通用的函数式接口，主要有四种，这里是其一。
 * <p>
 * Function<T, R> 接口的抽象方法：{@code R apply(T t)}，表示接收一个参数，返回计算结果值。（类似函数 y=f(x)）
 *
 * @author wung 2018/7/31.
 */
public class FunctionTest {
	
	public static void main(String[] args) {
		Function<Integer, Integer> function1 = n -> n*2;
		Function<Integer, Integer> function2 = n -> n*n;
		
		System.out.println(function1.apply(3));
		System.out.println(function2.apply(3));
		
		// 默认方法 andThen 支持链式操作
		System.out.println(function1.andThen(function2).apply(3));
		// 默认方法 compose()，执行顺序与 andThen 反过来
		System.out.println(function1.compose(function2).apply(3));
		
		// out
		// 6
		// 9
		// 36
		// 18
		
	}
	
}
