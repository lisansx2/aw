package cn.com.cslc.aw.core.common.domain;
// Generated 2017-3-29 13:36:52 by Hibernate Tools 5.1.2.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysProvince generated by hbm2java
 */
@Entity
@Table(name = "sys_province")
public class SysProvince extends BaseEntity {

	private String provinceNo;
	private String provinceName;
	private String provinceFullName;
	private String provinceShortName;
	private String alphabeticCode;
	private Integer countryId;
	private Integer sortId;
	private String statusCode;
	private Date statusChangeTime;
	private Integer delFlag;
	private Date delTime;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
	private Set<SysCity> sysCities = new HashSet<SysCity>(0);
	private Set<SysOrg> sysOrgs = new HashSet<SysOrg>(0);

	@Column(name = "PROVINCE_NO", length = 6)
	public String getProvinceNo() {
		return this.provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}

	@Column(name = "PROVINCE_NAME", length = 30)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "PROVINCE_FULL_NAME", length = 60)
	public String getProvinceFullName() {
		return this.provinceFullName;
	}

	public void setProvinceFullName(String provinceFullName) {
		this.provinceFullName = provinceFullName;
	}

	@Column(name = "PROVINCE_SHORT_NAME", length = 30)
	public String getProvinceShortName() {
		return this.provinceShortName;
	}

	public void setProvinceShortName(String provinceShortName) {
		this.provinceShortName = provinceShortName;
	}

	@Column(name = "ALPHABETIC_CODE", nullable = false, length = 30)
	public String getAlphabeticCode() {
		return this.alphabeticCode;
	}

	public void setAlphabeticCode(String alphabeticCode) {
		this.alphabeticCode = alphabeticCode;
	}

	@Column(name = "COUNTRY_ID")
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "SORT_ID")
	public Integer getSortId() {
		return this.sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	@Column(name = "STATUS_CODE", length = 45)
	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STATUS_CHANGE_TIME", length = 19)
	public Date getStatusChangeTime() {
		return this.statusChangeTime;
	}

	public void setStatusChangeTime(Date statusChangeTime) {
		this.statusChangeTime = statusChangeTime;
	}

	@Column(name = "DEL_FLAG")
	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEL_TIME", length = 19)
	public Date getDelTime() {
		return this.delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysProvince")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysProvince")
	public Set<SysCity> getSysCities() {
		return this.sysCities;
	}

	public void setSysCities(Set<SysCity> sysCities) {
		this.sysCities = sysCities;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysProvince")
	public Set<SysOrg> getSysOrgs() {
		return this.sysOrgs;
	}

	public void setSysOrgs(Set<SysOrg> sysOrgs) {
		this.sysOrgs = sysOrgs;
	}

}
