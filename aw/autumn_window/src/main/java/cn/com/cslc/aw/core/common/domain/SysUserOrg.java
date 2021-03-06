package cn.com.cslc.aw.core.common.domain;
// Generated 2017-9-14 11:07:50 by Hibernate Tools 5.1.2.Final

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysUserOrg generated by hbm2java
 */
@Entity
@Table(name = "SYS_USER_ORG")
public class SysUserOrg extends BaseEntity {

	private SysOrg sysOrg;
	private SysUser sysUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	public SysOrg getSysOrg() {
		return this.sysOrg;
	}

	public void setSysOrg(SysOrg sysOrg) {
		this.sysOrg = sysOrg;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}
