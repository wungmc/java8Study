/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.time;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 测试时区的日期时间 API.
 *
 * @author wung 2018/7/31.
 */
public class ZoneDateTimeTest {
	
	public static void main(String[] args) {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println("date1=" + zonedDateTime);
		
		ZonedDateTime zonedDateTime1 = ZonedDateTime.parse("2018-07-31T00:09:25.647+08:00[Asia/Shanghai]");
		System.out.println("date2=" + zonedDateTime1);
		
		ZoneId zoneId = ZoneId.of("Asia/Shanghai");
		System.out.println("zoneId=" + zoneId);
		
		ZoneId currentZoneId = ZoneId.systemDefault();
		System.out.println("zoneId2=" + currentZoneId);
		
		
		// out
		// date1=2018-07-31T00:12:41.539+08:00[Asia/Shanghai]
		// date2=2018-07-31T00:09:25.647+08:00[Asia/Shanghai]
		// zoneId=Asia/Shanghai
		// zoneId2=Asia/Shanghai
		
	}
}
