/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.optional;

import com.wung.java8.optional.model.Car;
import com.wung.java8.optional.model.Insurance;
import com.wung.java8.optional.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional 用法 Demo。
 *
 * Optional 是一个容器，里面封装一个对象，这个对象可能存在也可能不存在。
 * 核心原理就是对不存在(null)的情况进行封装建模，而不是直接赋个 null。
 *
 * @author wung 2018/8/2.
 */
public class OptionalTest {
	
	private Person person;
	
	@Before
	public void init() {
		person = new Person();
	}
	
	/**
	 * 0、获取一个人的汽车所使用的保险的保险公司名字
	 *
	 * 问题：可能抛空指针异常
	 * @return
	 */
	@Test
	public void geInsuranceName0() {
		String name = person.getCar().getInsurance().getName();
		System.out.println(name);
	}
	
	/**
	 * 1、java 8 之前的解决方案
	 *
	 * 问题：到处都是 null 检查，代码臃肿丑陋
	 * @return
	 */
	@Test
	public void getInsuranceName1() {
		if (person != null) {
			Car car = person.getCar();
			if (car != null) {
				Insurance insurance = car.getInsurance();
				if (insurance != null) {
					System.out.println(insurance.getName());
				}
			}
		}
	}
	
	/**
	 * 2、java 8 之前的改进方案2（用谓语句来避免多次嵌套）
	 *
	 * 问题：仍然过多的 null 检查，多个退出点
	 * @return
	 */
	@Test
	public void getInsuranceName2() {
		if (person == null) {
			return;
		}
		Car car = person.getCar();
		if (car == null) {
			return;
		}
		Insurance insurance = car.getInsurance();
		if (insurance == null) {
			return;
		}
		System.out.println(insurance.getName());
	}
	
	/**
	 * 3、使用 java 8 的 Optional 解决。
	 *
	 */
	@Test
	public void getInsuranceName3() {
		Insurance insurance = new Insurance();
		insurance.setName("平安");
		Car car = new Car();
		car.setInsurance(insurance);
		Person person1 = new Person();
		person1.setCar(car);
		
		// 分步写
		Optional<Person> personOptional = Optional.ofNullable(person1);
		Optional<Car> carOptional = personOptional.map(Person::getCar);
		Optional<Insurance> insuranceOptional = carOptional.map(Car::getInsurance);
		Optional<String> name = insuranceOptional.map(Insurance::getName);
		// 如果值存在，就执行使用该值的方法调用，否则什么也不做
		name.ifPresent(System.out::println);
		
		// 链式写法（推荐）
		String name1 = Optional.ofNullable(person1)
				.map(Person::getCar)
				.map(Car::getInsurance)
				.map(Insurance::getName).orElse(null);
		System.out.println(name1);
		
	}
	
	@Test
	public void of() {
		// 创建一个空的 Optional 对象
		Optional<Person> person1 = Optional.empty();
		
		// 静态工厂方法：of()
		// 如果person不为null，则创建一个Optional对象，
		// 否则抛出空指针异常
		Optional<Person> person2 = Optional.of(person);
		
		// 静态工厂方法：ofNullable()
		// 与 of() 的区别是：当 person 为null时，该方法不抛空指针异常，会创建一个empty的Optional对象。
		Optional<Person> person3 = Optional.ofNullable(person);
		assert (person3.isPresent());
		assert (person3.get() != null);
		
	}
	
	/**
	 * 使用 map() 提取信息
	 */
	@Test
	public void map() {
		Insurance insurance = new Insurance();
		insurance.setName("平安");
		
		// java 8 之前的做法
		String name = null;
		if (insurance != null) {
			name = insurance.getName();
		}
		
		// Optional 做法
		name = Optional.ofNullable(insurance)
				.map(Insurance::getName)
				.orElse(null);
		
		System.out.println(name);
	}
	
	@Test
	public void orElse() throws Exception {
		Person person1 = new Person();
		Optional<Person> stringOptional = Optional.ofNullable(person1);
		
		// orElseGet() ：如果有值，则直接返回，否则执行Supplier方法
		Person person2 = stringOptional.orElseGet(() -> new Person());
		assert (person2 == person1);
		
		// orElseThrow() ：如果有值，则直接返回，否则抛出指定异常
		person2 = stringOptional.orElseThrow(() -> new Exception());
		assert person2 == person1;
		
	}
	
	/**
	 * filter() 过滤数据.
	 * 如果值存在并且满足提供的谓词，就返回包含该值的 Optional 对象;否则返回一个空的 Optional 对象.
	 */
	@Test
	public void filter() {
		// 判断保险公司是否是平安
		Insurance insurance = new Insurance();
		insurance.setName("pingan");
		
		// java 8 之前的做法
		if (insurance != null && "pingan".equals(insurance.getName())) {
			System.out.println("ok");
		}
		
		// Optional 改造
		Optional.ofNullable(insurance)
				.filter(n -> "pingan".equals(n.getName()))
				.ifPresent(a -> System.out.println(a.getName()));
	}
	
	
}
