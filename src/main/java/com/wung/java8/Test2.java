/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8;

import javax.xml.bind.SchemaOutputResolver;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author wung 2018/8/23.
 */
public class Test2 {
	
	public static void main(String[] args) {
		Consumer<Integer> c1 = i -> i=i+1;
		Function<Integer, Integer> c2 = i -> i=i+1;
		Function<String, Integer> c3 = (s1) -> s1.compareTo("s");
		Consumer<String> c4 = (s1) -> s1.compareTo("s");
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("");
			}
		}).start();
		
		new Thread(() -> System.out.println("")).start();
		
		UnaryOperator<Integer> uo = n -> n * 2;
	}
}
