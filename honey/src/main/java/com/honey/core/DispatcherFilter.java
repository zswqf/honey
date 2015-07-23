package com.honey.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.honey.config.HoneyConfig;
import com.honey.exception.ExceptionResult;
import com.honey.utils.URLUtils;

/**
 * 
 * @author 张树威
 * @since 2014年7月17日下午7:16:20
 */
public class DispatcherFilter implements Filter {
	private final static Logger logger = LoggerFactory
			.getLogger(DispatcherFilter.class);
	private static final HoneyInit honeyInit = HoneyInit.me();
	private HoneyConfig honeyConfig;
	private int contextPathLength;
	private String encoding;

	@Override
	public void init(FilterConfig config) throws ServletException {
		initHoneyConfig(config.getInitParameter("configClass"));
		honeyInit.init(honeyConfig);
		encoding = Const.DEFAULT_ENCODING;
		String contextPath = config.getServletContext().getContextPath();
		contextPathLength = (contextPath == null || "/".equals(contextPath) ? 0
				: contextPath.length());
		if (logger.isDebugEnabled()) {
			logger.debug("------------项目已启动------------");
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
//		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		String target = request.getRequestURI();
		if (contextPathLength != 0)
			target = target.substring(contextPathLength);
		String methodName = URLUtils.getMethod(target);
		if (methodName == null) {
			printWriter(response, ExceptionResult.NO_INTERFACE_METHOD);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("------------调用接口方法名称------------:" + methodName);
			}
			Action atcion = honeyInit.getAction(methodName);
			if (atcion == null) {
				printWriter(response, ExceptionResult.NO_INTERFACE_METHOD);
			} else {
				Object result = honeyInit.getActionInvoke().invoke(atcion, request);
				printWriter(response, result);
			}
		}
	}

	@Override
	public void destroy() {

	}

	/**
	 * 返回结果
	 * 
	 * @param response
	 * @param json
	 */
	private void printWriter(HttpServletResponse response, Object result) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (logger.isDebugEnabled()) {
				logger.debug("返回值：" + result);
			}
			out.print(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 初始化配置
	 * 
	 * @param configClass
	 */
	private void initHoneyConfig(String configClass) {
		if (configClass == null)
			throw new RuntimeException(
					"Please set configClass parameter of JsfFilter in web.xml");
		try {
			Object temp = Class.forName(configClass).newInstance();
			if (temp instanceof HoneyConfig)
				honeyConfig = (HoneyConfig) temp;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
