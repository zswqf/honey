package com.honey.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * 
 * @author 张树威
 * @since 2014年8月1日上午11:27:58
 */
public class PropertiesUtils {
	/**
	 * 加载属性文件
	 * 
	 * @param propsPath
	 * @return
	 */
	public static Properties load(String propsPath) {
		Properties props = new Properties();
		InputStream is = null;
		try {
			String suffix = ".properties";
			if (propsPath.lastIndexOf(suffix) == -1) {
				propsPath += suffix;
			}
			is = PropertiesUtils.class.getClassLoader().getResourceAsStream(
					propsPath);
			if (is != null) {
				props.load(is);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}

	/**
	 * 加载属性文件，并转为 Map
	 * 
	 * @param propsPath
	 * @return
	 */
	public static Map<String, String> loadToMap(String propsPath) {
		Map<String, String> map = new HashMap<String, String>();
		Properties props = load(propsPath);
		for (String key : props.stringPropertyNames()) {
			map.put(key, props.getProperty(key));
		}
		return map;
	}

	/**
	 * 获取字符型属性
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getString(Properties props, String key) {
		return props.getProperty(key) != null ? props.getProperty(key) : "";
	}

	/**
	 * 获取数值型属性
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static int getNumber(Properties props, String key) {
		return props.getProperty(key) == null ? 0 : Integer.parseInt(props
				.getProperty(key));
	}

}
