package cn.com.cslc.aw.core.user.dto;

public class UserRoleDefinition{
	
	//角色id
	private Long roleId;
	//角色名称
	private String roleName;
	//用户Id
	private Long userId;
	
	//角色是否已授权该用户
	private Boolean isAuthority;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsAuthority() {
		return isAuthority;
	}

	public void setIsAuthority(Boolean isAuthority) {
		this.isAuthority = isAuthority;
	}
	
}