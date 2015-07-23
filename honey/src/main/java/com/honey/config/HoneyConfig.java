package com.honey.config;

import java.util.Properties;

import com.honey.core.Const;
import com.honey.utils.PropertiesUtils;

public abstract class HoneyConfig {
	public abstract void configConstants(Constants constants);

	public abstract void configPlgin(Plugins plugins);

	public Properties loadPropertyFile(String file) {
		return PropertiesUtils.load(Const.HONEY_CONFIG_FILE);
	}
}
