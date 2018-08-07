/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.function;

import org.junit.Test;

/**
 * lambda 表达式使用局部变量的问题
 *
 * @author wung 2018/8/7.
 */
public class ScopeTest {
	
	@Test
	public void t1() {
		// 1、使用外层作用域中的变量，这种没问题
		int portNumber = 10;
		Runnable r = () -> System.out.println(portNumber);
	}
	
	@Test
	public void t2() {
		// 2、lambda使用后改变后改变变量的值，这种无法编译。
		// 因为，lambda要求使用的局部变量必须是 final 的。
		// int portNumber = 10;
		// Runnable r = () -> System.out.println(portNumber);
		// portNumber = 11;
	}
	
	
	
}
