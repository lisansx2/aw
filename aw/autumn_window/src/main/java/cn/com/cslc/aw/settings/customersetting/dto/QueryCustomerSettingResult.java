package cn.com.cslc.aw.settings.customersetting.dto;

public class QueryCustomerSettingResult {
	
	private String provinceName;
	
	private String customerSettingList;
		
	private String provinceNo;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCustomerSettingList() {
		return customerSettingList;
	}

	public void setCustomerSettingList(String customerSettingList) {
		this.customerSettingList = customerSettingList;
	}

	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}
}
