package cn.com.cslc.aw.reports.periodsales.dto;

import java.math.BigDecimal;

public class QueryPeriodSalesResult {
	
	private String shopNo;
	
	private String orgName;
	
	private BigDecimal saledTicketNumSum;
	
	private BigDecimal saledTicketAmountSum;
	
    private BigDecimal cancelTicketNumSum;
	
	private BigDecimal cancelTicketAmountSum;
	
	private BigDecimal refundTicketNumSum;
	
	private BigDecimal refundTicketAmountSum;
	
	private BigDecimal paidTicketNumSum;
	
	private BigDecimal paidTicketAmountSum;
	
	

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public BigDecimal getCancelTicketNumSum() {
		return cancelTicketNumSum;
	}

	public void setCancelTicketNumSum(BigDecimal cancelTicketNumSum) {
		this.cancelTicketNumSum = cancelTicketNumSum;
	}

	public BigDecimal getCancelTicketAmountSum() {
		return cancelTicketAmountSum;
	}

	public void setCancelTicketAmountSum(BigDecimal cancelTicketAmountSum) {
		this.cancelTicketAmountSum = cancelTicketAmountSum;
	}

	public BigDecimal getRefundTicketNumSum() {
		return refundTicketNumSum;
	}

	public void setRefundTicketNumSum(BigDecimal refundTicketNumSum) {
		this.refundTicketNumSum = refundTicketNumSum;
	}

	public BigDecimal getRefundTicketAmountSum() {
		return refundTicketAmountSum;
	}

	public void setRefundTicketAmountSum(BigDecimal refundTicketAmountSum) {
		this.refundTicketAmountSum = refundTicketAmountSum;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public BigDecimal getSaledTicketNumSum() {
		return saledTicketNumSum;
	}

	public void setSaledTicketNumSum(BigDecimal saledTicketNumSum) {
		this.saledTicketNumSum = saledTicketNumSum;
	}

	public BigDecimal getSaledTicketAmountSum() {
		return saledTicketAmountSum;
	}

	public void setSaledTicketAmountSum(BigDecimal saledTicketAmountSum) {
		this.saledTicketAmountSum = saledTicketAmountSum;
	}

	public BigDecimal getPaidTicketNumSum() {
		return paidTicketNumSum;
	}

	public void setPaidTicketNumSum(BigDecimal paidTicketNumSum) {
		this.paidTicketNumSum = paidTicketNumSum;
	}

	public BigDecimal getPaidTicketAmountSum() {
		return paidTicketAmountSum;
	}

	public void setPaidTicketAmountSum(BigDecimal paidTicketAmountSum) {
		this.paidTicketAmountSum = paidTicketAmountSum;
	}

}
