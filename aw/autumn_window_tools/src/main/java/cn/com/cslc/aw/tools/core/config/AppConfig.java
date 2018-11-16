package cn.com.cslc.aw.tools.core.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="app")
public class AppConfig {
	
	private String appName;
	
	private List<Map<String, String>> userDef = new ArrayList<>();
	
	private List<Map<String, String>> menuDef = new ArrayList<>();

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<Map<String, String>> getUserDef() {
		return userDef;
	}

	public void setUserDef(List<Map<String, String>> userDef) {
		this.userDef = userDef;
	}

	public List<Map<String, String>> getMenuDef() {
		return menuDef;
	}

	public void setMenuDef(List<Map<String, String>> menuDef) {
		this.menuDef = menuDef;
	}
	
}