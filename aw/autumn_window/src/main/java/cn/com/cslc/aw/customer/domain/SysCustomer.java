package cn.com.cslc.aw.customer.domain;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.com.cslc.aw.core.common.domain.BaseEntity;
import cn.com.cslc.aw.core.common.domain.SysProvince;


@Entity
@Table(name = "SYS_CUSTOMER")
public class SysCustomer extends BaseEntity{


	private String code;
	private String name;
	private String provinceNo;
	private SysProvince sysProvince;
	
	private Set<SysOrgCustomer> sysOrgCustomers = new HashSet<SysOrgCustomer>(0);



	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysCustomer")
	public Set<SysOrgCustomer> getSysOrgCustomers() {
		return this.sysOrgCustomers;
	}

	public void setSysOrgCustomers(Set<SysOrgCustomer> sysOrgCustomers) {
		this.sysOrgCustomers = sysOrgCustomers;
	}
	

	@Column(name = "PROVINCE_NO", length = 6)
	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	public SysProvince getSysProvince() {
		return this.sysProvince;
	}

	public void setSysProvince(SysProvince sysProvince) {
		this.sysProvince = sysProvince;
	}
}
