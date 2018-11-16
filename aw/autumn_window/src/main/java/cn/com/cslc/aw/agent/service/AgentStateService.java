package cn.com.cslc.aw.agent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cslc.aw.agent.domain.SysAgentState;
import cn.com.cslc.aw.agent.repository.AgentStateRepository;
import cn.com.cslc.aw.core.common.constant.SysAgentStateConstant;

@Transactional(readOnly = true)
@Service
public class AgentStateService {
	
	@Autowired
	private AgentStateRepository agentStateRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(AgentStateService.class);
	
	public SysAgentState getAgentState(SysAgentStateConstant agentState){
		return agentStateRepository.findByCode(agentState.getCode());
	}
	
}
