package cn.com.cslc.aw.customer.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.exception.BusinessLogicException;
import cn.com.cslc.aw.core.common.service.BaseService;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.customer.domain.SysCustomer;
import cn.com.cslc.aw.customer.dto.MD_ChannelCustomerDto;
import cn.com.cslc.aw.customer.repository.CustomerRepository;
import cn.com.cslc.aw.settings.customersetting.dto.QueryCustomerSettingResult;




@Transactional(readOnly = true)
@Service
public class CustomerService {

	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private OrgService orgService;
	
	public Page<QueryCustomerSettingResult> queryCustomerListByProvinceCodes(Set<String> provinceCodesSet, Pageable pageable) {
		return customerRepository.queryCustomerListByProvinceCodes(provinceCodesSet, pageable);
	}
	
	public List<MD_ChannelCustomerDto> getCustomerDtoByProvinceNo(String provinceNo){
		
		return customerRepository.getCustomerDtoByProvinceNo(provinceNo);
	}
	
	public List<SysCustomer> findByProvinceNo(String provinceNo){
		return customerRepository.findByProvinceNo(provinceNo);
		
	}
	
	public List<SysCustomer> findUnusedCustomByProvinceNo(String provinceNo,Long orgId){
		return customerRepository.findUnusedCustomByProvinceNo(provinceNo,orgId);
		
	}
	
	public List<SysCustomer> findByProvinceId(Long provinceId){
		return customerRepository.findByProvinceId(provinceId);
		
	}
	
	@Transactional
	public void setCustomer(String provinceNo, Set<String> selectedCustomerNoSet) {
		List<SysCustomer> oldCustomerList = customerRepository.findByProvinceNo(provinceNo);
		for(SysCustomer oldCustomer : oldCustomerList){
			String oldCustomerCode =oldCustomer.getCode();
			if(!selectedCustomerNoSet.contains(oldCustomerCode)){
				//判断机构是否引用大客户
				if(oldCustomer.getSysOrgCustomers().isEmpty()){
					customerRepository.delete(oldCustomer);
				}else{
					LOG.info("代理（" +  oldCustomer.getName() + "）存在引用大客户的机构");
					throw new BusinessLogicException("存在引用大客户的机构，操作失败");
				}
			}else{
				selectedCustomerNoSet.remove(oldCustomerCode);
			}
		}
		SysProvince province=baseService.getSysProvinceByNo(provinceNo);
		
		for(String customerNo : selectedCustomerNoSet){
			List<MD_ChannelCustomerDto> customerDtoList = customerRepository.getAllCustomerFromMD_ChannelCustomerByNo(customerNo);
			SysCustomer newCustomer = new SysCustomer();
			newCustomer.setCode(customerNo);
			newCustomer.setName(customerDtoList.get(0).getCustomerName());
			newCustomer.setProvinceNo(provinceNo);
			newCustomer.setSysProvince(province);
			customerRepository.save(newCustomer);
		}
	}

	/**
	 * 获取该机构及下属机构的大客户编码
	 * @param orgCode
	 * @return
	 */
	public List<String> findCustomerCodesByOrgCode(String orgCode){
		List<String> customerCodeList = Lists.newArrayList();
		Set<String> orgCodes = Sets.newHashSet();
		Set<SysOrg> orgSet = orgService.findOrgsByOrgCode(orgCode);
		for(SysOrg org : orgSet){
			orgCodes.add(org.getCode());
		}
		List<SysCustomer> customerList = this.findCustomerByOrgCodes(orgCodes);
		for(SysCustomer customer : customerList){
			customerCodeList.add(customer.getCode());
		}
		return customerCodeList;
	}
	
	public List<SysCustomer> findCustomerByOrgCodes(Set<String> orgCodes){
		return customerRepository.findCustomerByOrgCodes(orgCodes);
	}

	public SysCustomer findByCustomerId(Long customerId) {
		return customerRepository.findOne(customerId);
	}
	
}
