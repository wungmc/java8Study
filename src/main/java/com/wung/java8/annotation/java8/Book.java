/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.annotation.java8;

import java.util.Arrays;

/**
 * @author wung 2018/8/20.
 */
@Author(name = "Raoul-Gabriel Urma")
@Author(name = "Mario Fusco")
public class Book {
	
	public static void main(String[] args) {
		Author[] authors = Book.class.getAnnotationsByType(Author.class);
		Arrays.asList(authors).forEach(a -> System.out.println(a.name()));
		
		// out
		// Raoul-Gabriel Urma
		// Mario Fusco
	}
	
}
