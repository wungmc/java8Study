/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

/**
 * 交易员
 *
 * @author wung 2018/8/10.
 */
public class Trader {
	
	private String name;
	private String city;
	
	public Trader(String name, String city) {
		this.name = name;
		this.city = city;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCity() {
		return city;
	}
	
	@Override
	public String toString() {
		return "Trader{" +
				"name='" + name + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}
