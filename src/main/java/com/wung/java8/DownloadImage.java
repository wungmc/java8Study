/*
 * Copyright (C), 2011-2018.
 */
package com.wung.java8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载图片到本地
 *
 * @author wung 2018/8/1.
 */
public class DownloadImage {
	public static void main(String[] args) throws Exception {
		String[] fileNames = {
				"522321196506154015"
		};
		
		String[] urls = {
				"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1182790825,3992631659&fm=27&gp=0.jpg"
		};
		
		for (int i = 0; i < urls.length; i++) {
			String url = urls[i];
			String suff = url.substring(url.lastIndexOf('.'), url.length());
			download(urls[i], fileNames[i] + suff, "/Users/wungmc/Desktop/tz");
		}
		
	}
	
	public static void download(String urlString, String filename,String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		//设置请求超时为5s
		con.setConnectTimeout(5*1000);
		// 输入流
		InputStream is = con.getInputStream();
		
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if(!sf.exists()){
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath()+"/"+filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}
	
}
