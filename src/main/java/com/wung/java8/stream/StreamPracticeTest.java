/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Stream 操作练习。
 * 领域：交易员和交易
 *
 * @author wung 2018/8/10.
 */
public class StreamPracticeTest {
	
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
	 * 找出2011年发生的所有交易，并按交易额排序（从低到高）
	 */
	@Test
	public void t1() {
		List<Transaction> t1 = transactions.stream()
				.filter(t -> t.getYear() == 2011)
				.sorted(Comparator.comparingInt(Transaction::getValue))
				.collect(toList());
		
		t1.forEach(t -> System.out.println(t.getValue()));
		
		// out
		// 300
		// 400
	}
	
	/**
	 * 交易员都在哪些不同的城市工作过
	 */
	@Test
	public void t2() {
		List<String> cityList = transactions.stream()
				.map(t -> t.getTrader().getCity())
				.distinct()
				.collect(toList());
		
		System.out.println(cityList);
	
		// out
		// [Cambridge, Milan]
	}
	
	/**
	 * 查找所有来自剑桥的交易员，并按姓名排序
	 */
	@Test
	public void t3() {
		List<Trader> traderList = transactions.stream()
				.map(Transaction::getTrader)
				.filter(t -> "Cambridge".equalsIgnoreCase(t.getCity()))
				.sorted(Comparator.comparing(Trader::getName))
				.distinct()
				.collect(toList());
		
		traderList.forEach(System.out::println);
		
		// out
		// Trader{name='Alan', city='Cambridge'}
		// Trader{name='Brian', city='Cambridge'}
		// Trader{name='Raoul', city='Cambridge'}
		
	}
	
	/**
	 * 返回所有交易员的姓名字符串，并按字母顺序排序。
	 */
	@Test
	public void t4() {
		String name = transactions.stream()
				.map(t -> t.getTrader().getName())
				.sorted()
				.distinct()
				.reduce("", (n1, n2) -> n1 + n2);
		
		System.out.println(name);
		
		// out
		// AlanBrianMarioRaoul
	}
	
	/**
	 * 有没有交易员是在米兰工作的？
	 */
	@Test
	public void t5() {
		boolean result = transactions.stream()
				.anyMatch(t -> "Milan".equalsIgnoreCase(t.getTrader().getCity()));
		
		System.out.println("result:" + result);
		
		// out
		// result:true
	}
	
	/**
	 * 打印生活在剑桥的交易员的所有交易额
	 */
	@Test
	public void t6() {
		transactions.stream()
				.filter(t -> "Cambridge".equalsIgnoreCase(t.getTrader().getCity()))
				.map(Transaction::getValue)
				.forEach(System.out::println);
		
		
		// out
		// 300
		// 1000
		// 400
		// 950
	}
	
	/**
	 * 找出最高的交易额
	 */
	@Test
	public void t7() {
		Optional<Integer> max = transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::max);
		
		max.ifPresent(m -> System.out.println("max=" + m));
		
		// out
		// max=1000
		
	}
	
	/**
	 * 找出交易额最小的交易
	 */
	@Test
	public void t8() {
		Optional<Transaction> min = transactions.stream()
				.reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
		
		min.ifPresent(m -> System.out.println("min=" + m));
		
		
		Optional<Transaction> minTransaction = transactions.stream()
				.min(Comparator.comparing(Transaction::getValue));
		
		minTransaction.ifPresent(m -> System.out.println("min=" + m));
		
		// out
		// min=Transaction{trader=Trader{name='Brian', city='Cambridge'}, year=2011, value=300}
		// min=Transaction{trader=Trader{name='Brian', city='Cambridge'}, year=2011, value=300}
	}
}
