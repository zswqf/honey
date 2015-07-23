package com.honey.config;

import com.honey.core.Const;

public class Constants {
	/**
	 * 默认扫描包
	 */
	private String autoScanPak = Const.DEFAULT_SCAN_PAK;
	/**
	 * 默认处理的action 
	 */
	private String actionInvokeClassName = Const.DEFAULT_ACTIONINVOKE;

	public String getAutoScanPak() {
		return autoScanPak;
	}

	public void setAutoScanPak(String autoScanPak) {
		this.autoScanPak = autoScanPak;
	}

	public String getActionInvokeClassName() {
		return actionInvokeClassName;
	}

	public void setActionInvokeClassName(String actionInvokeClassName) {
		this.actionInvokeClassName = actionInvokeClassName;
	}

}
