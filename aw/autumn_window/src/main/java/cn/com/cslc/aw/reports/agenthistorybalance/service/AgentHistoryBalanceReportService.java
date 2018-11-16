package cn.com.cslc.aw.reports.agenthistorybalance.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceCondition;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceResult;
import cn.com.cslc.aw.reports.agenthistorybalance.repository.AgentHistoryBalanceReportRepository;



@Transactional(readOnly = true)
@Service
public class AgentHistoryBalanceReportService {

	private static final Logger LOG = LoggerFactory.getLogger(AgentHistoryBalanceReportService.class);

	@Autowired
	private AgentHistoryBalanceReportRepository agentHistoryBalanceReportRepository;
	
	public Page<QueryAgentHistoryBalanceResult> queryByCondition(QueryAgentHistoryBalanceCondition queryCondition,
			Pageable pageRequest) {
		return agentHistoryBalanceReportRepository.queryByCondition(queryCondition, pageRequest);
	}

}
