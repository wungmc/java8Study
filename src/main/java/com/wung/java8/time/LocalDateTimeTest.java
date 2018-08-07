/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * java.time 包下提供了关于日期、时间、日期／时间、时区、时刻、时钟（Clock）的操作.
 * 该包下很多类都是不可变的，确保了线程安全。
 *
 * <p>
 * 本类是测试本地化日期时间 API 的.
 *
 * @author wung 2018/7/30.
 */
public class LocalDateTimeTest {
	
	public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("date1= " + localDateTime);
		
		LocalDate localDate = localDateTime.toLocalDate();
		System.out.println("date2=" + localDate);
		
		int year = localDateTime.getYear();
		Month month = localDateTime.getMonth();
		int day = localDateTime.getDayOfMonth();
		int hour = localDateTime.getHour();
		System.out.println("date3=" + year + "," + month.getValue() + "," + day + ":" + hour);
		
		LocalDateTime localDateTime1 = localDateTime.plusDays(2);
		System.out.println("date4=" + localDateTime1);
		// 也可以使用这个方法：plus(2, ChronoUnit.DAYS);
		
		LocalDateTime localDateTime2 = localDateTime.withYear(2017).withDayOfMonth(6);
		System.out.println("date5=" + localDateTime2);
		
		System.out.println();
		
		// LocalDate
		LocalDate localDate1 = LocalDate.now();
		System.out.println("date6=" + localDate1);
		
		LocalDateTime localDateTime3 = localDate1.atTime(2, 20);
		System.out.println("date7=" + localDateTime3);
		
		LocalDate localDate2 = LocalDate.of(2020, 10, 20);
		System.out.println("date8=" + localDate2);
		
		System.out.println();
		
		// LocalTime
		LocalTime localTime = LocalTime.parse("02:10:39");
		System.out.println("date9=" + localTime);
		
		
		// out
		// date1= 2018-07-31T00:06:47.434
		// date2=2018-07-31
		// date3=2018,7,31:0
		// date4=2018-08-02T00:06:47.434
		// date5=2017-07-06T00:06:47.434
		//
		// date6=2018-07-31
		// date7=2018-07-31T02:20
		// date8=2020-10-20
		//
		// date9=02:10:39
		
	}
	
}
