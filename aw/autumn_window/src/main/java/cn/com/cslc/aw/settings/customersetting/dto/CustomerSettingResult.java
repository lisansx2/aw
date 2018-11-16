package cn.com.cslc.aw.settings.customersetting.dto;

import java.util.List;

import cn.com.cslc.aw.core.common.domain.SysProvince;


public class CustomerSettingResult {


	private List<CustomerSettingDefinition> customerDefList;
	
	private SysProvince sysProvince;

	public List<CustomerSettingDefinition> getCustomerSettingDefList() {
		return customerDefList;
	}

	public void setCustomerSettingDefList(List<CustomerSettingDefinition> customerDefList) {
		this.customerDefList = customerDefList;
	}

	public List<CustomerSettingDefinition> getCustomerDefList() {
		return customerDefList;
	}

	public void setCustomerDefList(List<CustomerSettingDefinition> customerDefList) {
		this.customerDefList = customerDefList;
	}

	public SysProvince getSysProvince() {
		return sysProvince;
	}

	public void setSysProvince(SysProvince sysProvince) {
		this.sysProvince = sysProvince;
	}
		

}
