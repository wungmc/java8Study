/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.defaultmethod;

/**
 *
 * @author wung 2018/7/30.
 */
public class Car implements Vehicle, FourWheeler {
	/**
	 * 当多个接口都有相同的默认方法时，实现类可以覆盖、也可以指定调用哪一个
	 */
	@Override
	public void print() {
		// 指定 Vehicle 接口的 print 方法
		Vehicle.super.print();
		// 指定 FourWheeler 接口的 print 方法
		FourWheeler.super.print();
		System.out.println("im a car");
		// 静态方法直接调用
		Vehicle.horn();
	}
	
	public static void main(String[] args) {
		Vehicle car = new Car();
		car.print();
	}
	
}
