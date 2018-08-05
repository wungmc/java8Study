/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.function;

/**
 * 自定义函数式接口。
 * 实现两个数的简单计算.
 *
 * @author wung 2018/8/5.
 */
@FunctionalInterface
public interface CalculatorFunctionInterface<T> {
	
	T calculate(T t1, T t2);
	
}
