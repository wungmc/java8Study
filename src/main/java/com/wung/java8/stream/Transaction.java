/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.stream;

/**
 * 交易
 *
 * @author wung 2018/8/10.
 */
public class Transaction {
	private Trader trader;
	private int year;
	private int value;
	
	public Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}
	
	public Trader getTrader() {
		return trader;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "Transaction{" +
				"trader=" + trader +
				", year=" + year +
				", value=" + value +
				'}';
	}
}
