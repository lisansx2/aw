package cn.com.cslc.aw.balance.shopbalance.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class QueryShopBalanceCondition {

	private String shopCode;
	
	private String accountStateCode;
	
	private List<String> agentCodeList = Lists.newArrayList();

	private String userOrg;
	
	private String orgCode;
	
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getAccountStateCode() {
		return accountStateCode;
	}

	public void setAccountStateCode(String accountStateCode) {
		this.accountStateCode = accountStateCode;
	}

	public List<String> getAgentCodeList() {
		return agentCodeList;
	}

	public void setAgentCodeList(List<String> agentCodeList) {
		this.agentCodeList = agentCodeList;
	}
	
	public String getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	public String getOrgCode() {
		if(this.orgCode == null){
			if(this.userOrg != null){
				this.orgCode = this.userOrg.split("  ")[0];
			}
		}
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
