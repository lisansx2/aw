package cn.com.cslc.aw.reports.competitionprizecash.dto;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Sets;

public class QueryCompetitionPrizeCashCondition {

	
	private String startDate;
	
	private String endDate;
	
	private String shopNo;

	private String orgCode;
	
	private String orgName;
	
	private String userOrg;
	
	
	private String roundNo;
	
	private Set<String> agentCodeSet = Sets.newHashSet();
	
	private Set<String> orgCodeSet = Sets.newHashSet();
	

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


	public String getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	public String getRoundNo() {
		return roundNo;
	}

	public void setRoundNo(String roundNo) {
		this.roundNo = roundNo;
	}

	public Set<String> getAgentCodeSet() {
		return agentCodeSet;
	}

	public void setAgentCodeSet(Set<String> agentCodeSet) {
		this.agentCodeSet = agentCodeSet;
	}

	public Set<String> getOrgCodeSet() {
		return orgCodeSet;
	}

	public void setOrgCodeSet(Set<String> orgCodeSet) {
		this.orgCodeSet = orgCodeSet;
	}
	


}
