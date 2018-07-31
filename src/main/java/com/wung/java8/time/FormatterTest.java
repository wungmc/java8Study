/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式化
 *
 * @author wung 2018/7/31.
 */
public class FormatterTest {
	
	public static void main(String[] args) {
		String tommorrow = "20180801";
		// 使用默认的DateTimeFormatter.BASIC_ISO_DATE格式化
		LocalDate localDate = LocalDate.parse(tommorrow, DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("date1=" + localDate);
		
		// 自定义格式
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");
		LocalDate localDate1 = LocalDate.parse("2018 08 01", dateTimeFormatter);
		System.out.println("date2=" + localDate1);
		
		
		// 日期转字符串
		String localDate2 = dateTimeFormatter.format(localDate1);
		System.out.println("date3=" + localDate2);
		
		// out
		// date1=2018-08-01
		// date2=2018-08-01
		// date3=2018 08 01
		
	}
}
