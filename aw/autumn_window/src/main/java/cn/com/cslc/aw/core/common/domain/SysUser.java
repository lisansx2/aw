package cn.com.cslc.aw.core.common.domain;
// Generated 2017-3-30 14:23:34 by Hibernate Tools 5.1.2.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * SysUser generated by hbm2java
 */
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {
	private SysCity sysCity;
	private SysCredentialType sysCredentialType;
	private SysProvince sysProvince;
	private SysUserState sysUserState;
	private String userName;
	private String email;
	private String mobilePhoneNumber;
	private String password;
	private Date createDate;
	private Date lastOperDate;
	private String userFullName;
	private Long umpUserId;
	private String description;
	private String credentialNo;
	private Set<SysAuthority> sysAuthorities = new HashSet<SysAuthority>(0);
	private Set<SysUserOrg> sysUserOrgs = new HashSet<SysUserOrg>(0);
	
	private String userOrgInfo ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	public SysCity getSysCity() {
		return this.sysCity;
	}

	public void setSysCity(SysCity sysCity) {
		this.sysCity = sysCity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "credential_type_id")
	public SysCredentialType getSysCredentialType() {
		return this.sysCredentialType;
	}

	public void setSysCredentialType(SysCredentialType sysCredentialType) {
		this.sysCredentialType = sysCredentialType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	public SysProvince getSysProvince() {
		return this.sysProvince;
	}

	public void setSysProvince(SysProvince sysProvince) {
		this.sysProvince = sysProvince;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_state_id")
	public SysUserState getSysUserState() {
		return this.sysUserState;
	}

	public void setSysUserState(SysUserState sysUserState) {
		this.sysUserState = sysUserState;
	}

	@Column(name = "user_name", length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile_phone_number", length = 20)
	public String getMobilePhoneNumber() {
		return this.mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_oper_date", length = 19)
	public Date getLastOperDate() {
		return this.lastOperDate;
	}

	public void setLastOperDate(Date lastOperDate) {
		this.lastOperDate = lastOperDate;
	}

	
	@Column(name = "user_full_name", nullable = false, length = 100)
	public String getUserFullName() {
		return this.userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	@Column(name = "ump_user_id")
	public Long getUmpUserId() {
		return this.umpUserId;
	}

	public void setUmpUserId(Long umpUserId) {
		this.umpUserId = umpUserId;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "credential_no", length = 50)
	public String getCredentialNo() {
		return this.credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<SysAuthority> getSysAuthorities() {
		return this.sysAuthorities;
	}

	public void setSysAuthorities(Set<SysAuthority> sysAuthorities) {
		this.sysAuthorities = sysAuthorities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<SysUserOrg> getSysUserOrgs() {
		return this.sysUserOrgs;
	}

	public void setSysUserOrgs(Set<SysUserOrg> sysUserOrgs) {
		this.sysUserOrgs = sysUserOrgs;
	}

	@Transient
	public String getUserOrgInfo() {
		String userOrgInfo = null;
		if(!this.getSysUserOrgs().isEmpty()){
			SysOrg org = this.getSysUserOrgs().iterator().next().getSysOrg();
			userOrgInfo = org.getCode()+"  " + org.getName();
		}
		return userOrgInfo;
	}

	public void setUserOrgInfo(String userOrgInfo) {
		this.userOrgInfo = userOrgInfo;
	}

}
