package cn.com.cslc.aw.reports.agenthistorybalance.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceCondition;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceResult;
import cn.com.cslc.aw.reports.agenthistorybalance.service.AgentHistoryBalanceReportService;

@RestController
@RequestMapping("/api/reports/agentHistoryBalanceReport")
public class AgentHistoryBalanceReportApiController extends BaseApiController{
	
	@Autowired
	private AgentHistoryBalanceReportService agentHistoryBalanceReportService;
	
	@Autowired
	private AgentService agentService;
	
	@RequestMapping(value = "/search", method = POST)
	public DataTablesResponse<List<QueryAgentHistoryBalanceResult>> queryPeriodSales(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryAgentHistoryBalanceCondition queryCondition, @SessionAttribute Set<String> userAgentCodeSet) {
		queryCondition.setAgentCodeSet(userAgentCodeSet);
		String agentCode =queryCondition.getAgentCode();
		if(agentCode == null || "".equals(agentCode)){
			queryCondition.setProvinceIdSet(agentService.getProvinceIdByAgentNo(userAgentCodeSet));
		}else{
			Set<String> agentCodeSet = Sets.newHashSet();
			agentCodeSet.add(agentCode);
			queryCondition.setProvinceId(agentService.getProvinceIdByAgentNo(agentCodeSet).iterator().next());
		}

		Page<QueryAgentHistoryBalanceResult> page = agentHistoryBalanceReportService.queryByCondition(queryCondition, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest,page);
	}
}
