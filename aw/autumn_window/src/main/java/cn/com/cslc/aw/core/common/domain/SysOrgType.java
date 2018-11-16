package cn.com.cslc.aw.core.common.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sys_org_type")
public class SysOrgType  extends BaseEntity {

	private String code;
	private String name;
	private Set<SysOrg> sysOrgs = new HashSet<SysOrg>(0);

	@Column(name = "code", length = 10)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysOrgType")
	public Set<SysOrg> getSysOrgs() {
		return this.sysOrgs;
	}

	public void setSysOrgs(Set<SysOrg> sysOrgs) {
		this.sysOrgs = sysOrgs;
	}


}
