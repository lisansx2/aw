package cn.com.cslc.aw.balance.agentbalance.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cslc.aw.balance.agentbalance.dto.QueryAgentBalanceResult;
import cn.com.cslc.aw.balance.agentbalance.repository.AgentBalanceRepository;

@Transactional(readOnly = true)
@Service
public class AgentBalanceService {

	private static final Logger LOG = LoggerFactory.getLogger(AgentBalanceService.class);
	
	@Autowired
	private AgentBalanceRepository agentBalanceRepository;

	public Page<QueryAgentBalanceResult> queryAgentBalance(List<String> agentCodeList, Pageable pageRequest) {
		return agentBalanceRepository.queryAgentBalanceResultByAgentCodes(agentCodeList, pageRequest);
	}
}
