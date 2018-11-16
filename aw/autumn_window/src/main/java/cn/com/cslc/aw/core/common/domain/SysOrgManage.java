package cn.com.cslc.aw.core.common.domain;
// Generated 2017-9-19 15:59:38 by Hibernate Tools 5.1.2.Final



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * SysOrg generated by hbm2java
 */
@Entity
@Table(name = "SYS_ORG")
public class SysOrgManage  extends BaseEntity  implements Comparable<SysOrgManage>{

	private Long parentId;
	private String name;
	private String parentIds;
	private Short isShow;
	private Short hasChildren;
	private Long weight;
	private String code;
	private String parentCode;
	private Long provinceId;
	private Long orgTypeId;
	private Long delFlag;
	private Date delTime;
	private Date updateTime;
	
	@Column(name = "DEL_FLAG", precision = 1, scale = 0)
	public Long getDelFlag() {
		return delFlag;
	}
	
	public void setDelFlag(Long delFlag) {
		this.delFlag = delFlag;
	}
	
	@Column(name = "DEL_TIME")
	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	
	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "PARENT_ID", precision = 19, scale = 0)
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "province_id", precision = 19, scale = 0)
	public Long getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "ORG_TYPE_ID", precision = 19, scale = 0)
	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	@Column(name = "NAME", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENT_IDS", length = 400)
	public String getParentIds() {
		return this.parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Column(name = "IS_SHOW", precision = 3, scale = 0)
	public Short getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}

	@Column(name = "HAS_CHILDREN", precision = 3, scale = 0)
	public Short getHasChildren() {
		return this.hasChildren;
	}

	public void setHasChildren(Short hasChildren) {
		this.hasChildren = hasChildren;
	}

	@Column(name = "WEIGHT", precision = 10, scale = 0)
	public Long getWeight() {
		return this.weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "PARENT_CODE", length = 20)
	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Override
	public int compareTo(SysOrgManage o) {
		return this.getCode().compareTo(o.getCode());
	}
}
