/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 自定义对long型数组求和的分支／合并计算框架。
 * 1、是否可在分的判断
 * 2、计算
 *
 * @author wung 2018/8/15.
 */
public class ForkJoinSum extends RecursiveTask<Long> {
	private final long[] nums;
	private final int start;
	private final int end;
	
	public ForkJoinSum(long[] nums) {
		this.nums = nums;
		this.start = 0;
		this.end = nums.length;
	}
	
	private ForkJoinSum(long[] nums, int start, int end) {
		this.nums = nums;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute() {
		int length = end - start;
		// 最小单位为 10000
		if (length <= 10000) {
			return seqSum();
		}
		
		ForkJoinSum leftTask = new ForkJoinSum(nums, start, start + length/2);
		// 左任务异步执行
		leftTask.fork();
		ForkJoinSum rightTask = new ForkJoinSum(nums, start + length/2, end);
		// 右任务同步执行
		Long right = rightTask.compute();
		// 获取left的计算结果，如果任务没有结束，则等待
		Long left = leftTask.join();
		return left + right;
	}
	
	
	private long seqSum() {
		long sum = 0L;
		for (int i = start; i < end; i++) {
			sum += nums[i];
		}
		return sum;
	}
	
	public static Long forkJoinSum(long n) {
		long[] nums = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> forkJoinSum = new ForkJoinSum(nums);
		return new ForkJoinPool().invoke(forkJoinSum);
	}
	
	public static Long forkJoinSum2(long[] nums) {
		ForkJoinTask<Long> forkJoinSum = new ForkJoinSum(nums);
		return new ForkJoinPool().invoke(forkJoinSum);
	}
	
}
