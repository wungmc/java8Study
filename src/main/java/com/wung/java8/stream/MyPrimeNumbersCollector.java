/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义一个质数收集器，将给定的正整数n之前的所有数分区为质数和非质数。
 *
 * @author wung 2018/8/13.
 */
public class MyPrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
	
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () ->;
	}
	
	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return null;
	}
	
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return null;
	}
	
	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return null;
	}
	
	@Override
	public Set<Characteristics> characteristics() {
		return null;
	}
}
