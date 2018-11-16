package cn.com.cslc.aw.agent.dto;

import java.math.BigDecimal;

public class B_AgentDto {
	
	private String agentNo;
	
	private String agentName;
	
	private BigDecimal statusCode; 
	
	private BigDecimal delFlag;

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public BigDecimal getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(BigDecimal statusCode) {
		this.statusCode = statusCode;
	}

	public BigDecimal getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(BigDecimal delFlag) {
		this.delFlag = delFlag;
	}
	
}
