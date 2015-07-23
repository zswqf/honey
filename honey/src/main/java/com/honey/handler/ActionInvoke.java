package com.honey.handler;

import javax.servlet.http.HttpServletRequest;

import com.honey.core.Action;

public interface ActionInvoke {
	Object invoke(Action action,HttpServletRequest request);
}
