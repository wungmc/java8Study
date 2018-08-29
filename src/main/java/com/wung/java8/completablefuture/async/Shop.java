/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.completablefuture.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步方法获取商品价格
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
	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> priceFuture = new CompletableFuture<>();
		
		new Thread(
				() -> {
					double price = calculatePrice(product);
					priceFuture.complete(price);
				}
		).start();
		
		return priceFuture;
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
		Future<Double> priceFuture = shop.getPriceAsync("apple");
		System.out.println("获取结果前耗时：" + (System.currentTimeMillis() - start));
		
		doOtherThing();
		System.out.println("其它工作结束，耗时：" + (System.currentTimeMillis() - start));
		
		try {
			// 如果结果还没有计算出来，则最多等待1秒后退出。
			double price = priceFuture.get(1, TimeUnit.SECONDS);
			System.out.println("价格：" + price);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("所有工作结束，耗时：" + (System.currentTimeMillis() - start));
		
		// out
		// 获取结果前耗时：183
		// 其它工作结束，耗时：1187
		// 价格：21.76365751052288
		// 所有工作结束，耗时：1191
	}
	
	private static void doOtherThing() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
