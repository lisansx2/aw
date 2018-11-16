package cn.com.cslc.aw.reports.shopinstanttrade.dto;

import java.math.BigDecimal;

public class QueryShopInstantTradeResult {
	
	private String shopNo;
	
	private String orgName;
	
	private BigDecimal paidTicketNumSum;
	
	private BigDecimal paidTicketAmountSum;
	
	private BigDecimal promotionPaidTicketAmountSum;
	
	private BigDecimal activeNumSum;
	
	private BigDecimal activeAmountSum;

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public BigDecimal getPromotionPaidTicketAmountSum() {
		return promotionPaidTicketAmountSum;
	}

	public void setPromotionPaidTicketAmountSum(BigDecimal promotionPaidTicketAmountSum) {
		this.promotionPaidTicketAmountSum = promotionPaidTicketAmountSum;
	}

	public BigDecimal getActiveNumSum() {
		return activeNumSum;
	}

	public void setActiveNumSum(BigDecimal activeNumSum) {
		this.activeNumSum = activeNumSum;
	}

	public BigDecimal getActiveAmountSum() {
		return activeAmountSum;
	}

	public void setActiveAmountSum(BigDecimal activeAmountSum) {
		this.activeAmountSum = activeAmountSum;
	}
	
}
