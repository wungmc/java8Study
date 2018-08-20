/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.annotation.java8;

import java.lang.annotation.*;

/**
 *
 * @author wung 2018/8/20.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authors {
	Author[] value();
}
