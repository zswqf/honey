package com.honey.handler;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.honey.core.Action;
import com.honey.mybatis.HoneyTemple;

/**
 * 默认action 处理方式
 * 
 * @author Administrator
 *
 */
public class DefaultActionInvoke implements ActionInvoke {

	@Override
	public Object invoke(Action action, HttpServletRequest request) {
		Object result = null;
		SqlSession session = HoneyTemple.setSqlSession();
		try {
			result = action
					.getMethod()
					.invoke(action.getControllerClass(),
							action.getParamValues(request)).toString();
			if (action.isTmFalg()) {
				session.commit();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			if (action.isTmFalg()) {
				session.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
