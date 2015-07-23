package com.honey.core;

import com.honey.config.Constants;
import com.honey.config.HoneyConfig;
import com.honey.config.Plugins;

class Config {
	private static final Constants constants = new Constants();
	private static final Plugins plugins = new Plugins();

	public static final Constants getConstants() {
		return constants;
	}

	public static final Plugins getPlugins() {
		return plugins;
	}


	public static void configHoneyInit(HoneyConfig honeyConfig) {
		honeyConfig.configConstants(constants);
		honeyConfig.configPlgin(plugins);
	}
}
