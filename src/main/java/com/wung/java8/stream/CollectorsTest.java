/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 预定义收集器，Collectors 提供（collect 高级归约，只是reducing 的特殊情况）.
 * 主要功能分三类：
 * 1、归约和汇总为一个值
 * 2、对流元素分组
 * 3、对流元素分区（可以看作特殊的分组）
 *
 *
 * @author wung 2018/8/12.
 */
public class CollectorsTest {
	
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
	 * 对交易按交易员分类
	 * java8之前的写法
	 */
	@Test
	public void t1() {
		Map<Trader, List<Transaction>> transactionByTrader = new HashMap<>();
		for(Transaction transaction : transactions) {
			Trader trader = transaction.getTrader();
			List<Transaction> list = transactionByTrader.get(trader);
			if (list == null) {
				list = new ArrayList<>();
				transactionByTrader.put(trader, list);
			}
			list.add(transaction);
		}
	}
	
	/**
	 * 对交易按交易员分类.
	 * 使用 java8 的收集器改写
	 */
	@Test
	public void groupingBy() {
		Map<Trader, List<Transaction>> transactionByTrader = transactions.stream()
				.collect(Collectors.groupingBy(Transaction::getTrader));
	}
	
	/**
	 * 返回交易员姓名列表
	 */
	@Test
	public void toList() {
		List<String> nameList = transactions.stream()
				.map(t -> t.getTrader().getName())
				.distinct()
				.collect(Collectors.toList());
		
		System.out.println(nameList);
		
		// out
		// [Brian, Raoul, Mario, Alan]
	}
	
	/**
	 * 求流中元素数量
	 */
	@Test
	public void counting() {
		long count = transactions.stream()
				.collect(Collectors.counting());
		System.out.println("count=" + count);
		
		// 上面只是演示用，实际求数量用下面的
		// int size = transactions.size();
		// count = transactions.stream().count();
		
		
		// out
		// count=6
	}
	
	/**
	 * 求最大值最小值
	 */
	@Test
	public void maxBy() {
		Optional<Transaction> maxTransaction = transactions.stream()
				.collect(Collectors.maxBy(Comparator.comparingInt(Transaction::getValue)));
		
		maxTransaction.ifPresent(t -> System.out.println("max=" + t.getValue()));
		
		// 这个方法更简单些
		// Optional<Transaction> maxTransaction transactions.stream()
		// 		.max(Comparator.comparingInt(Transaction::getValue));
		
		// minBy 类似
		
		// out
		// max=1000
	}
	
	/**
	 * 汇总操作
	 */
	@Test
	public void summingInt() {
		// summingInt 默认返回0
		int sum = transactions.stream()
				.collect(Collectors.summingInt(Transaction::getValue));
		System.out.println("sum=" + sum);
		
		// 实际求和时，应该用下面这种方法
		sum = transactions.stream()
				.mapToInt(Transaction::getValue).sum();
		System.out.println("sum=" + sum);
		
		// out
		// sum=4060
		// sum=4060
	}
	
	/**
	 * 平均值
	 */
	@Test
	public void averagingInt() {
		Double average = transactions.stream()
				.collect(Collectors.averagingInt(Transaction::getValue));
		
		System.out.println("average=" + average);
		
		// out
		// average=676.6666666666666
	}
	
	/**
	 * 一次得到多个结果：和、平均值、最大、最小值等
	 */
	@Test
	public void summarizingInt() {
		IntSummaryStatistics summaryStatistics = transactions.stream()
				.collect(Collectors.summarizingInt(Transaction::getValue));
		
		System.out.println(summaryStatistics);
		
		// out
		// IntSummaryStatistics{count=6, sum=4060, min=300, average=676.666667, max=1000}
	}
	
	/**
	 * 字符串连接
	 */
	@Test
	public void joining() {
		String[] str = new String[]{"java8", "in", "action"};
		String join = Arrays.stream(str)
				.collect(Collectors.joining());
		
		System.out.println("join=" + join);
		
		join = Arrays.stream(str)
				.collect(Collectors.joining(", "));
		
		System.out.println("join1=" + join);
		
		
		join = Arrays.stream(str)
				.collect(Collectors.joining(",", "(", ")"));
		
		System.out.println("join1=" + join);
		
		
		// out
		// join=java8inaction
		// join1=java8, in, action
		// join1=(java8,in,action)
	}
	
	
	/**
	 * 更普适的规约方法.
	 * Collectors 提供的其它归约方法，可以看作是 reducing 的特殊情况。
	 * 类似，updateName() 之于 update().
	 *
	 */
	@Test
	public void reducing() {
		// 求总的交易量
		// 上面是这么做的
		Integer sum = transactions.stream()
				.collect(Collectors.summingInt(Transaction::getValue));
		System.out.println("sum=" + sum);
		
		// 也可以使用 reducing
		// 归约的操作原理：利用累积函数，把一个初始化为起始值的累加器，和把转换函数应用到流中每个元素上得到的值不断迭代合并到一起。
		// 0： 起始值
		// Transaction::getValue ：转换函数
		// Integer::sum : 累积函数
		sum = transactions.stream()
				.collect(Collectors.reducing(0, Transaction::getValue, Integer::sum));
		
		System.out.println("sum1=" + sum);
		
		// 看看 Collector.counting 收集器的jdk源码，就是利用的 reducing
		// public static <T> Collector<T, ?, Long> counting() {
		// 	return reducing(0L, e -> 1L, Long::sum);
		// }
		
	}
	
