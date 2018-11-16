package cn.com.cslc.aw.settings.customersetting.dto;

public class CustomerSettingDefinition {

	
	private Long customerId;
	
	private String customerNo;

	private String customerName;
	
	private Long provinceId;
	
	
	private Boolean isSelected;


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getCustomerNo() {
		return customerNo;
	}


	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Long getProvinceId() {
		return provinceId;
	}


	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}


	public Boolean getIsSelected() {
		return isSelected;
	}


	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	
}
