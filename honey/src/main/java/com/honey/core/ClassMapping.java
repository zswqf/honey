package com.honey.core;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.honey.utils.ClassInvoke;

public class ClassMapping {
	private final Map<String, Object> classMapping = new HashMap<String, Object>();
	private final Map<String, ActionMapping> serviceMapping = new HashMap<String, ActionMapping>();
	public Object getClass(String interfaceName) {
		return classMapping.get(interfaceName);
	}

	public Map<String, Object> getClassMapping() {
		return classMapping;
	}

	private void addClassMapping(String interfaceName, Object classObject) {
		classMapping.put(interfaceName, classObject);
	}
	private void addServiceMapping(String interfaceName, ActionMapping am) {
		classMapping.put(interfaceName, am);
	}

	/**
	 * 初始化Class
	 * 
	 * @return
	 */
	protected void initClassMapping() {
		Collection<String> classNames = new HashSet<String>();
		String[] daoPackages = Config.getConstants().getAutoScanPak()
				.split(",");
		for (String pkg : daoPackages) {
			Collection<String> classNamesTemp = ClassInvoke.getClassNames(pkg,
					true);
			classNames.addAll(classNamesTemp);
		}
		try {
			for (String fullClassName : classNames) {
				Class<?> temp = Class.forName(fullClassName);
				if (temp.getAnnotations().length > 0) {
					Object objectClass = temp.newInstance();
					addClassMapping(fullClassName,
							objectClass);
				} else if (temp.getAnnotations().length > 0 ||(!temp.isInterface()
						&& temp.getInterfaces().length > 0
						&& !fullClassName.contains("$"))) {
					Object objectClass = temp.newInstance();
					addClassMapping((temp.getInterfaces())[0].toString(),
							objectClass);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		injectClass();
	}

	private void injectClass() {
		Set<Entry<String, Object>> set = classMapping.entrySet();
		for (Entry<String, Object> entry : set) {
			Object objectCla = entry.getValue();
			try {
				Field[] fs = objectCla.getClass().getDeclaredFields();
				for (Field f : fs) {
					f.setAccessible(true);
					if (f.getModifiers() == 2) {
						f.set(objectCla, getClass(f.getType().toString()));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
