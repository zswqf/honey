package com.honey.core;

import java.util.List;

import com.honey.config.HoneyConfig;
import com.honey.handler.ActionInvoke;
import com.honey.plugin.IPlugin;

public class HoneyInit {
	private static HoneyInit honeyInit = new HoneyInit();
	private ClassMapping classMapping;
	private ActionMapping actionMapping;
	private ActionInvoke actionInvoke;

	protected static HoneyInit me() {
		return honeyInit;
	}

	protected boolean init(HoneyConfig honeyConfig) {
		Config.configHoneyInit(honeyConfig);
		initClassMapping();
		initActionMapping();
		initPlugin();
		initActionInvoke();
		return true;
	}

	protected void initActionInvoke() {
		try {
			actionInvoke = (ActionInvoke) Class.forName(
					Config.getConstants().getActionInvokeClassName())
					.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected void initClassMapping() {
		classMapping = new ClassMapping();
		classMapping.initClassMapping();
	}

	protected void initActionMapping() {
		actionMapping = new ActionMapping(classMapping);
		actionMapping.initActionMapping();
	}

	protected void initPlugin() {
		List<IPlugin> plugins = Config.getPlugins().getPluginList();
		if (plugins.size() > 0) {
			for (IPlugin plugin : plugins) {
				plugin.start();
			}
		}
	}

	protected Action getAction(String methodName) {
		return actionMapping.getAction(methodName);
	}

	protected ActionInvoke getActionInvoke() {
		return actionInvoke;
	}

	protected void stopPlugins() {
		List<IPlugin> plugins = Config.getPlugins().getPluginList();
		if (plugins.size() > 0) {
			for (IPlugin plugin : plugins) {
				plugin.stop();
			}
		}
	}

	protected void destory() {
		stopPlugins();
	}

}