	/**
	 * 按交易额大小分类
	 */
	@Test
	public void groupingBy2() {
		Map<String, List<Transaction>> map = transactions.stream().collect(
				Collectors.groupingBy(t -> {
					if (t.getValue() > 900) {
						return "HIGH";
					}
					if (t.getValue() > 500) {
						return "MIDDLE";
					}
					return "LOW";
				})
		);
		
		System.out.println(map);
	}
	
	/**
	 * 按交易员和交易额进行二级分组
	 */
	@Test
	public void groupingBy3() {
		Map<Trader, Map<String, List<Transaction>>> map =
				transactions.stream().collect(
						Collectors.groupingBy(Transaction::getTrader,
								Collectors.groupingBy(
										t -> {
											if (t.getValue() > 900) {
												return "HIGH";
											}
											if (t.getValue() > 500) {
												return "MIDDLE";
											}
											return "LOW";
										}
								)
						)
				);
		
		// groupingBy 第二参数可以是任意类型的收集器
		Map<Trader, Long> map1 =
				transactions.stream().collect(
						Collectors.groupingBy(Transaction::getTrader,
								Collectors.counting()
								)
				);
		
		System.out.println(map1);
		
		// 每一个交易员的交易总额
		Map<Trader, Integer> map2 = transactions.stream().collect(
				Collectors.groupingBy(Transaction::getTrader,
						Collectors.summingInt(Transaction::getValue)
						)
		);
		
		// out
		// {Trader{name='Mario', city='Milan'}=2, Trader{name='Alan', city='Cambridge'}=1, Trader{name='Brian', city='Cambridge'}=1, Trader{name='Raoul', city='Cambridge'}=2}
	}
	
	/**
	 * 找出每个交易员最高的交易
	 */
	@Test
	public void groupingBy4() {
		Map<Trader, Optional<Transaction>> map = transactions.stream().collect(
				Collectors.groupingBy(Transaction::getTrader,
						Collectors.maxBy(Comparator.comparingInt(Transaction::getValue))
						)
		);
		
		// 上面的结果的值为 Optional类型，其实是没有必要的，因为这个值是肯定存在的。
		// 下面就用 Collectors.collectingAndThen 进行改进
		
	}
	
	
	/**
	 * collectingAndThen 就是对收集器包装了一下。
	 */
	@Test
	public void collectingAndThen() {
		Map<Trader, Transaction> map = transactions.stream().collect(
				Collectors.groupingBy(Transaction::getTrader,
						// collectingAndThen 收集器的第二个参数是转换函数，
						// 对第一个参数收集器的结果进行转换，并返回一个收集器
						Collectors.collectingAndThen(
							Collectors.maxBy(Comparator.comparingInt(Transaction::getValue)),
							Optional::get
						)
				)
		);
		
		
	}
	
	
	@Test
	public void groupingAndMapping() {
		Map<Trader, Set<String>> map = transactions.stream().collect(
				Collectors.groupingBy(Transaction::getTrader,
						// mapping 的第一个参数是转换函数，负责将每个交易员下的交易转换成交易额
						// 第二个参数是收集器，负责将第一个转换函数转换的结果进行规约收集
						Collectors.mapping(
								v -> {
									if (v.getValue() > 900) {
										return "HIGH";
									}
									if (v.getValue() > 500) {
										return "MIDDLE";
									}
									return "LOW";
								},
								// toSet() 默认使用 HashSet
								Collectors.toSet()
								// 也可以指定集合类型
								// Collectors.toCollection(HashSet::new)
								)
						)
		);
	}
	
	/**
	 * 测试自定义收集器 MyToListCollector
	 */
	@Test
	public void myToListCollector() {
		List<Integer> valueList = transactions.stream()
				.map(Transaction::getValue)
				.collect(new MyToListCollector<>());
		
		System.out.println(valueList);
		
		
		// out
		// [300, 1000, 400, 710, 700, 950]
	}
	
	
	/**
	 * 也可以将收集器写到 collect 接口里，不用显示的先定义。
	 * （不过，不推荐这种写法）
	 */
	@Test
	public void myToListCollector2() {
		List<Integer> valueList = transactions.stream()
				.map(Transaction::getValue)
				.collect(ArrayList::new,
						List::add,
						ArrayList::addAll
						);
		
		System.out.println(valueList);
		
		
		// out
		// [300, 1000, 400, 710, 700, 950]
	}
	
	
}
