/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * @author wung 2018/8/14.
 */
public class MyPrimeNumbersCollector3 implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<Boolean, List<Integer>>() {{
			put(true, new ArrayList<>());
			put(false, new ArrayList<>());
		}};
	}
	
	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (map, item) -> {
			map.get(isPrime(map.get(true), item)).add(item);
		};
	}
	
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (map1, map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}
	
	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}
	
	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
	}
	
	private static boolean isPrime(List<Integer> list, int item) {
		int i = (int) Math.sqrt((double)item);
		return takeWhile(list, p -> p <= i).stream()
				.noneMatch(p -> item % p == 0);
	}
	
	private static List<Integer> takeWhile(List<Integer> list, Predicate<Integer> p) {
		int i = 0;
		for (Integer item : list) {
			if (!p.test(item)) {
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		int n = 20;
		Map<Boolean, List<Integer>> map = IntStream.rangeClosed(2, n).boxed()
				.collect(new MyPrimeNumbersCollector3());
		
		System.out.println(map);
	}
}
