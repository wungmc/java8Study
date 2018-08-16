/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * peek 接口是专门用来调试流的。
 * 它可以在每个元素恢复运行之前插入一个动作，针对的是单个元素。
 * 不像 forEach 等方法针对的是整个流。
 *
 * @author wung 2018/8/16.
 */
public class PeekTest {
	
	public static void main(String[] args) {
		Stream<String> stream = Stream.of("one", "two", "three", "four");
		List<String> result = stream.peek(s -> System.out.println("from stream: " + s))
				.filter(s -> s.length() > 3)
				.peek(s -> System.out.println("after filter: " + s))
				.map(s -> s.toUpperCase())
				.peek(s -> System.out.println("after map: " + s))
				.collect(Collectors.toList());
		
		System.out.println(result);
		
		// out
		
		// from stream: one
		// from stream: two
		// from stream: three
		// after filter: three
		// after map: THREE
		// from stream: four
		// after filter: four
		// after map: FOUR
		// [THREE, FOUR]
		
	}
	
}
