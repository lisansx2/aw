package cn.com.cslc.aw.balance.agentbalance.dto;

import java.math.BigDecimal;

public class QueryAgentBalanceResult {
	
	private String agentName;
	
	private String agentCode;
	
	private String agentAccountCode;
	
	private BigDecimal computerAvalibleBanlance;
	
	private BigDecimal computerAccountBalance;
	
	private String accountState;

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public BigDecimal getComputerAvalibleBanlance() {
		return computerAvalibleBanlance;
	}

	public void setComputerAvalibleBanlance(BigDecimal computerAvalibleBanlance) {
		this.computerAvalibleBanlance = computerAvalibleBanlance;
	}

	public BigDecimal getComputerAccountBalance() {
		return computerAccountBalance;
	}

	public void setComputerAccountBalance(BigDecimal computerAccountBalance) {
		this.computerAccountBalance = computerAccountBalance;
	}

	public String getAccountState() {
		return accountState;
	}

	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	public String getAgentAccountCode() {
		return agentAccountCode;
	}

	public void setAgentAccountCode(String agentAccountCode) {
		this.agentAccountCode = agentAccountCode;
	}
	
}
