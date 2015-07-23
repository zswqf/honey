package com.honey.core;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSONObject;

/**
 * 类型转换
 * 
 * @author Administrator
 * 
 */
final class TypeConverter {
	private static final int timeStampLen = "2011-01-18 16:18:18".length();
	private static final String timeStampPattern = "yyyy-MM-dd HH:mm:ss";
	private static final String datePattern = "yyyy-MM-dd";

	public static final Object convert(Type type, String s)
			throws ParseException {
		if (type == String.class) {
			return ("".equals(s) ? null : s);
		}
		Object result = null;
		if (type == Integer.class || type == int.class) {
			result = s == null ? null : Integer.parseInt(s);
		} else if (type == Long.class || type == long.class) {
			result = s == null ? null : Long.parseLong(s);
		} else if (type == Double.class || type == double.class) {
			result = s == null ? null : Double.parseDouble(s);
		} else if (type == Float.class || type == float.class) {
			result = s == null ? null : Float.parseFloat(s);
		} else if (type == Boolean.class || type == boolean.class) {
			result = Boolean.parseBoolean(s) || "1".equals(s);
		} else if (type == java.math.BigDecimal.class) {
			result = new java.math.BigDecimal(s);
		} else if (type == java.math.BigInteger.class) {
			result = new java.math.BigInteger(s);
		} else if (type == byte[].class) {
			result = s.getBytes();
		} else if (type == java.util.Date.class) {
			if (s.length() >= timeStampLen) {
				result = new SimpleDateFormat(timeStampPattern).parse(s);
			} else {
				result = new SimpleDateFormat(datePattern).parse(s);
			}
		} else if (type == java.sql.Date.class) {
			if (s.length() >= timeStampLen) {
				result = new java.sql.Date(new SimpleDateFormat(
						timeStampPattern).parse(s).getTime());
			} else {
				result = new java.sql.Date(new SimpleDateFormat(datePattern)
						.parse(s).getTime());
			}
		} else if (type == java.sql.Time.class) {
			result = java.sql.Time.valueOf(s);
		} else if (type == java.sql.Timestamp.class) {
			result = java.sql.Timestamp.valueOf(s);
		} else {
			result = JSONObject.parseObject(s, type);
		}
		return result;
	}
}
