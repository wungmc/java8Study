/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.libs;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 类库新增接口的测试
 *
 * @author wung 2018/8/21.
 */
public class LibsTest {
	
	private static Map<String, Integer> map = new HashMap<>();
	static {
		map.put("apple", 5);
		map.put("banana", null);
	}
	
	@Test
	public void oldMap() {
		System.out.println(map.isEmpty());
		System.out.println(map.containsKey("apple"));
		System.out.println(map.containsKey("banana"));
		System.out.println(map.get("banana"));
		System.out.println(map.containsKey("pear"));
		System.out.println(map.get("pear"));
		
		
		// out
		// false
		// true
		// true
		// null
		// false
		// null
	}
	
	@Test
	public void newMap() {
		System.out.println(map.getOrDefault("banana", 6));
		System.out.println(map.getOrDefault("pear", 4));
		map.forEach((k, v) -> System.out.println(k + "->" + v));
		
		System.out.println(map.computeIfAbsent("pear", String::length));
		
		map.replaceAll((k, v) -> v == null? 0 : v + 1);
		System.out.println(map);
		
		// out
		// null
		// 4
		// banana->null
		// apple->5
		// 4
		// {banana=0, apple=6, pear=5}
		
	}
	
	@Test
	public void arrays() {
		Integer[] nums = new Integer[10];
		// i 是下标
		Arrays.setAll(nums, i -> i * 2);
		
		// parallelSetAll 是并发执行
		// Arrays.parallelSetAll(nums, i -> i * 2);
		
		System.out.println(Arrays.asList(nums));
		
		
		// out
		// [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
	}
	
	@Test
	public void string() {
		System.out.println(String.join(",", "A", "B", "C"));
		
		String[] s = {"A", "B", "C"};
		System.out.println(String.join("+", s));
		
		List<String> s1 = Arrays.asList("A", "B", "C");
		System.out.println(String.join("-", s1));
		
		// out
		// A,B,C
		// A+B+C
		// A-B-C
	}
	
	
	@Test
	public void random() {
		Random random = new Random();
		List<Integer> list = random.ints().limit(5).boxed().collect(Collectors.toList());
		System.out.println(list);
		
		list = random.ints(5).boxed().collect(Collectors.toList());
		System.out.println(list);
		
		// out
		// [741058607, 928253881, -982754982, -1580006629, 1276325701]
		// [-1300186560, -463737391, 471078614, -1383403339, 200942855]
		
	}
	
}
