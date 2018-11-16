package cn.com.cslc.aw.reports.shopinstantpromotion.dto;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Sets;

public class QueryShopInstantPromotionCondition {

	private String startDate;
	
	private String endDate;
	
	private String shopNo;

	private String orgCode;
	
	private String orgName;
	
	private String userOrg;
	
	private String promotionNo;
	
	private Set<String> customerCodeSet = Sets.newHashSet();
	

	private Set<String> promotionNoSet = Sets.newHashSet();
	

	
	public Set<String> getPromotionNoSet() {
		return promotionNoSet;
	}

	public void setPromotionNoSet(Set<String> promotionNoSet) {
		this.promotionNoSet = promotionNoSet;
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

	public String getPromotionNo() {
		return promotionNo;
	}

	public void setPromotionNo(String promotionNo) {
		this.promotionNo = promotionNo;
	}

	public String getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	public Set<String> getCustomerCodeSet() {
		return customerCodeSet;
	}

	public void setCustomerCodeSet(Set<String> customerCodeSet) {
		this.customerCodeSet = customerCodeSet;
	}
	
}
