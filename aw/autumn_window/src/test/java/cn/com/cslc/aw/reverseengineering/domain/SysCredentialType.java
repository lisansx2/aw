package cn.com.cslc.aw.reverseengineering.domain;
// Generated 2017-5-9 13:48:55 by Hibernate Tools 5.1.2.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysCredentialType generated by hbm2java
 */
@Entity
@Table(name = "SYS_CREDENTIAL_TYPE", schema = "AW")
public class SysCredentialType implements java.io.Serializable {

	private BigDecimal id;
	private String code;
	private String name;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	public SysCredentialType() {
	}

	public SysCredentialType(BigDecimal id) {
		this.id = id;
	}

	public SysCredentialType(BigDecimal id, String code, String name, Set<SysUser> sysUsers) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.sysUsers = sysUsers;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name = "CODE", length = 40)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysCredentialType")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

}
