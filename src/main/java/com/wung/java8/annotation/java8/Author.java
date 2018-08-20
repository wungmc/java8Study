/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.annotation.java8;

import java.lang.annotation.*;

/**
 * 在注解上加上 @Repeatable 表示，该注解可以重复。
 *
 * @author wung 2018/8/20.
 */
@Repeatable(Authors.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
	String name();
}
