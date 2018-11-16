package cn.com.cslc.aw.core.common.domain;
// Generated 2017-4-12 13:58:55 by Hibernate Tools 5.1.2.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysResourceType generated by hbm2java
 */
@Entity
@Table(name = "sys_resource_type")
public class SysResourceType extends BaseEntity {

	private String name;
	private String code;
	private Set<SysResource> sysResources = new HashSet<SysResource>(0);

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysResourceType")
	public Set<SysResource> getSysResources() {
		return this.sysResources;
	}

	public void setSysResources(Set<SysResource> sysResources) {
		this.sysResources = sysResources;
	}

}
