package cn.com.cslc.aw.balance.agentbalance.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.balance.agentbalance.dto.QueryAgentBalanceResult;
import cn.com.cslc.aw.balance.agentbalance.service.AgentBalanceService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;

@RestController
@RequestMapping("/api/balance/agentBalance")
public class AgentBalanceApiController extends BaseApiController{
	
	@Autowired
	private AgentBalanceService agentBalanceService;
	
	@RequestMapping(value="/queryBalance", method = POST)
	public DataTablesResponse<List<QueryAgentBalanceResult>> queryAgentBalance(@DataTablesRequestParams DataTablesRequest dataTablesRequest, @SessionAttribute List<SysAgent> userAgentList) {
		List<String> agentCodeList = Lists.newArrayList();
		for(int i =0 ; i < userAgentList.size(); i++){
			agentCodeList.add(userAgentList.get(i).getCode());
		}
		
		Page<QueryAgentBalanceResult> page = agentBalanceService.queryAgentBalance(agentCodeList, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest, page);
	}
}
