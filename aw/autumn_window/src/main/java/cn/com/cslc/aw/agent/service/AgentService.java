package cn.com.cslc.aw.agent.service;

import java.util.Date;
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

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.dto.AgentProvince;
import cn.com.cslc.aw.agent.dto.B_AgentDto;
import cn.com.cslc.aw.agent.repository.AgentRepository;
import cn.com.cslc.aw.core.common.constant.SysAgentStateConstant;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.exception.BusinessLogicException;
import cn.com.cslc.aw.core.common.service.BaseService;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.settings.agentsetting.dto.QueryAgentSettingResult;

@Transactional(readOnly = true)
@Service
public class AgentService {

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private AgentStateService agentStateService;
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private OrgService orgService;

	private static final Logger LOG = LoggerFactory.getLogger(AgentService.class);

	@Transactional
	public void synAgentInfo() {
		List<B_AgentDto> agentDtos = agentRepository.getAllAgentInfoFromB_Agent();
		for (B_AgentDto agentDto : agentDtos) {
			String agentNo = agentDto.getAgentNo();
			SysAgent sysAgent = agentRepository.findByCode(agentNo);
			
			int agentStatusCode = agentDto.getStatusCode().intValue();
			SysAgentStateConstant agentState = this.convertAgentState(agentStatusCode);
			int agentDelFlag = agentDto.getDelFlag().intValue();
			if(agentDelFlag == 1){
				agentState = this.convertAgentState(agentDelFlag);
			}
			
			Date currentTime = new Date();
			if (sysAgent == null) {//新增
				sysAgent = new SysAgent();
				sysAgent.setCode(agentNo);
				sysAgent.setCreateDate(currentTime);
			}
			sysAgent.setName(agentDto.getAgentName());
			sysAgent.setSysAgentState(agentStateService.getAgentState(agentState));
			sysAgent.setLastOperDate(currentTime);
			agentRepository.save(sysAgent);
		}
	}
	
	private SysAgentStateConstant convertAgentState(int code){
		switch (code) {
		case 10:// 已启用
			return SysAgentStateConstant.Enabled;
		case 0:// 已停用
			return SysAgentStateConstant.Disable;
		case 1:// 已删除
			return SysAgentStateConstant.Deleted;
		default:
			return SysAgentStateConstant.Disable;
		}
	}
	
	public List<SysAgent> findAllAgent(){
		return agentRepository.findAll();
	}

	public List<SysAgent> findAgentByProvinceNo(String provinceNo){
		return agentRepository.findByProvinceNo(provinceNo);
	}
	
	public List<SysAgent> findUnusedAgentByProvinceNo(String provinceNo,Long orgId){
		return agentRepository.findUnusedAgentByProvinceNo(provinceNo,orgId);
	}
	
	public List<SysAgent> findAgentByProvinceId(Long provinceId){
		return agentRepository.findByProvinceId(provinceId);
	}
	
	public SysAgent getAgentByCode(String agentCode) {
		return agentRepository.findByCode(agentCode);
	}
	
	public Set<String> getProvinceIdByAgentNo(Set<String> agentNoSet){
		List<AgentProvince> agentProvinceList = agentRepository.findByCodeIn(agentNoSet);
		Set<String> provinceIdSet = Sets.newHashSet();
		for(AgentProvince record : agentProvinceList) {
		    provinceIdSet.add(record.getProvinceNo());
		}
		return provinceIdSet;
	}
	
	public List<B_AgentDto> getAllAgentDtoByProvinceNo(String provinceNo){
		return 	agentRepository.getAllAgentInfoByProvinceNo(provinceNo);
	}

	public Page<QueryAgentSettingResult> queryAgentListByProvinceCodes(Set<String> provinceCodesSet, Pageable pageable) {
		return agentRepository.queryAgentListByProvinceCodes(provinceCodesSet, pageable);
	}


	/**
	 * 设置代理
	 * 1、遍历原始代理，判断是原始代理是否在已选择代理集合中
	 * 如果在，则从已选择代理集合中删除该代理，继续；否则删除原始代理。
	 * 2、保存剩余的已选择代理集合
	 * @param provinceNo
	 * @param selectedAgentIdArr
	 */
	@Transactional
	public void setAgent(String provinceNo, Set<String> selectedAgentNoSet) {
		List<SysAgent> oldAgentList = agentRepository.findByProvinceNo(provinceNo);
		for(SysAgent oldAgent : oldAgentList){
			String oldAgentCode =oldAgent.getCode();
			if(!selectedAgentNoSet.contains(oldAgentCode)){
				//判断机构是否引用代理
				if(oldAgent.getSysOrgAgents().isEmpty()){
					agentRepository.delete(oldAgent);
				}else{
					LOG.info("代理（" +  oldAgent.getName() + "）存在引用的机构");
					throw new BusinessLogicException("存在引用代理的机构，操作失败");
				}
			}else{
				selectedAgentNoSet.remove(oldAgentCode);
			}
		}
		for(String agentNo : selectedAgentNoSet){
			B_AgentDto  agentDto = agentRepository.getAllAgentInfoFromB_AgentByNo(agentNo);
			SysAgent newAgent = new SysAgent();
			newAgent.setCode(agentNo);
			newAgent.setName(agentDto.getAgentName());
			newAgent.setProvinceNo(provinceNo);
			newAgent.setSysProvince(baseService.getSysProvinceByNo(provinceNo));
			newAgent.setSysAgentState(agentStateService.getAgentState(SysAgentStateConstant.Enabled));
			agentRepository.save(newAgent);
		}
	}
	
	public List<SysAgent> findAgentByOrgCodes(Set<String> orgCodes){
		return agentRepository.findAgentByOrgCodes(orgCodes);
	}
	
	/**
	 * 获取该机构及下属机构的代理编码
	 * @param orgCode
	 * @return
	 */
	public List<String> findAgentCodesByOrgCode(String orgCode){
		List<String> agentCodeList = Lists.newArrayList();
		Set<String> orgCodes = Sets.newHashSet();
		Set<SysOrg> orgSet = orgService.findOrgsByOrgCode(orgCode);
		for(SysOrg org : orgSet){
			orgCodes.add(org.getCode());
		}
		List<SysAgent> agentList = this.findAgentByOrgCodes(orgCodes);
		for(SysAgent agent : agentList){
			agentCodeList.add(agent.getCode());
		}
		return agentCodeList;
	}

	public SysAgent findByAgentId(Long agentId) {
		return agentRepository.findOne(agentId);
	}
}
