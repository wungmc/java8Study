/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.function;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 函数函数接口 Supplier
 *
 * 函数式接口：以 {@code @FunctionInterface} 标记的，有且只有一个抽象方法的接口。
 * java 默认提供了一些通用的函数式接口，主要有四种，这里是其一。
 * <p>
 * Supplier 接口的抽象方法：{@code T get()}，表示不接收参数，直接返回值。（类似生产者）
 * 没有默认方法。
 *
 * @author wung 2018/7/31.
 */
public class SupplierTest {
	
	public static void main(String[] args) {
		Supplier<Integer> random = () -> new Random().nextInt();
		System.out.println(random.get());
		
		Supplier<Object> objectSupplier = () -> new Object();
		System.out.println(objectSupplier.get());
		
		// 没有默认方法
	}
	
	
}
