/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.function;

/**
 *
 * @author wung 2018/8/5.
 */
public class CalculatorTest {
	
	public static void main(String[] args) {
		// 实现加法函数
		CalculatorFunctionInterface<Integer> addCal = (a, b) -> a + b;
		// 实现乘法函数
		CalculatorFunctionInterface<Double> multCal = (a, b) -> a * b;
		System.out.println("90 + 100 = " + addCal.calculate(90, 100));
		System.out.println("90 * 100 =" + multCal.calculate(90d, 100d));
		
		// 实现函数式接口
		int d = CalculatorTest.cal(90, 100, new CalculatorFunctionInterface<Integer>() {
			@Override
			public Integer calculate(Integer t1, Integer t2) {
				return t1 - t2;
			}
		});
		System.out.println("90 - 100 = " + d);
		// lambda 写法
		d = CalculatorTest.cal(90, 100, (a, b) -> a - b);
		System.out.println("90 - 100 = " + d);
		
		// out
		// 90 + 100 = 190
		// 90 * 100 =9000.0
		// 90 - 100 = -10
		// 90 - 100 = -10
		
	}
	
	public static <T> T cal(T t1, T t2, CalculatorFunctionInterface<T> cal) {
		return cal.calculate(t1, t2);
	}
	
}
