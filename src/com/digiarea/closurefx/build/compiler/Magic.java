package com.digiarea.closurefx.build.compiler;

import java.lang.reflect.Method;

public class Magic {

	public static Class<?> getClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Method getDeclaredMethod(Class<?> cls, String name,
			Class<?>... parameterTypes) {
		try {
			Method method = cls.getDeclaredMethod(name, parameterTypes);
			method.setAccessible(true);
			return method;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
}
