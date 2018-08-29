/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.completablefuture.sync;

import java.util.Random;

/**
 * 同步方法获取商品价格
 *
 * @author wung 2018/8/22.
 */
public class Shop {
	
	/**
	 * 查询商品价格
	 *
	 * @param product
	 * @return
	 */
	public double getPrice(String product) {
		return calculatePrice(product);
	}
	
	private double calculatePrice(String product) {
		// 制造延迟
		delay();
		return new Random().nextDouble() * product.charAt(0);
	}
	
	
	private static void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Shop shop = new Shop();
		double price = shop.getPrice("apple");
		System.out.println("价格： " + price + ", 耗时：" + (System.currentTimeMillis() - start));
		
		// out
		// 价格： 37.77645917250537, 耗时：1006
	}
}
