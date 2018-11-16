package cn.com.cslc.aw.reverseengineering.domain;
// Generated 2017-5-9 13:48:55 by Hibernate Tools 5.1.2.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysRoleResource generated by hbm2java
 */
@Entity
@Table(name = "SYS_ROLE_RESOURCE", schema = "AW")
public class SysRoleResource implements java.io.Serializable {

	private BigDecimal id;
	private SysResource sysResource;
	private SysRole sysRole;

	public SysRoleResource() {
	}

	public SysRoleResource(BigDecimal id) {
		this.id = id;
	}

	public SysRoleResource(BigDecimal id, SysResource sysResource, SysRole sysRole) {
		this.id = id;
		this.sysResource = sysResource;
		this.sysRole = sysRole;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCE_ID")
	public SysResource getSysResource() {
		return this.sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}
