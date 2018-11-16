package cn.com.cslc.aw.core.common.security.sso;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="sso")
public class SsoSettings {
	
	private String websealTokenName;
	
	private String address;
	
	private String username;
	
	private String password;
	
	private String logoutUrl;
	
	private String loginUrl;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getWebsealTokenName() {
		return websealTokenName;
	}

	public void setWebsealTokenName(String websealTokenName) {
		this.websealTokenName = websealTokenName;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}
