/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * java8 中，Base64 已成为 java 标准类库
 *
 * @author wung 2018/7/30.
 */
public class Base64Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		// 基本类型(映射到一组字符：A-Za-z0-9+/)
		byte[] encodeBytes = Base64.getEncoder().encode("v1/login?username=jack".getBytes("utf-8"));
		System.out.println("Base64编码（标准）:" + new String(encodeBytes, "utf-8"));
		encodeBytes = Base64.getDecoder().decode(encodeBytes);
		System.out.println("Base64解码（标准）:" + new String(encodeBytes, "utf-8"));
		
		// url 类型（映射到一组字符：A-Za-z0-9+_）
		encodeBytes = Base64.getUrlEncoder().encode("v1/login?username=jack".getBytes("utf-8"));
		System.out.println("Base64编码（url）:" + new String(encodeBytes, "utf-8"));
		encodeBytes = Base64.getUrlDecoder().decode(encodeBytes);
		System.out.println("Base64解码（url）:" + new String(encodeBytes, "utf-8"));
		
		
		// MIME 类型(映射到一组字符：MIME友好格式。输出每行不超过76字符，并且使用'\r\n'作为分割。)
		// 构造一个长字符串
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(UUID.randomUUID().toString());
		}
		encodeBytes = Base64.getMimeEncoder().encode(sb.toString().getBytes("utf-8"));
		System.out.println("Base64编码（MIME）:" + new String(encodeBytes, "utf-8"));
		encodeBytes = Base64.getMimeDecoder().decode(encodeBytes);
		System.out.println("Base64解码（MIME）:" + new String(encodeBytes, "utf-8"));
		
		
	}
}
