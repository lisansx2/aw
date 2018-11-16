package cn.com.cslc.aw.reports.shopinstanttrade.dto;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Sets;

public class QueryShopInstantTradeCondition {
	
	private String startDate;
	
	private String endDate;
	
	private String shopNo;

	private String orgCode;
	
	private String orgName;
	
	private String userOrg;
	
	
	private Set<String> customerCodeSet = Sets.newHashSet();;

	private Set<String> gameNoSet = Sets.newHashSet();
	
	private String gameNo;
	
	public Set<String> getGameNoSet() {
		return gameNoSet;
	}

	public void setGameNoSet(Set<String> gameNoSet) {
		this.gameNoSet = gameNoSet;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getOrgCode() {
		if(StringUtils.isNotEmpty(userOrg)){
			orgCode = userOrg.split("  ")[0];
		}
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		if(StringUtils.isNotEmpty(userOrg)){
				orgName = userOrg.split("  ")[1];
		}
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Set<String> getCustomerCodeSet() {
		return customerCodeSet;
	}

	public void setCustomerCodeSet(Set<String> customerCodeSet) {
		this.customerCodeSet = customerCodeSet;
	}

	public String getGameNo() {
		return gameNo;
	}

	public void setGameNo(String gameNo) {
		this.gameNo = gameNo;
	}

	public String getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}
	
}
