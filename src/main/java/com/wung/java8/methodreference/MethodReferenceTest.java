/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.methodreference;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用：是 lambda 表达式的简写，可以看作 lambda 的语法糖。
 * 1、什么lambda可以用？如果lambda表达式只是简单的方法调用，可以简写为方法引用，如：
 * <p>
 *     (String x) -> System.out.println(x); 等价于：System.out :: println;
 *     (Apple a) -> a.getWeight(); 等价于：Apple :: getWeight;
 *     () -> Thread.currentThread().dumpStack(); 等价于: Thread.currentThread() :: dumpStack;
 *     (String s, int i) -> s.subString(i); 等价于： String :: subString;
 * </p>
 *
 * @author wung 2018/8/8.
 */
public class MethodReferenceTest {
	
	
	/**
	 * 指向静态方法的方法引用.
	 * (args) -> ClassName.staticMethod(args);
	 * 方法引用为：
	 * ClassName :: staticMethod
	 *
	 */
	@Test
	public void t1() {
		Function<String, Integer> function = (s) -> Integer.parseInt(s);
		Function<String, Integer> function1 = Integer :: parseInt;
		
	}
	
	/**
	 * 指向对象实例方法的方法引用
	 * (arg, rest) -> arg.instanceMethod(rest);
	 * 方法引用为(arg 的类名为 ClassName)：
	 * ClassName :: instanceMethod;
	 */
	@Test
	public void t2() {
		Function<String, Integer> function = (s) -> s.length();
		Function<String, Integer> function1 = String :: length;
		
	}
	
	/**
	 * 指向外部对象的实例方法的方法引用
	 * (args) -> instance.instanceMethod(args)
	 * 方法引用为:
	 * instance :: instanceMethod
	 */
	@Test
	public void t3() {
		Apple apple = new Apple(20);
		Supplier<Integer> supplier = () -> apple.getWeight();
		Supplier<Integer> supplier1 = apple :: getWeight;
		System.out.println(supplier1.get());
		
		// out
		// 20
	}
	
	/**
	 * 构造方法的方法引用
	 * ClassName :: new
	 */
	@Test
	public void constructor() {
		Supplier<Apple> supplier = () -> new Apple();
		Supplier<Apple> supplier1 = Apple::new;
		
	}
	
	/**
	 * 实现 List 的排序
	 */
	@Test
	public void sort() {
		
		List<Apple> appleList = Arrays.asList(
				new Apple(10),
				new Apple(5)
		);
		
		// 1种写法
		Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);
		appleList.sort(comparator);
		appleList.forEach(a -> System.out.println(a.getWeight()));
		
		// 2种写法
		appleList.sort(Comparator.comparing(Apple::getWeight));
		
		// 逆序
		appleList.sort(Comparator.comparing(Apple::getWeight).reversed());
		appleList.forEach(a -> System.out.println(a.getWeight()));
	}
	
}
