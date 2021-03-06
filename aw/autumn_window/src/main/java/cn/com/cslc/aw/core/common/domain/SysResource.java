package cn.com.cslc.aw.core.common.domain;
// Generated 2017-3-29 13:36:52 by Hibernate Tools 5.1.2.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * SysResource generated by hbm2java
 */
@Entity
@Table(name = "sys_resource")
public class SysResource extends BaseEntity {
	
	private String name;
	private String identifier;
	private String url;
	private Long parentId;
	private String parentIds;
	private Integer weight;
	private Byte hasChildren;
	private Byte isShow;
	private String icon;
	private Set<SysRoleResource> sysRoleResource = new HashSet<SysRoleResource>(0);
	private SysResourceType sysResourceType;
	
	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "identifier", length = 100)
	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "parent_id")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "parent_ids", length = 200)
	public String getParentIds() {
		return this.parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Column(name = "weight")
	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Column(name = "has_children")
	public Byte getHasChildren() {
		return this.hasChildren;
	}

	public void setHasChildren(Byte hasChildren) {
		this.hasChildren = hasChildren;
	}

	@Column(name = "is_show")
	public Byte getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Byte isShow) {
		this.isShow = isShow;
	}
	
	@Column(name = "icon", length = 200)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysResource")
	public Set<SysRoleResource> getSysRoleResource() {
		return this.sysRoleResource;
	}

	public void setSysRoleResource(Set<SysRoleResource> sysRoleResource) {
		this.sysRoleResource = sysRoleResource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_type_id")
	public SysResourceType getSysResourceType() {
		return this.sysResourceType;
	}

	public void setSysResourceType(SysResourceType sysResourceType) {
		this.sysResourceType = sysResourceType;
	}
}
