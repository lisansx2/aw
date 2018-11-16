package cn.com.cslc.aw.tools.ws.simulator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="ump-simulator.template")
public class RequestTemplate {
	
	private String userName;
	
	private String userFullName;
	
	private String mobilePhoneNumber;
	
	private String UmpUserId;
	
	private String credentialTypeCode;
	
	private String credentialNo;
	
	private String provinceNo;
	
	private String cityNo;
	
	private String agentNo;
	
	private String isEnable;
	
	private String isDeleted;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getUmpUserId() {
		return UmpUserId;
	}

	public void setUmpUserId(String umpUserId) {
		UmpUserId = umpUserId;
	}

	public String getCredentialTypeCode() {
		return credentialTypeCode;
	}

	public void setCredentialTypeCode(String credentialTypeCode) {
		this.credentialTypeCode = credentialTypeCode;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
