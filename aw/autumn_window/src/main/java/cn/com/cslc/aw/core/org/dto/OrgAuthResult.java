package cn.com.cslc.aw.core.org.dto;
import java.util.List;

import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysOrgManage;
import lombok.Data;

@Data
public class OrgAuthResult{
	
	private SysOrgManage sysOrgManage;
	
	private SysOrg sysOrg;

	private List<OrgAgentDefinition> orgAgentDefList;
	
	private List<OrgCustomerDefinition> orgCustomerDefList;
	
	private Integer id;
	
	private Integer hasChildren;
	
	private String code;
	
	private String name;
	
	private String parentAgentName;
		
}
