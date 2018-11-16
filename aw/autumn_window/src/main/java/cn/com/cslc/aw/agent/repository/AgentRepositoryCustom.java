package cn.com.cslc.aw.agent.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.dto.B_AgentDto;
import cn.com.cslc.aw.settings.agentsetting.dto.QueryAgentSettingResult;

public interface AgentRepositoryCustom {
	
	/**
	 * 从B_AGENT表中获取AGENT_TYPE_CODE=10的全部代理
	 * @return
	 */
	List<B_AgentDto> getAllAgentInfoFromB_Agent(); 
	
	List<B_AgentDto> getAllAgentInfoByProvinceNo(String provinceNo);
	
	List<SysAgent> findUnusedAgentByProvinceNo(String provinceNo,Long orgId);
	
	B_AgentDto getAllAgentInfoFromB_AgentByNo(String agentNo); 
	
	Page<QueryAgentSettingResult> queryAgentListByProvinceCodes(Set<String> provinceCodesSet, Pageable pageable);
}
