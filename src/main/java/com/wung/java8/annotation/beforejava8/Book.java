/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.annotation.beforejava8;

/**
 * 通过定义 Authors 注解，来支持多个 Author 注解的写法。
 * 这种写法太难看。所以 java 8 支持直接写重复注解。
 *
 * @author wung 2018/8/20.
 */
@Authors(value = {
	@Author(name = "Raoul-Gabriel Urma"),
	@Author(name = "Mario Fusco")
})
public class Book {
}
