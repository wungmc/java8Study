/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.defaultmethod;

/**
 * 接口允许添加默认方法
 * 解决的问题：当接口修改时，不再需要修改所有其实现类。
 *
 * @author wung 2018/7/30.
 */
public interface Vehicle {
	/**
	 *	在方法前加 default 关键字
	 */
	default void print() {
		System.out.println("im a vehicle");
	}
	
	/**
	 * 也可以添加静态方法
	 */
	static void horn() {
		System.out.println("dididididi");
	}
	
}
