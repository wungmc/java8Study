/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.MonthDay;
import java.time.YearMonth;

/**
 * 周期性事件的处理，如：生日，信用卡账单日等。
 *
 * @author wung 2018/7/31.
 */
public class CycleDateTest {
	
	public static void main(String[] args) {
		// MonthDay 只有月份和日，没有年的信息
		MonthDay birthday = MonthDay.of(7, 31);
		System.out.println("生日：" + birthday);
		
		MonthDay current = MonthDay.now();
		System.out.println("今天：" + current);
		
		System.out.println("今天是否生日：" + current.equals(birthday));
		
		// YearMonth 同理
		YearMonth yearMonth = YearMonth.now();
		System.out.println("年月：" + yearMonth);
		
		// out
		// 生日：--07-31
		// 今天：--07-31
		// 今天是否生日：true
		// 年月：2018-07
	}
}
