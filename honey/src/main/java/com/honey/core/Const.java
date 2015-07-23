package com.honey.core;

/**
 * 
 * @author 张树威
 * @since 2014年7月17日下午8:03:08
 */
public interface Const {
	/**
	 * 属性配置文件名称
	 */
	String HONEY_CONFIG_FILE = "honey.properties";
	/**
	 * 默认编码
	 */
	String DEFAULT_ENCODING = "utf-8";
	/**
	 * 默认缓存插件
	 */
	String DEFAULT_CACHE_CLASSNAME = "com.honey.cache.EhCacheProvider";
	/**
	 * 默认扫描的包
	 */
	String DEFAULT_SCAN_PAK ="com";
	String DEFAULT_ACTIONINVOKE = "com.honey.handler.DefaultActionInvoke";
}
