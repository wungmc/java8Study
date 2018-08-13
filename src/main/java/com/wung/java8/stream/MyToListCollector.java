/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义收集器。
 * 将流中的所有元素归约到一个 List 集合中。
 *
 * @author wung 2018/8/13.
 */
public final class MyToListCollector<T> implements Collector<T, List<T>, List<T>> {
	
	/**
	 * 返回一个 Supplier 函数。
	 * 目的是创建累积器。
	 * @return
	 */
	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}
	
	/**
	 * 返回累积函数，该函数会依次应用到流中每个元素。
	 *
	 * @return
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}
	
	/**
	 * 合并两个结果容器，并行时才用到。
	 *
	 * @return
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		};
	}
	
	/**
	 * 最终转换函数，会在遍历完流后调用，将累积器转换为最终的返回结果。
	 * 因为这个累加器最终就是我们要返回的结果，所以直接使用恒等函数（将参数原样返回的函数）。
	 *
	 * @return
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}
	
	/**
	 * 这里标记归约是否可以并行，以及优化策略。
	 * <p>
	 * 主要有三种：
	 * 1、UNORDER: 元素是否有顺序。
	 * 2、CONCURRENT: 是否可以并行归约（前提元素必须是无顺序的）
	 * 3、IDENTITY_FINISH: 标记finisher函数是不是恒等函数，如果是，则可以放心的直接返回累积器，不用转换了。
	 * @return
	 */
	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
	}
	
}
