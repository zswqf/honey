package com.honey.core;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.honey.annotations.Honey;
import com.honey.annotations.Tm;
import com.honey.utils.ClassInvoke;



final class ActionMapping {
	private final ClassMapping classMapping;
	private final Map<String, Action> actionMapping = new HashMap<String, Action>();

	public ActionMapping(ClassMapping classMapping) {
		super();
		this.classMapping = classMapping;
	}

	/**
	 * 添加映射
	 * 
	 * @param methodName
	 * @param action
	 * @return
	 */
	private ActionMapping addActionMapping(String methodName, Action action) {
		actionMapping.put(methodName, action);
		return this;
	}

	protected Action getAction(String methodName) {
		return actionMapping.get(methodName);
	}

	protected void initActionMapping() {
		Map<String, Object> cm = classMapping.getClassMapping();
		Set<Entry<String, Object>> set = cm.entrySet();
		for (Entry<String, Object> entry : set) {
			if (entry.getValue().getClass().getAnnotation(Honey.class) != null) {
				buildActionMapping(entry.getValue());
			}
		}
	}

	/**
	 * 加载方法
	 * 
	 * @param controller
	 */
	private void buildActionMapping(Object serivce) {
		Method[] method = serivce.getClass().getMethods();
		for (Method m : method) {
			String methodName = m.getName();
			if (m.getModifiers() == Modifier.PUBLIC
					&& !methodName.equals("equals")
					&& !methodName.equals("toString")) {
				String[] paramNames = ClassInvoke.getMethodParamNames(m);
				Type[] paramTypes =  m.getGenericParameterTypes();
				boolean tmFalg = false;
				if (m.getAnnotation(Tm.class) != null) {
					tmFalg = true;
				}
				Action action = new Action(serivce, m, methodName, paramNames,
						paramTypes,tmFalg);
				addActionMapping(methodName, action);
			}
		}
	}
}
