/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * 自定义 TemporalAdjuster ：下个工作日。
 * 原理就是重写 adjustInto() 方法，将一个temporal 变为另一个。
 *
 * @author wung 2018/8/7.
 */
public class NextWorkingDay implements TemporalAdjuster {
	@Override
	public Temporal adjustInto(Temporal temporal) {
		DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		int dayToadd = 1;
		if (dayOfWeek == DayOfWeek.FRIDAY) {
			dayToadd = 3;
		}
		else if (dayOfWeek == DayOfWeek.SATURDAY) {
			dayToadd = 2;
		}
		
		return temporal.plus(dayToadd, ChronoUnit.DAYS);
	}
	
	
	public static void main(String[] args) {
		LocalDate localDate = LocalDate.of(2018, 8, 3);
		System.out.println(localDate);
		
		LocalDate localDate1 = localDate.with(new NextWorkingDay());
		System.out.println(localDate1);
		
		// out
		// 2018-08-03
		// 2018-08-06
		
	}
	
}
