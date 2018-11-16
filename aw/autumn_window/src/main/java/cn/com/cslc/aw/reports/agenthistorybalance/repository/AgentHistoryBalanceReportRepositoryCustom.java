package cn.com.cslc.aw.reports.agenthistorybalance.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceCondition;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceResult;

public interface AgentHistoryBalanceReportRepositoryCustom {
	
	Page<QueryAgentHistoryBalanceResult> queryByCondition(QueryAgentHistoryBalanceCondition queryCondition, Pageable pageable);
	
	//List<Object[]> queryProvinceIdAccountIdByAgentNo(List<String> agentNoList);
}
