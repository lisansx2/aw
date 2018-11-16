package cn.com.cslc.aw.reports.shopcommission.dto;

import com.google.common.collect.Sets;

import java.util.Set;

public class QueryShopCommissionCondition {
	
		private String orgCode;
		
//		private String startDate;
	private String beginSalePeriodNO;
//		private String endDate;
    private String endSalePeriodNO;
		private Set<String> agentCodeSet = Sets.newHashSet();
		
		private String provinceId;
		
//		private String accountId;
	private String techSystemId;
	private Set<String> techSystemIdSet;

	private Set<String> provinceIdSet = Sets.newHashSet();
		
		private Set<String> accountIdSet = Sets.newHashSet();
	private String shopNo;


	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Set<String> getAgentCodeSet() {
			return agentCodeSet;
		}

		public void setAgentCodeSet(Set<String> agentCodeSet) {
			this.agentCodeSet = agentCodeSet;
		}

		public Set<String> getProvinceIdSet() {
			return provinceIdSet;
		}

		public void setProvinceIdSet(Set<String> provinceIdSet) {
			this.provinceIdSet = provinceIdSet;
		}

		public Set<String> getAccountIdSet() {
			return accountIdSet;
		}

		public void setAccountIdSet(Set<String> accountIdSet) {
			this.accountIdSet = accountIdSet;
		}

		public String getProvinceId() {
			return provinceId;
		}

		public void setProvinceId(String provinceId) {
			this.provinceId = provinceId;
		}

	public String getBeginSalePeriodNO() {
		return beginSalePeriodNO;
	}

	public void setBeginSalePeriodNO(String beginSalePeriodNO) {
		this.beginSalePeriodNO = beginSalePeriodNO;
	}

	public String getEndSalePeriodNO() {
		return endSalePeriodNO;
	}

	public void setEndSalePeriodNO(String endSalePeriodNO) {
		this.endSalePeriodNO = endSalePeriodNO;
	}

	public String getTechSystemId() {
		return techSystemId;
	}

	public void setTechSystemId(String techSystemId) {
		this.techSystemId = techSystemId;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public Set<String> getTechSystemIdSet() {
		return techSystemIdSet;
	}

	public void setTechSystemIdSet(Set<String> techSystemIdSet) {
		this.techSystemIdSet = techSystemIdSet;
	}
}
