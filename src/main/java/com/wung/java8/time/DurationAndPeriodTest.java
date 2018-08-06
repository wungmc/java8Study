/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

/**
 * 时间段表示
 *
 * @author wung 2018/8/6.
 */
public class DurationAndPeriodTest {
	
	public static void main(String[] args) {
		LocalTime localTime1= LocalTime.of(2, 12, 30);
		LocalTime localTime2= LocalTime.of(2, 12, 50);
		// 两个时间之差
		Duration duration = Duration.between(localTime1, localTime2);
		System.out.println(duration.getSeconds());
		
		LocalDate localDate1 = LocalDate.of(2018, 8, 5);
		LocalDate localDate2 = LocalDate.of(2018, 8, 6);
		// 两个日期之差
		Period period = Period.between(localDate1, localDate2);
		System.out.println(period.getDays());
		
		// out
		// 20
		// 1
	}
}
