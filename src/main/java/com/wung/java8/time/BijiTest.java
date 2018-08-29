/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * 整理笔记时测试用
 *
 * @author wung 2018/8/29.
 */
public class BijiTest {

	@Test
	public void t1() {
		LocalDate date = LocalDate.of(2018, 8, 29);
		System.out.println(date);
		
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();
		
		System.out.println("year=" + year);
		System.out.println("month=" + month.getValue());
		System.out.println("dow=" + dow.getValue());
		System.out.println("len=" + len);
		System.out.println("leap=" + leap);
		
		int year1 = date.get(ChronoField.YEAR);
		int month1 = date.get(ChronoField.MONTH_OF_YEAR);
		int day1 = date.get(ChronoField.DAY_OF_MONTH);
		System.out.println("year1=" + year1);
		System.out.println("month1=" + month1);
		System.out.println("day1=" + day1);
	}
	
	@Test
	public void t2() {
		LocalTime time = LocalTime.now();
		System.out.println(time);
		
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
		
		System.out.println("hour=" + hour);
		System.out.println("minute=" + minute);
		System.out.println("second=" + second);
	}
	
	@Test
	public void t3() {
		Instant instant = Instant.now();
		System.out.println(instant.getEpochSecond());
		System.out.println(System.currentTimeMillis());
		
		System.out.println(instant);
		System.out.println(LocalDateTime.now());
	}
}
