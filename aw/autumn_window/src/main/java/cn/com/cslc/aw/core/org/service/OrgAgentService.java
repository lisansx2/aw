package cn.com.cslc.aw.core.org.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.agent.domain.SysOrgAgent;
import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.org.repository.OrgAgentRepository;

@Transactional(readOnly = true)
@Service
public class OrgAgentService {
	
	@Autowired
	private OrgAgentRepository orgAgentRepository;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private AgentService agentService;
	
	private static final Logger LOG = LoggerFactory.getLogger(OrgAgentService.class);
	
	
	/**
	 * 机构授权 代理
	 * @param orgId
	 * @param selectedAgentIdArr
	 */
	@Transactional
	public void authOrg(Long orgId, Long... selectedRoleIdArr) {
		orgAgentRepository.deleteByOrgId(orgId);
		List<SysOrgAgent> sysOrgAgentList = Lists.newArrayList();
		SysOrg org = orgService.findOneById(orgId);
		if(selectedRoleIdArr != null){
			if(selectedRoleIdArr[0]!=-1){
				for(Long agentId : selectedRoleIdArr){
					SysOrgAgent sysOrgAgent = new SysOrgAgent();
					sysOrgAgent.setSysAgent(agentService.findByAgentId(agentId));
					sysOrgAgent.setSysOrg(org);
					sysOrgAgentList.add(sysOrgAgent);
				}
			}
		}
		if(!sysOrgAgentList.isEmpty()){
			orgAgentRepository.save(sysOrgAgentList);
			LOG.info("机构（orgId:" + orgId + "）授权代理成功");
		}
	}

	
}
