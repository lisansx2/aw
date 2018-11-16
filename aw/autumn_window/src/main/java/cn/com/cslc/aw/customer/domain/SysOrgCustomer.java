package cn.com.cslc.aw.customer.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import cn.com.cslc.aw.core.common.domain.BaseEntity;
import cn.com.cslc.aw.core.common.domain.SysOrg;

@Entity
@Table(name = "SYS_ORG_CUSTOMER")
public class SysOrgCustomer extends BaseEntity{
	private SysOrg sysOrg;
	private SysCustomer sysCustomer;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	public SysOrg getSysOrg() {
		return this.sysOrg;
	}

	public void setSysOrg(SysOrg sysOrg) {
		this.sysOrg = sysOrg;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	public SysCustomer getSysCustomer() {
		return this.sysCustomer;
	}

	public void setSysCustomer(SysCustomer sysCustomer) {
		this.sysCustomer = sysCustomer;
	}
}
