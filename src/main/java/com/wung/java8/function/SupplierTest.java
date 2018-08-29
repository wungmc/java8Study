/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.function;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 提供者函数接口 Supplier
 *
 * 函数式接口：以 {@code @FunctionInterface} 标记的(可以省略)，有且只有一个抽象方法的接口。
 * （和 Object 里的 public 类型的方法签名相同的接口不算！所以，Comparator里虽然有两个抽象接口，但依然是函数式接口）。
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
		// 方法引用的写法
		// Supplier<Object> objectSupplier = Object :: new;
		System.out.println(objectSupplier.get());
		
		int n = getInt(() -> new Random().nextInt());
		System.out.println(n);
		
		// 没有默认方法
		
		// out
		// 2103572490
		// java.lang.Object@6979e8cb
		// 1727481262
	}
	
	public static int getInt(Supplier<Integer> s) {
		return s.get();
	}
	
}
