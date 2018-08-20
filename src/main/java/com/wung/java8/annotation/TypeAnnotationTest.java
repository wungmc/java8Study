/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.annotation;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

/**
 * @author wung 2018/8/20.
 */
public class TypeAnnotationTest {
	
	class Book {
		String name;
	}
	
	@Test
	public void type() {
		Book book = new Book();
		book.name = "java8 in action";
		
		@NotNull String name = book.name;
		
		System.out.println(name);
		
		// out
		// java8 in action
	}
	
	@Test
	public void type2() {
		Book book = new Book();
		
		// 这个只是个标记（加不加，在运行时是一样的），只是让你知道这个变量不为 null 而已。
		@NotNull String name = book.name;
		
		System.out.println(name);
		
		// out
		// null
	}
	
}
