/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 原始类型特化流:IntStream, LongStream, DoubleStream.
 * 为了避免使用 Stream<Integer> 所带来的默认装箱成本。
 * 而且还提供了一些针对数值的规约接口，如：sum, max等。
 * 还可以在需要的时候转化为 Stream<T> 类型的流。
 *
 * @author wung 2018/8/10.
 */
public class IntStreamTest {
	
	
	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario","Milan");
	Trader alan = new Trader("Alan","Cambridge");
	Trader brian = new Trader("Brian","Cambridge");
	
	List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
	);
	
	/**
	 * 转化为特化流
	 */
	@Test
	public void mapToInt() {
		IntStream intStream = transactions.stream()
				.mapToInt(Transaction::getValue);
		
		// sum 默认返回0
		System.out.println("sum=" + intStream.sum());
		
		// 流只能被消费一次，所有这里需要再生成一次流
		intStream = transactions.stream()
				.mapToInt(Transaction::getValue);
		// 当流是空的时候，没有最大值，所以返回值是 OptionalInt 类型的
		OptionalInt max = intStream.max();
		System.out.println("max=" + max.orElse(0));
		
		transactions.stream()
				.mapToInt(Transaction::getValue)
				.average()
				.ifPresent(a -> System.out.println("average=" + a));
		
		
		// out
		// sum=4060
		// max=1000
		// average=676.6666666666666
	}
	
	/**
	 * 特化流转回一般流
	 */
	@Test
	public void boxed() {
		IntStream intStream = transactions.stream()
				.mapToInt(Transaction::getValue)
				.sorted();
		
		intStream.boxed().forEach(System.out::println);
		
		// out
		// 300
		// 400
		// 700
		// 710
		// 950
		// 1000
	}
	
	/**
	 * 数值范围
	 */
	@Test
	public void range() {
		// range 不包含结束值
		long count = IntStream.range(1, 5).count();
		// rangeClosed 包含结束值
		long count1 = IntStream.rangeClosed(1, 5).count();
		
		System.out.println("count=" + count);
		System.out.println("count1=" + count1);
		
		
	}
	
	
	/**
	 * 生成勾股数对（a*a + b*b = c*c）
	 */
	@Test
	public void pythagorean1() {
		Stream<double[]> doubleStream = IntStream.rangeClosed(1, 50).boxed()
				.flatMap(
						a -> IntStream.rangeClosed(a + 1, 50)
									.mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
									.filter(t -> t[2] % 1 == 0)
				)
				;
		
		doubleStream.forEach(d -> System.out.println(d[0] + "," + d[1] + "," + d[2]));
		
		// out
		// 3.0,4.0,5.0
		// 5.0,12.0,13.0
		// 6.0,8.0,10.0
		// 7.0,24.0,25.0
		// 8.0,15.0,17.0
		// 9.0,12.0,15.0
		// 9.0,40.0,41.0
		// 10.0,24.0,26.0
		// 12.0,16.0,20.0
		// 12.0,35.0,37.0
		// 14.0,48.0,50.0
		// 15.0,20.0,25.0
		// 15.0,36.0,39.0
		// 16.0,30.0,34.0
		// 18.0,24.0,30.0
		// 20.0,21.0,29.0
		// 20.0,48.0,52.0
		// 21.0,28.0,35.0
		// 24.0,32.0,40.0
		// 24.0,45.0,51.0
		// 27.0,36.0,45.0
		// 28.0,45.0,53.0
		// 30.0,40.0,50.0
		// 33.0,44.0,55.0
		// 36.0,48.0,60.0
		// 40.0,42.0,58.0
	}
	
	/**
	 * 重写勾股对（为了熟练）
	 */
	@Test
	public void pythagorean2() {
		Stream<double[]> doubleStream = IntStream.rangeClosed(1, 50).boxed()
				.flatMap(
						a -> IntStream.rangeClosed(a + 1, 50)
							.mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
							.filter(c -> c[2] % 1 == 0)
				);
		
		doubleStream.forEach(a -> System.out.println(a[0] + "," + a[1] + "," + a[2]));
	}
	
	
}
