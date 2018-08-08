/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.defaultmethod;

/**
 * 默认方法带来的多继承问题。
 * 如果一个类使用相同的函数签名从多处（类或接口）继承了方法，那么到底调用的是哪个方法呢？
 * 判断原则有三条：
 * 1、类中声明的方法的优先级高。类或父类中声明的方法的优先级高于任何接口中声明的默认方法；
 * 2、如果第一条无法判断，那么子接口中的方法优先级高。（优先选择拥有最具体实现的方法，越往上越抽象，越往下越具体）；
 * 3、如果第二条无法判断，则需要显示的覆盖或指定调用的方法。
 *
 * @author wung 2018/8/8.
 */
public class MultiExtendTest {
	
	public static void main(String[] args) {
		MultiExtendTest test = new MultiExtendTest();
		test.new C().hello();
		test.new E().hello();
		test.new F().hello();
		test.new I().hello();
		
		// out
		// hello from B
		// hello from B
		// hello from F
		// hello from I
		// hello from A
	}
	
	interface IA {
		default void hello() {
			System.out.println("hello from A");
		}
	}
	
	interface IB extends IA {
		@Override
		default void hello() {
			System.out.println("hello from B");
		}
	}
	
	class C implements IB, IA {}
	
	class D implements IA {}
	class E extends D implements IB, IA {}
	
	class F implements IA {
		@Override
		public void hello() {
			System.out.println("hello from F");
		}
	}
	class G extends F implements IB, IA {}
	
	interface IH {
		default void hello() {
			System.out.println("hello from H");
		}
	}
	class I implements IH, IA {
		// 这里必须重写 hello 方法，编译器无法判断选哪个
		@Override
		public void hello() {
			System.out.println("hello from I");
			
			// 或者显示的指定调用哪个（java8新语法）
			IA.super.hello();
		}
	}
}
