/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Clock 时钟类可以获取某个时区的某个瞬时时间，取代之前的 System.currentTimeMillis().
 *
 * @author wung 2018/7/31.
 */
public class ClockTest {
	
	public static void main(String[] args) {
		Clock clock = Clock.systemUTC();
		Clock clock1 = Clock.systemDefaultZone();
		System.out.println("clock=" + clock.millis());
		System.out.println("clock1=" + clock1.millis());
		System.out.println(System.currentTimeMillis());
		
		// 瞬时，即时间戳(默认UTC，比北京时间晚8个小时)
		Instant instant = Instant.now();
		System.out.println(instant);
		System.out.println("北京时间：" + LocalDateTime.now());
		
		// out
		// clock=1533455218058
		// clock1=1533455218059
		// 1533455218059
		// 2018-08-05T07:46:58.059Z
		// 北京时间：2018-08-05T15:46:58.079
		
	}
}
