/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

import org.junit.Test;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author wung 2018/8/14.
 */
public class PerformanceTest {
	
	/**
	 * 测试框架。
	 * 因为 JIT 有预热，故多运行几次。
	 */
	public static long testFramework(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			
			long sum = adder.apply(n);
			
			long duration = (System.nanoTime() - start) / 1000000;
			if (duration < fastest) {
				fastest = duration;
			}
			System.out.println("和：" + sum);
		}
		return fastest;
	}
	
	/**
	 * 顺序求自然数前n的和。
	 */
	public static long seqSum(long n) {
		return Stream.iterate(1L, a -> a + 1)
				.limit(n)
				.reduce(0L, Long::sum);
	}
	
	/**
	 * 普通迭代求自然数前n的和。
	 */
	public static long iterativeSum(long n) {
		long sum = 0L;
		for (long j = 1L; j <= n; j++) {
			sum += j;
		}
		return sum;
	}
	
	
	/**
	 * 并行流求自然数前n的和。
	 */
	public static long parallelSum(long n) {
		long sum = Stream.iterate(1L, a -> a + 1)
				.limit(n)
				.parallel()
				.reduce(0L, Long::sum);
		return sum;
	}
	
	
	/**
	 * LongStream 并行流求自然数前n的和。
	 */
	public static long longStreamParalleSum(long n) {
		long sum = LongStream.rangeClosed(1L, n).parallel().reduce(0L, Long::sum);
		return sum;
	}
	
	
	@Test
	public void seqSumTest() {
		System.out.println("seqSumTest耗时：" + testFramework(PerformanceTest::seqSum, 1000_0000));
		
		// out
		// seqSumTest耗时：125
	}
	
	@Test
	public void iterativeSumTest() {
		System.out.println("iterativeSumTest耗时：" + testFramework(PerformanceTest::iterativeSum, 1000_0000));
		
		// out
		// iterativeSumTest耗时：4
	}
	
	@Test
	public void parallelSumTest() {
		System.out.println("parallelSumTest耗时：" + testFramework(PerformanceTest::parallelSum, 1000_0000));
		
		// out
		// parallelSumTest耗时：390
		
		// 可以看出这种方法——使用 iterate 方法生成的流进行并行计算——是最低效。
		// 因为 iterate 是以函数来生成流，流的每一项都依赖前一项，流是顺序的，所以不适合分块计算，并行计算反而更低效。
	}
	
	@Test
	public void longStreamParalleSumTest() {
		System.out.println("longStreamParalleSumTest耗时：" + testFramework(PerformanceTest::longStreamParalleSum, 1000_0000));
		
		// out
		// longStreamParalleSumTest耗时：3
	}
	
	@Test
	public void forkJoinSumTest() {
		System.out.println("forkJoinSumTest耗时：" + testFramework(ForkJoinSum::forkJoinSum, 1000_0000));
		
		
		// out
		// forkJoinSumTest耗时：93
	}
	
	@Test
	public void forkJoinSumTest2() {
		long[] nums = LongStream.rangeClosed(1, 1000_0000).toArray();
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			
			long sum = ForkJoinSum.forkJoinSum2(nums);
			
			long duration = (System.nanoTime() - start) / 1000000;
			if (duration < fastest) {
				fastest = duration;
			}
			System.out.println("和：" + sum);
		}
		System.out.println("forkJoinSumTest2耗时：" + fastest);
		
		// out
		// forkJoinSumTest2耗时：4
		
		// 注意与上例的区别，上面的例子中，大部分时间都是花在了准备数组上。
		// 本例中，把数组准备的时间去掉后，效率就看出来了。
		
	}
}