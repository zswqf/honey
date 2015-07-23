package com.honey.result;

import com.alibaba.fastjson.JSONObject;

public class ResponseValue {
	private String code = ResultCode.SUCCESS_CODE;
	private String msg = ResultMsg.SUCCESS_MSG;
	private JSONObject resultParams = new JSONObject();
	private Object result = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	protected Object getResult() {
		if (result == null && resultParams.size() == 0) {
			if (code.equals(ResultCode.SUCCESS_CODE)) {
				this.code = ResultCode.NO_VALUE_CODE;
				this.msg = ResultMsg.NO_VALUE_MSG;
			}
			this.result = "";
		} else if (result == null && resultParams.size() > 0) {
			result = resultParams;
		}
		return result;
	}

	public void setResultParams(String key, Object value) {
		resultParams.put(key, value);
	}

	public void setResult(Object value) {
		result = value;
	}

}
