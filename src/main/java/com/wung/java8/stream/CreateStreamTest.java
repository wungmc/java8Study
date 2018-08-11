/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 构建流
 *
 * @author wung 2018/8/11.
 */
public class CreateStreamTest {
	
	@Test
	public void of() {
		// 空的流
		Stream stream = Stream.empty();
		
		// of 直接创建
		Stream<String> stringStream = Stream.of("java", "lambda", "stream");
		stringStream.map(String::toUpperCase).forEach(System.out::println);
		
		// 通过数组创建
		int[] nums = new int[]{2, 4, 5, 6};
		IntStream intStream = Arrays.stream(nums);
		System.out.println("sum=" + intStream.sum());
		
		// out
		// JAVA
		// LAMBDA
		// STREAM
		// sum=17
	}
	
	/**
	 * test 问题内容：
	 */
	@Test
	public void fromFile() {
		try (
				Stream<String> lines = Files.lines(Paths.get("/Users/wungmc/Desktop","test"), Charset.defaultCharset())
				) {
			long count = lines.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();
			System.out.println("不同单词个数：" + count);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// out
		// 不同单词个数：5
	}
	
	/**
	 * 使用 interate 生成一系列值
	 */
	@Test
	public void interate() {
		// 通过一个lambda创建一个无限流
		// 无限流一定要配合limit使用，防止无限打印
		List<Integer> list = Stream.iterate(0, a -> a + 2)
				.limit(10)
				.collect(Collectors.toList());
		System.out.println(list);
		
		// out
		// [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
	}
	
	/**
	 * 使用 interate 生成斐波那契数元组序列的前20列:
	 * (0, 1),(1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21)
	 */
	@Test
	public void fibGroup() {
		Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]})
				.limit(20)
				.forEach(a -> System.out.println("(" + a[0] + ", " + a[1] + ")"));
		
		// out
		// (0, 1)
		// (1, 1)
		// (1, 2)
		// (2, 3)
		// (3, 5)
		// (5, 8)
		// (8, 13)
		// (13, 21)
		// (21, 34)
		// (34, 55)
		// (55, 89)
		// (89, 144)
		// (144, 233)
		// (233, 377)
		// (377, 610)
		// (610, 987)
		// (987, 1597)
		// (1597, 2584)
		// (2584, 4181)
		// (4181, 6765)
	}
	
	/**
	 * 变相生成斐波那契数列
	 */
	@Test
	public void fib() {
		List<Integer> list = Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]})
				.limit(10)
				.map(a -> a[0])
				.collect(Collectors.toList());
		
		System.out.println(list);
		
		// out
		// [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
		
	}
	
	/**
	 * 使用 generate 生成无限流。
	 * 也要配合 limit 使用。
	 */
	@Test
	public void generate() {
		Stream.generate(Math::random)
				.limit(5)
				.forEach(System.out::println);
		
		// 使用 IntSteam 避免装箱
		IntStream.generate(() -> 1)
				.limit(6)
				.forEach(a -> System.out.print(a + ","));
		
		// out
		// 0.2600664537360652
		// 0.5357954645521844
		// 0.8833378957268412
		// 0.2563684378270399
		// 0.6428879696368965
		// 1,1,1,1,1,1,
	}
	
	/**
	 * @deprecated
	 * 尽量不要使用这种方法：因为对象有可变的状态。（上面的fib()则没有可变的状态，可以并行处理）
	 * 这样不利于并行。
	 */
	@Deprecated
	@Test
	public void fibByGenerate() {
		IntStream.generate(new IntSupplier() {
			private int previous = 0;
			private int current = 1;
			@Override
			public int getAsInt() {
				int ret = this.previous;
				int next = this.previous + this.current;
				this.previous = this.current;
				this.current = next;
				return ret;
			}
		})
				.limit(6)
				.forEach(System.out::println);
				
	}
}
