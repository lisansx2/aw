package cn.com.cslc.aw.core.user.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryUserCondition {

	private String id;
	
	private String userName;
	
	private String userFullName;
	
	private int userStateId;
	
	private String userOrg;
	
	private String orgCode;
	
	private List<String> orgCodeList = new ArrayList<String>();
	
	private Map<String,String> additionalData = new HashMap<String,String>();

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

	public int getUserStateId() {
		return userStateId;
	}

	public void setUserStateId(int userStateId) {
		this.userStateId = userStateId;
	}


	public String getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	public String getOrgCode() {
		if(orgCode == null){
			orgCode = this.getUserOrg().split("  ")[0];
		}
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getOrgCodeList() {
		return orgCodeList;
	}

	public void setOrgCodeList(List<String> orgCodeList) {
		this.orgCodeList = orgCodeList;
	}

	public Map<String, String> getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(Map<String, String> additionalData) {
		this.additionalData = additionalData;
	}
	
}
