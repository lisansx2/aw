package cn.com.cslc.aw.customer.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.agent.dto.B_AgentDto;
import cn.com.cslc.aw.customer.domain.SysCustomer;
import cn.com.cslc.aw.customer.dto.MD_ChannelCustomerDto;

import cn.com.cslc.aw.settings.customersetting.dto.QueryCustomerSettingResult;


public interface CustomerRepositoryCustom {

	List<MD_ChannelCustomerDto> getCustomerDtoByProvinceNo(String provinceNo);
	
	List<SysCustomer> findUnusedCustomByProvinceNo(String provinceNo,Long orgId);
	
	Page<QueryCustomerSettingResult> queryCustomerListByProvinceCodes(Set<String> provinceCodesSet, Pageable pageable);
	
	List<MD_ChannelCustomerDto> getAllCustomerFromMD_ChannelCustomerByNo(String customerNo); 
}
