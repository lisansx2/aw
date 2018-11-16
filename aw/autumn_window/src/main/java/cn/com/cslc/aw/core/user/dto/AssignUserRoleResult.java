package cn.com.cslc.aw.core.user.dto;
import java.util.List;

import cn.com.cslc.aw.core.common.domain.SysUser;

public class AssignUserRoleResult{
	
	private SysUser sysUser;

	private List<UserRoleDefinition> userRoleDefList;
		
	public List<UserRoleDefinition> getUserRoleDefList() {
		return userRoleDefList;
	}


	public void setUserRoleDefList(List<UserRoleDefinition> userRoleDefList) {
		this.userRoleDefList = userRoleDefList;
	}

	public SysUser getSysUser() {
		return sysUser;
	}


	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}
