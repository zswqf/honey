package com.honey.utils;

/**
 * url 拦截处理
 * 
 * @author 张树威
 * @since 2014年8月1日上午11:28:04
 */
public abstract class URLUtils {
	/**
	 * 獲取方法名
	 * 
	 * @param url
	 * @return
	 */
	public static String getMethod(String url) {
		
		return  url.substring(1);
	}
}
