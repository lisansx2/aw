package cn.com.cslc.aw.saleperiod.dto;

import java.math.BigDecimal;
import java.util.Date;

public class C_SalePeriodDto {

	private BigDecimal salePeriodId	;
	private BigDecimal provinceCenterId	;
	private String beginTime	;
	private String endTime	;
	private String salePeriodNo	;
	private Date createDate	;
	private BigDecimal createUserId	;
	private BigDecimal settledFlag	;
	private Date settledTime	;
	private BigDecimal commissionModeTypeCode	;
	private BigDecimal tigerFlag	;
	private BigDecimal delFlag	;
	private Date delTime	;

	public BigDecimal getSalePeriodId() {
		return salePeriodId;
	}

	public void setSalePeriodId(BigDecimal salePeriodId) {
		this.salePeriodId = salePeriodId;
	}

	public BigDecimal getProvinceCenterId() {
		return provinceCenterId;
	}

	public void setProvinceCenterId(BigDecimal provinceCenterId) {
		this.provinceCenterId = provinceCenterId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSalePeriodNo() {
		return salePeriodNo;
	}

	public void setSalePeriodNo(String salePeriodNo) {
		this.salePeriodNo = salePeriodNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(BigDecimal createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getSettledFlag() {
		return settledFlag;
	}

	public void setSettledFlag(BigDecimal settledFlag) {
		this.settledFlag = settledFlag;
	}

	public Date getSettledTime() {
		return settledTime;
	}

	public void setSettledTime(Date settledTime) {
		this.settledTime = settledTime;
	}

	public BigDecimal getCommissionModeTypeCode() {
		return commissionModeTypeCode;
	}

	public void setCommissionModeTypeCode(BigDecimal commissionModeTypeCode) {
		this.commissionModeTypeCode = commissionModeTypeCode;
	}

	public BigDecimal getTigerFlag() {
		return tigerFlag;
	}

	public void setTigerFlag(BigDecimal tigerFlag) {
		this.tigerFlag = tigerFlag;
	}

	public BigDecimal getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(BigDecimal delFlag) {
		this.delFlag = delFlag;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
}
