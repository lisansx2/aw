package cn.com.cslc.aw.core.org.dto;

import lombok.Data;

@Data
public class OrgAgentDefinition{
	
	//代理id
	private Long agentId;
	//代理名称
	private String agentName;
	//机构Id
	private Long orgId;
	
	//是否已授权该机构
	private Boolean isAuthority;

}