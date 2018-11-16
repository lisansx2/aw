package cn.com.cslc.aw.reports.shophistorybalance.dto;

import java.math.BigDecimal;

public class QueryShopHistoryBalanceResult {
	
	private String shopCode;
	
	private String orgName;
	
	private String reportDate;
	
	private BigDecimal balanceAmountBeforeSettled;
	
	private BigDecimal balanceAmountAfterSettled;
	
	private BigDecimal paidAmount;
	
	private BigDecimal agentAllocatedAmount;
	
	private BigDecimal netSalesAmount;
	
	private BigDecimal cashPrizeAmount;
	
	private BigDecimal cashPrizeTransferBalanceAmount;
	
	private BigDecimal wxpayAmount;
	
	private BigDecimal wxpayFee;
	
	private BigDecimal alipayAmount;
	
	private BigDecimal alipayFee;
	
	private BigDecimal refundAmount;
	
	private BigDecimal paidAutoCollectAmount;

	
	
	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate.substring(0,10);
	}

	public BigDecimal getWxpayAmount() {
		return wxpayAmount;
	}

	public void setWxpayAmount(BigDecimal wxpayAmount) {
		this.wxpayAmount = wxpayAmount;
	}

	public BigDecimal getWxpayFee() {
		return wxpayFee;
	}

	public void setWxpayFee(BigDecimal wxpayFee) {
		this.wxpayFee = wxpayFee;
	}

	public BigDecimal getAlipayAmount() {
		return alipayAmount;
	}

	public void setAlipayAmount(BigDecimal alipayAmount) {
		this.alipayAmount = alipayAmount;
	}

	public BigDecimal getAlipayFee() {
		return alipayFee;
	}

	public void setAlipayFee(BigDecimal alipayFee) {
		this.alipayFee = alipayFee;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getNetSalesAmount() {
		return netSalesAmount;
	}

	public void setNetSalesAmount(BigDecimal netSalesAmount) {
		this.netSalesAmount = netSalesAmount;
	}

	public BigDecimal getCashPrizeAmount() {
		return cashPrizeAmount;
	}

	public void setCashPrizeAmount(BigDecimal cashPrizeAmount) {
		this.cashPrizeAmount = cashPrizeAmount;
	}

	public BigDecimal getCashPrizeTransferBalanceAmount() {
		return cashPrizeTransferBalanceAmount;
	}

	public void setCashPrizeTransferBalanceAmount(BigDecimal cashPrizeTransferBalanceAmount) {
		this.cashPrizeTransferBalanceAmount = cashPrizeTransferBalanceAmount;
	}

	public BigDecimal getBalanceAmountBeforeSettled() {
		return balanceAmountBeforeSettled;
	}

	public void setBalanceAmountBeforeSettled(BigDecimal balanceAmountBeforeSettled) {
		this.balanceAmountBeforeSettled = balanceAmountBeforeSettled;
	}

	public BigDecimal getBalanceAmountAfterSettled() {
		return balanceAmountAfterSettled;
	}

	public void setBalanceAmountAfterSettled(BigDecimal balanceAmountAfterSettled) {
		this.balanceAmountAfterSettled = balanceAmountAfterSettled;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getAgentAllocatedAmount() {
		return agentAllocatedAmount;
	}

	public void setAgentAllocatedAmount(BigDecimal agentAllocatedAmount) {
		this.agentAllocatedAmount = agentAllocatedAmount;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public BigDecimal getPaidAutoCollectAmount() {
		return paidAutoCollectAmount;
	}

	public void setPaidAutoCollectAmount(BigDecimal paidAutoCollectAmount) {
		this.paidAutoCollectAmount = paidAutoCollectAmount;
	}
	
}
