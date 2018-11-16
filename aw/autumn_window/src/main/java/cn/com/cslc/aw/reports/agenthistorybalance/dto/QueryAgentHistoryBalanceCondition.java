package cn.com.cslc.aw.reports.agenthistorybalance.dto;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class QueryAgentHistoryBalanceCondition {
	
		private String agentCode;
		
		private String startDate;
		
		private String endDate;
		
		private Set<String> agentCodeSet = Sets.newHashSet();
		
		private String provinceId;
		
		private String accountId;
		
		private Set<String> provinceIdSet = Sets.newHashSet();
		
		private Set<String> accountIdSet = Sets.newHashSet();
		
		public String getAgentCode() {
			return agentCode;
		}

		public void setAgentCode(String agentCode) {
			this.agentCode = agentCode;
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

		public String getAccountId() {
			return accountId;
		}

		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

}
