/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * 时间间隔
 *
 * @author wung 2018/8/6.
 */
public class DurationAndPeriodTest {
	
	public static void main(String[] args) {
		LocalTime localTime1= LocalTime.of(2, 12, 30);
		LocalTime localTime2= LocalTime.of(2, 12, 50);
		// 两个时间之差(以秒、纳秒衡量时间，所以不能传LocalDate做参数)
		Duration duration = Duration.between(localTime1, localTime2);
		System.out.println(duration.getSeconds());
		
		LocalDate localDate1 = LocalDate.of(2018, 8, 5);
		LocalDate localDate2 = LocalDate.of(2018, 8, 6);
		// 两个日期之差
		Period period = Period.between(localDate1, localDate2);
		System.out.println(period.getDays());
		
		
		// 其它工厂方法
		Duration tenSeconds = Duration.ofSeconds(10);
		Duration tenSeconds1 = Duration.of(10, ChronoUnit.SECONDS);
		System.out.println(tenSeconds1.getSeconds());
		
		Period sevenDays = Period.ofDays(7);
		Period oneWeek = Period.ofWeeks(1);
		Period twoYearsSixMonthsFiveDays = Period.of(2, 6, 5);
		System.out.println(twoYearsSixMonthsFiveDays.getMonths());
		
		// out
		// 20
		// 1
		// 10
		// 6
	}
}
