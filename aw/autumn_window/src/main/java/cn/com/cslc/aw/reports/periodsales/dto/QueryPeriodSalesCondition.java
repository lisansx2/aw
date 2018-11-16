package cn.com.cslc.aw.reports.periodsales.dto;

import java.util.Set;

import com.google.common.collect.Sets;

public class QueryPeriodSalesCondition {
	
		private String gameNo;
		
		private Set<String> gameNoSet = Sets.newHashSet();
		
		private String startDate;
		
		private String endDate;
		
		private String shopNo;
		
		private Set<String> agentCodeSet = Sets.newHashSet();
		
		private String shopCode;
	
		private Set<String> provinceIdSet = Sets.newHashSet();
		
		private String userOrg;
		
		private String orgCode;
		
		private String orgName;
		
		
		public String getGameNo() {
			return gameNo;
		}

		public void setGameNo(String gameNo) {
			this.gameNo = gameNo;
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


		public Set<String> getAgentCodeSet() {
			return agentCodeSet;
		}

		public void setAgentCodeSet(Set<String> agentCodeSet) {
			this.agentCodeSet = agentCodeSet;
		}

		public Set<String> getGameNoSet() {
			return gameNoSet;
		}

		public void setGameNoSet(Set<String> gameNoSet) {
			this.gameNoSet = gameNoSet;
		}

		public String getShopCode() {
			return shopCode;
		}

		public void setShopCode(String shopCode) {
			this.shopCode = shopCode;
		}

		public Set<String> getProvinceIdSet() {
			return provinceIdSet;
		}

		public void setProvinceIdSet(Set<String> provinceIdSet) {
			this.provinceIdSet = provinceIdSet;
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

		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}
		

}
