package com.honey.config;

import java.util.ArrayList;
import java.util.List;

import com.honey.plugin.IPlugin;
final public class Plugins {
	private final List<IPlugin> plugins = new ArrayList<IPlugin>();
	public Plugins add(IPlugin plugin) {
		if (plugin != null)
			this.plugins.add(plugin);
		return this;
	}
	
	public List<IPlugin> getPluginList() {
		return plugins;
	}
}
