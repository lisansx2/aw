package cn.com.cslc.aw.reports.shoppayment.dto;

import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class QueryShopPaymentCondition {


	private String orgCode;
	private Set<String> agentCodeSet = Sets.newHashSet();

	private String dealTypeCode;
	private String bankNo;

	private String bDate;
	private String eDate;

	private String agentList;
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

	public String getDealTypeCode() {
		return dealTypeCode;
	}

	public void setDealTypeCode(String dealTypeCode) {
		this.dealTypeCode = dealTypeCode;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String getAgentList() {
		return agentList;
	}

	public void setAgentList(String agentList) {
		this.agentList = agentList;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
}
