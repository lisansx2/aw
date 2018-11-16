package cn.com.cslc.aw.balance.shopbalance.dto;

import java.math.BigDecimal;

public class QueryShopBalanceResult {
	
	private String orgName;
	
	private String shopCode;
	
	private String accountCode;
	
	private BigDecimal computerAvalibleBanlance;
	
	private BigDecimal computerAccountBalance;
	
	private String accountStateName;


	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
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

	public String getAccountStateName() {
		return accountStateName;
	}

	public void setAccountStateName(String accountStateName) {
		this.accountStateName = accountStateName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
