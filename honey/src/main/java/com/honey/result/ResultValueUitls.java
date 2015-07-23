package com.honey.result;

import com.alibaba.fastjson.JSONObject;

public class ResultValueUitls {
	private ResultValueUitls() {

	}

	private static String getResultValue(Object result, String code, String msg) {
		JSONObject resultValue = new JSONObject();
		resultValue.put("code", code);
		resultValue.put("msg", msg);
		resultValue.put("result", result);
		return resultValue.toString();
	}

	public static String getReturnValue(ResponseValue rv) {
		return getResultValue(rv.getResult(), rv.getCode(), rv.getMsg());
	}
}
