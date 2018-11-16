package cn.com.cslc.aw.core.common.domain;
// Generated 2017-3-30 14:23:34 by Hibernate Tools 5.1.2.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysUserState generated by hbm2java
 */
@Entity
@Table(name = "sys_user_state")
public class SysUserState extends BaseEntity {

	private String code;
	private String name;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	@Column(name = "code", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysUserState")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

}