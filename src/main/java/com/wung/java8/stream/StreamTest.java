/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author wung 2018/8/9.
 */
public class StreamTest {
	List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH)
	);
	
	/**
	 * 流的操作主要分为两大类
	 * 1、中间操作（方法返回值为流的操作），支持链式操作，可以组成的流水线，完成更复杂的功能；
	 * 2、终端操作（方法返回值为非流的操作），触发流水线执行。必须要有终端操作。
	 */
	@Test
	public void operatorType() {
		List<String> list = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.limit(3)
				.collect(Collectors.toList());
		
		System.out.println(list);
		
		// filter, map, limit 的返回值都是Stream，所以都属于中间操作。
		// collect 的返回值为非Stream，故是终端操作。
		
		// out
		// [pork, beef, chicken]
	}
	
	/**
	 *  给定一个数字列表，返回一个由每个数的平方构成的列表
	 */
	@Test
	public void square() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squares = numbers.stream().map(n -> n * n).collect(Collectors.toList());
		System.out.println(squares);
		
		// out
		// [1, 4, 9, 16, 25]
	}
	
	/**
	 * 给定两个数字列表，返回所有数对
	 *
	 */
	@Test
	public void pairs() {
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		
		List<int[]> pairs = numbers1.stream()
				.flatMap(
						i -> numbers2.stream()
						.map(j -> new int[]{i, j})
				)
				.collect(Collectors.toList());
		
	}
	
	
	/**
	 * 对上面的例子做扩展，返回和能被3整除的数对
	 *
	 */
	@Test
	public void pairs2() {
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		
		List<int[]> pairs = numbers1.stream()
				.flatMap(
						i -> numbers2.stream()
								.filter(j -> (i + j) % 3 == 0)
								.map(j -> new int[]{i, j})
				)
				.collect(Collectors.toList());
		
	}
	
	/**
	 * 1、anyMatch：流中是否有元素匹配给定的谓词。
	 * 2、allMatch: 是否都匹配给定的谓词。
	 */
	@Test
	public void match() {
		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("有素食");
		}
		
		if (menu.stream().allMatch(Dish::isVegetarian)) {
			System.out.println("全是素食");
		}
		
		// 返回第一个元素，或空的Optional
		Optional<Dish> firstDish = menu.stream().findFirst();
		firstDish.ifPresent(dish -> System.out.println(dish.getName()));
		
		// 返回任意一个元素，或空的Optional
		// findAny 涉及到并行，效率高，如果不关心是哪个元素，就使用findAny。
		firstDish = menu.stream().findAny();
		firstDish.ifPresent(dish -> System.out.println(dish.getName()));
		
		// out
		// 有素食
		// pork
		// pork
		
	}
	
	/**
	 * 归约操作：将流中元素反复结合起来，得到一个值。
	 *
	 */
	@Test
	public void reduce() {
		// 求和
		// reduce 第一个参数是和的初始值，然后依次对流中的元素累加
		int sum = menu.stream()
				.map(Dish::getCalories)
				.reduce(0, Integer::sum);
		
		System.out.println("sum=" + sum);
		
		// 最大值
		Optional<Integer> max = menu.stream()
				.map(Dish::getCalories)
				.reduce(Integer::max);
		max.ifPresent(n -> System.out.println("max=" + n));
		
		// 求流中元素数量
		// 这就是 map-reduce 模式
		int count = menu.stream()
				.map(d -> 1)
				.reduce(0, Integer::sum);
		System.out.println("count=" + count);
		
		long count1 = menu.stream().count();
		System.out.println("count1=" + count1);
		System.out.println("count2=" + menu.size());
		
		// out
		// sum=4200
		// max=800
		// count=9
		// count1=9
		// count2=9
	}
	
	
}
