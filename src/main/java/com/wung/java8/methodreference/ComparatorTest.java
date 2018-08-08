/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.methodreference;


import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 比较器
 *
 * @author wung 2018/8/8.
 */
public class ComparatorTest {
	
	private List<Apple> appleList = Arrays.asList(
			new Apple(10),
			new Apple(5),
			new Apple(15)
	);
	
	/**
	 * List 排序
	 */
	@Test
	public void sort() {
		// 1种写法
		Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);
		appleList.sort(comparator);
		appleList.forEach(a -> System.out.println(a.getWeight()));
		
		// 2种写法
		// appleList.sort(Comparator.comparing(Apple::getWeight));
		
		// 逆序
		appleList.sort(Comparator.comparing(Apple::getWeight).reversed());
		appleList.forEach(a -> System.out.println(a.getWeight()));
		
		// 链式操作：如果重量相同，则按颜色排序
		appleList.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));
	}
	
}
