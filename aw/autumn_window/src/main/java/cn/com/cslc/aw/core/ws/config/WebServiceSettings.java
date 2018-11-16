package cn.com.cslc.aw.core.ws.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="ws")
public class WebServiceSettings {
	
	private String umpSourceName;
	
	private Map<String, String> credentialTypeCodeMap = new HashMap<>();
	
	private String awDefaultCredentialTypeCode;
	
	public String getUmpSourceName() {
		return umpSourceName;
	}
	public void setUmpSourceName(String umpSourceName) {
		this.umpSourceName = umpSourceName;
	}
	public Map<String, String> getCredentialTypeCodeMap() {
		return credentialTypeCodeMap;
	}
	public void setCredentialTypeCodeMap(Map<String, String> credentialTypeCodeMap) {
		this.credentialTypeCodeMap = credentialTypeCodeMap;
	}
	public String getAwDefaultCredentialTypeCode() {
		return awDefaultCredentialTypeCode;
	}
	public void setAwDefaultCredentialTypeCode(String awDefaultCredentialTypeCode) {
		this.awDefaultCredentialTypeCode = awDefaultCredentialTypeCode;
	}
	
}