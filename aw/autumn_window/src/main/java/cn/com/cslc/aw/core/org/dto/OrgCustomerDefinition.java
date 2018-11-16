package cn.com.cslc.aw.core.org.dto;

import lombok.Data;

@Data
public class OrgCustomerDefinition{
	
	//大客户id
	private Long customerId;
	//大客户名称
	private String customerName;
	//机构Id
	private Long orgId;
	
	//是否已授权该机构
	private Boolean isAuthority;

}