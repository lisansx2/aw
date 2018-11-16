package cn.com.cslc.aw.core.org.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.org.repository.OrgCustomerRepository;
import cn.com.cslc.aw.customer.domain.SysOrgCustomer;
import cn.com.cslc.aw.customer.service.CustomerService;

@Transactional(readOnly = true)
@Service
public class OrgCustomerService {
	
	@Autowired
	private OrgCustomerRepository orgCustomerRepository;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private CustomerService customerService;
	
	private static final Logger LOG = LoggerFactory.getLogger(OrgCustomerService.class);
	
	
	/**
	 * 机构授权 大客户
	 * @param orgId
	 * @param selectedCustomerIdArr
	 */
	@Transactional
	public void authOrg(Long orgId, Long[] selectedCustomerIdArr) {
		orgCustomerRepository.deleteByOrgId(orgId);
		List<SysOrgCustomer> sysOrgCustomerList = Lists.newArrayList();
		SysOrg org = orgService.findOneById(orgId);
		if(selectedCustomerIdArr != null){
			if(selectedCustomerIdArr[0]!=-1){
				for(Long customerId : selectedCustomerIdArr){
					SysOrgCustomer sysOrgCustomer = new SysOrgCustomer();
					sysOrgCustomer.setSysCustomer(customerService.findByCustomerId(customerId));
					sysOrgCustomer.setSysOrg(org);
					sysOrgCustomerList.add(sysOrgCustomer);
				}
			}
		}
		if(!sysOrgCustomerList.isEmpty()){
			orgCustomerRepository.save(sysOrgCustomerList);
			LOG.info("机构（orgId:" + orgId + "）授权大客户成功");
		}
		
	}

	
}
