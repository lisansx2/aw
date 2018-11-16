package cn.com.cslc.aw.reports.shopcommission.dto;

import java.math.BigDecimal;

public class QueryShopCommissionResult {
	
	private String shopNo;
	
	private String terminalNo;
	
	private String orgName;
	
	private BigDecimal commissionSaleAmount;
	
	private BigDecimal paidAmount;
	
	private BigDecimal quotaCommissionAmount;
	
	private BigDecimal commissionAmount;

	private BigDecimal cashCommissionAmount;

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public BigDecimal getCommissionSaleAmount() {
		return commissionSaleAmount;
	}

	public void setCommissionSaleAmount(BigDecimal commissionSaleAmount) {
		this.commissionSaleAmount = commissionSaleAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getQuotaCommissionAmount() {
		return quotaCommissionAmount;
	}

	public void setQuotaCommissionAmount(BigDecimal quotaCommissionAmount) {
		this.quotaCommissionAmount = quotaCommissionAmount;
	}

	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public BigDecimal getCashCommissionAmount() {
		return cashCommissionAmount;
	}

	public void setCashCommissionAmount(BigDecimal cashCommissionAmount) {
		this.cashCommissionAmount = cashCommissionAmount;
	}
}
