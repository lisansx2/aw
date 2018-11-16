package cn.com.cslc.aw.balance.shopbalance.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.balance.shopbalance.dto.QueryShopBalanceCondition;
import cn.com.cslc.aw.balance.shopbalance.dto.QueryShopBalanceResult;
import cn.com.cslc.aw.balance.shopbalance.service.ShopBalanceService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;

@RestController
@RequestMapping("/api/balance/shopBalance")
public class ShopBalanceApiController extends BaseApiController{
	
	@Autowired
	private ShopBalanceService shopBalanceService;
	
	@Autowired
	private AgentService agentService;
	
	@RequestMapping(value="/queryBalance", method = POST)
	public DataTablesResponse<List<QueryShopBalanceResult>> queryShopBalance(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryShopBalanceCondition queryShopBalanceCondition, @SessionAttribute Set<String> userAgentCodeSet) {
		if(StringUtils.isEmpty(queryShopBalanceCondition.getOrgCode())){
			queryShopBalanceCondition.getAgentCodeList().addAll(userAgentCodeSet);
		}else{
			queryShopBalanceCondition.getAgentCodeList().addAll(agentService.findAgentCodesByOrgCode(queryShopBalanceCondition.getOrgCode()));
		}

		Page<QueryShopBalanceResult> page = shopBalanceService.queryShopBalanceResultByCondition(queryShopBalanceCondition, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest, page);
	}
}
