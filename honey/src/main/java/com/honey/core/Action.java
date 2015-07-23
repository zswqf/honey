package com.honey.core;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

public class Action {
	private final Object controllerClass;
	private final Method method;
	private final String methodName;
	private final Map<String, Type> params = new LinkedHashMap<String, Type>();
	private final boolean tmFalg;

	public Action(Object controllerClass, Method method, String methodName,
			String[] paramNames, Type[] paramTypes, boolean tmFalg) {
		this.controllerClass = controllerClass;
		this.method = method;
		this.methodName = methodName;
		this.tmFalg = tmFalg;
		setParams(paramNames, paramTypes);
	}

	public void setParams(String[] paramNames, Type[] paramTypes) {
		int len = paramNames.length;
		for (int i = 0; i < len; i++) {
			params.put(paramNames[i], paramTypes[i]);
		}
	}

	public Set<String> getParamNames() {
		return this.params.keySet();
	}

	/**
	 * @return the serviceClass
	 */
	public Object getControllerClass() {
		return controllerClass;
	}

	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * @return the tmFalg
	 */
	public boolean isTmFalg() {
		return tmFalg;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * 返回参数值
	 * 
	 * @param request
	 * @return
	 */
	public Object[] getParamValues(HttpServletRequest request) {
		int len = params.size();
		Object[] values = new Object[len];
		int i = 0;
		try {
			for (Entry<String, Type> param : params.entrySet()) {
				values[i] = TypeConverter.convert(param.getValue(),
						request.getParameter(param.getKey()));
				i++;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return values;
	}
}
