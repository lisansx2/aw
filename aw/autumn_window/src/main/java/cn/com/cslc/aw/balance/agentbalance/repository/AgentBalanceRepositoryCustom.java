package cn.com.cslc.aw.balance.agentbalance.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.balance.agentbalance.dto.QueryAgentBalanceResult;

public interface AgentBalanceRepositoryCustom {
	
	Page<QueryAgentBalanceResult> queryAgentBalanceResultByAgentCodes(List<String> agentCodeList, Pageable pageable);
	
}
