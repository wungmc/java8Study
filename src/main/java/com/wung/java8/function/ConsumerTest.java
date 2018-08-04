/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 消费者函数接口 Consumer<T>
 *
 * 函数式接口：以 {@code @FunctionInterface} 标记的，有且只有一个抽象方法的接口。
 * java 默认提供了一些通用的函数式接口，主要有四种，这里是其一。
 * <p>
 * Consumer<T> 接口的抽象方法：{@code void accept(T t)}，表示接收一个参数，不返回值。（类似消费者，只消费，不返回结果）
 *
 * @author wung 2018/7/31.
 */
public class ConsumerTest {
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		System.out.println("输出所有值：");
		// forEach(Consumer<? super T> action)
		list.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				System.out.println(integer);
			}
		});
		
		// 用 lambda 改进
		System.out.println("lambda 改进后，输出所有值：");
		list.forEach(n -> System.out.println(n));
		
		// 用方法引用改进
		System.out.println("方法引用改进后，输出所有值：");
		list.forEach(System.out :: println);
		
		// Consumer 提供了一个默认方法 andThen，可以进行链式操作
		Consumer<Integer> consumer1 = n -> System.out.print("我是：");
		Consumer<Integer> consumer2 = n -> System.out.println(n);
		
		System.out.println("输出所有值：");
		list.forEach(consumer1.andThen(consumer2));
	}
	
}
