/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.completablefuture.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步方法获取商品价格。
 * 使用CompletableFuture 的工厂方法创建 CompletableFuture 。
 *
 * @author wung 2018/8/22.
 */
public class Shop3 {
	
	/**
	 * 查询商品价格
	 *
	 * @param product
	 * @return
	 */
	public Future<Double> getPriceAsync(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
		// 上面这句和下面这些代码创建的 CompletableFuture 完全等价。
		
		// CompletableFuture<Double> priceFuture = new CompletableFuture<>();
		// new Thread(
		// 		() -> {
		// 			// 异常处理，
		// 			// 抛出去，防止杀死本进程，导致外部线程一直等待，而且拿不到异常信息
		// 			try {
		// 				double price = calculatePrice(product);
		// 				priceFuture.complete(price);
		// 			} catch (Exception e) {
		// 				priceFuture.completeExceptionally(e);
		// 			}
		// 		}
		// ).start();
		//
		// return priceFuture;
	}
	
	private double calculatePrice(String product) {
		// 制造延迟
		delay();
		throw new RuntimeException("商品不存在");
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
		Shop3 shop = new Shop3();
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
		/*
			获取结果前耗时：146
			其它工作结束，耗时：1148
			java.util.concurrent.ExecutionException: java.lang.RuntimeException: 商品不存在
			所有工作结束，耗时：1150
			at java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:357)
			at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1915)
			at com.wung.java8.completablefuture.async.Shop2.main(Shop2.java:70)
			Caused by: java.lang.RuntimeException: 商品不存在
			at com.wung.java8.completablefuture.async.Shop2.calculatePrice(Shop2.java:47)
			at com.wung.java8.completablefuture.async.Shop2.lambda$getPriceAsync$0(Shop2.java:33)
			at java.lang.Thread.run(Thread.java:748)
		*/
	}
	
	private static void doOtherThing() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
