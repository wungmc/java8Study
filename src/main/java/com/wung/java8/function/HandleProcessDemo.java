/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.function;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 函数式接口改造责任了设计模式。
 *
 * @author wung 2018/8/16.
 */
public class HandleProcessDemo {
	
	public static void main(String[] args) {
		UnaryOperator<String> headerProcess = (String text) -> "From jack : " + text;
		UnaryOperator<String> spellCheckerProcess = (String text) -> text.replaceAll("lamda", "lambda");
		UnaryOperator<String> tailerProcess = (String text) -> text + " Sincerely. ";
		
		Function<String, String> handler = headerProcess.andThen(spellCheckerProcess).andThen(tailerProcess);
		System.out.println(handler.apply("Hello, lamda."));
		
		// out
		// From jack : Hello, lambda. Sincerely.
		
	}
	
}
