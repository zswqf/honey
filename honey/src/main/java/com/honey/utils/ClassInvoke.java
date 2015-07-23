package com.honey.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * 
 * @author 张树威
 * @since 2014年7月17日下午8:25:00
 */
public abstract class ClassInvoke {
	/**
	 * 扫描给定包的所有类
	 * 
	 * @param packageName
	 *            包名
	 * @param recursive
	 *            是否递归子包
	 * @return
	 */
	public static Collection<String> getClassNames(String packageName,
			boolean recursive) {
		String packagePath = packageName.replace('.', File.separatorChar);
		URL url = ClassInvoke.class.getClassLoader().getResource(packagePath);

		String path = null;
		try {
			path = URLDecoder.decode(url.getPath(), "utf-8");// 处理空格等特殊字符
		} catch (UnsupportedEncodingException e) {
		}
		Collection<File> files = FileUtils.listFiles(new File(path),"class");
		Collection<String> classNames = new HashSet<String>();
		for (File file : files) {
			String name = StringUtils.substringAfterLast(file.getPath(),
					packagePath);
			classNames.add(packageName
					+ StringUtils.substringBeforeLast(
							name.replace(File.separatorChar, '.'), ".class"));
		}

		return classNames;
	}

	/**
	 * 
	 * <p>
	 * 比较参数类型是否一致
	 * </p>
	 * 
	 * @param types
	 *            asm的类型({@link Type})
	 * @param clazzes
	 *            java 类型({@link Class})
	 * @return
	 */
	private static boolean sameType(Type[] types, Class<?>[] clazzes) {
		// 个数不同
		if (types.length != clazzes.length) {
			return false;
		}
		for (int i = 0; i < types.length; i++) {
			if (!Type.getType(clazzes[i]).equals(types[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 获取方法的参数名
	 * </p>
	 * 
	 * @param m
	 * @return
	 */
	public static String[] getMethodParamNames(final Method m) {
		final String[] paramNames = new String[m.getParameterTypes().length];
		ClassReader cr = null;
		InputStream s = ClassInvoke.class.getClassLoader().getResourceAsStream(
				getClassPath(m.getDeclaringClass().getName()));
		try {
			cr = new ClassReader(s);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		cr.accept(new ClassVisitor(Opcodes.ASM4) {
			@Override
			public MethodVisitor visitMethod(final int access,
					final String name, final String desc,
					final String signature, final String[] exceptions) {
				final Type[] args = Type.getArgumentTypes(desc);
				// 方法名相同并且参数个数相同
				if (!name.equals(m.getName())
						|| !sameType(args, m.getParameterTypes())) {
					return super.visitMethod(access, name, desc, signature,
							exceptions);
				}
				MethodVisitor v = super.visitMethod(access, name, desc,
						signature, exceptions);
				return new MethodVisitor(Opcodes.ASM4, v) {
					@Override
					public void visitLocalVariable(String name, String desc,
							String signature, Label start, Label end, int index) {
						int i = index - 1;
						// 如果是静态方法，则第一就是参数
						// 如果不是静态方法，则第一个是"this"，然后才是方法的参数
						if (Modifier.isStatic(m.getModifiers())) {
							i = index;
						}
						if (i >= 0 && i < paramNames.length) {
							paramNames[i] = name;
						}
						super.visitLocalVariable(name, desc, signature, start,
								end, index);
					}

				};
			}
		}, 0);
		return paramNames;
	}

	private static String getClassPath(String ClassFullPathName) {
		StringBuilder classPath = new StringBuilder();
		String[] paths = ClassFullPathName.split("\\.");
		for (int i = 0; i < paths.length; i++) {
			if (i > 0) {
				classPath.append(File.separator);
			}
			classPath.append(paths[i]);
		}
		return classPath.append(".class").toString();
	}

	/**
	 * 返回值<路径:类名>
	 * 
	 * @return
	 */
	public static Map<String, String> autoScanPackage(String packageName,
			String suffix, boolean recursive) {
		Collection<String> allClassNames = ClassInvoke.getClassNames(
				packageName, recursive);
		Map<String, String> classNames = new HashMap<String, String>();
		for (String name : allClassNames) {
			if (name.endsWith(suffix)) {
				String path = StringUtils.substringAfter(name, packageName);
				path = path.substring(0, path.length() - suffix.length());
				path = path.replaceAll("\\.", "/");
				classNames.put(path, name);
			}
		}
		return classNames;
	}

}
