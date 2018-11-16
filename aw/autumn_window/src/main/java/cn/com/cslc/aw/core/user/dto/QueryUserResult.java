package cn.com.cslc.aw.core.user.dto;

import java.util.Date;

public class QueryUserResult {

	private Long id;
	
	private String userName;
	
	private String userFullName;
	
	private String sysUserState;
	
	private String region;
	
	private String roleName;
	
	private String orgName;
	
	private String mobilePhoneNo;
	
	private Date lastOperDate;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

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

	public String getSysUserState() {
		return sysUserState;
	}

	public void setSysUserState(String sysUserState) {
		this.sysUserState = sysUserState;
	}

	public Date getLastOperDate() {
		return lastOperDate;
	}

	public void setLastOperDate(Date lastOperDate) {
		this.lastOperDate = lastOperDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}

	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
