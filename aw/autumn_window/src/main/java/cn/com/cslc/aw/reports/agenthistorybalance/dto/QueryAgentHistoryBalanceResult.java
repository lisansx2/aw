package cn.com.cslc.aw.reports.agenthistorybalance.dto;

import java.math.BigDecimal;

public class QueryAgentHistoryBalanceResult {
	
	private String agentCode;
	
	private String agentName;
	
	private String agentAccountCode;
	
	private BigDecimal balanceAmountBeforeSettled;
	
	private BigDecimal balanceAmountAfterSettled;
	
	private BigDecimal paidAmount;
	
	private BigDecimal agentAllocatedAmount;

	private BigDecimal ctbAmount;

	private BigDecimal paidAutocollectAmount;

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentAccountCode() {
		return agentAccountCode;
	}

	public void setAgentAccountCode(String agentAccountCode) {
		this.agentAccountCode = agentAccountCode;
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

	public BigDecimal getCtbAmount() {
		return ctbAmount;
	}

	public void setCtbAmount(BigDecimal ctbAmount) {
		this.ctbAmount = ctbAmount;
	}

	public BigDecimal getPaidAutocollectAmount() {
		return paidAutocollectAmount;
	}

	public void setPaidAutocollectAmount(BigDecimal paidAutocollectAmount) {
		this.paidAutocollectAmount = paidAutocollectAmount;
	}
}
