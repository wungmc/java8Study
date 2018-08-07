/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * 有的时候，你需要进行一些更加 复杂的操作，比如，将日期调整到下个 日、下个工作日，或者是本月的最后一天。
 * 这时，你可以使用重载版本的with方法，向其传递一个提供了更多定制化选择的TemporalAdjuster对象， 更加灵活地处理日期。
 *
 * @author wung 2018/8/6.
 */
public class TemporalAdjusterTest {
	
	public static void main(String[] args) {
		LocalDate localDate = LocalDate.of(2018, 8, 6);
		// TemporalAdjusters 已经提供了一些方法
		LocalDate localDate1 = localDate.with(TemporalAdjusters.firstDayOfMonth());
		// 该月的第3周的星期五
		LocalDate localDate2 = localDate.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.FRIDAY));
		System.out.println(localDate1);
		System.out.println(localDate2);
		
		
		
	}
}
