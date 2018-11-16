package cn.com.cslc.aw.reports.competitionprizecash.dto;

import java.math.BigDecimal;

public class QueryCompetitionPrizeCashResult {

	private String shopNo;
	
	private String orgName;
	//兑奖票数
	private BigDecimal paidTicketNumSum;
		//实兑金额
	private BigDecimal paidTicketAmountSum;
	//四舍五入
	private BigDecimal paidAmountRoundSum;
	//舍位取整
	private BigDecimal paidAmountAbandonSum;
	//进位取整
	private BigDecimal paidAmountCarrySum;
	
	//格式化后的实兑金额
	private String formatedPaidTicketAmountSum;

	//格式化后的计算金额，用户页面显示
	private String formatedComputePaidTicketAmountSum;

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
		return  paidTicketAmountSum/*.setScale(2, RoundingMode.HALF_DOWN)*/;
	}

	public void setPaidTicketAmountSum(BigDecimal paidTicketAmountSum) {
		this.paidTicketAmountSum = paidTicketAmountSum;
	}

	public String getFormatedPaidTicketAmountSum() {
		return this.formatedPaidTicketAmountSum;
	}

	public void setFormatedPaidTicketAmountSum(String formatedPaidTicketAmountSum) {
		this.formatedPaidTicketAmountSum = formatedPaidTicketAmountSum;
	}

	public String getFormatedComputePaidTicketAmountSum() {
		return formatedComputePaidTicketAmountSum;
	}

	public void setFormatedComputePaidTicketAmountSum(String formatedComputePaidTicketAmountSum) {
		this.formatedComputePaidTicketAmountSum = formatedComputePaidTicketAmountSum;
	}

	public BigDecimal getPaidAmountRoundSum() {
		return paidAmountRoundSum;
	}

	public void setPaidAmountRoundSum(BigDecimal paidAmountRoundSum) {
		this.paidAmountRoundSum = paidAmountRoundSum;
	}

	public BigDecimal getPaidAmountAbandonSum() {
		return paidAmountAbandonSum;
	}

	public void setPaidAmountAbandonSum(BigDecimal paidAmountAbandonSum) {
		this.paidAmountAbandonSum = paidAmountAbandonSum;
	}

	public BigDecimal getPaidAmountCarrySum() {
		return paidAmountCarrySum;
	}

	public void setPaidAmountCarrySum(BigDecimal paidAmountCarrySum) {
		this.paidAmountCarrySum = paidAmountCarrySum;
	}
	

}
