package cn.com.cslc.aw.reports.periodsales.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.thymeleaf.util.StringUtils;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.game.domain.SysGame;
import cn.com.cslc.aw.game.service.GameService;
import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesCondition;
import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesResult;
import cn.com.cslc.aw.reports.periodsales.service.PeriodSalesReportService;

@RestController
@RequestMapping("/api/reports/periodSalesReport")
public class PeriodSalesReportApiController extends BaseApiController{
	
	@Autowired
	private PeriodSalesReportService periodSalesReportService;
	
	@Autowired
	private GameService gameService;
	@Autowired
	private AgentService agentService;
	
	@RequestMapping(value = "/search", method = POST)
	public DataTablesResponse<List<QueryPeriodSalesResult>> queryPeriodSales(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryPeriodSalesCondition queryCondition, @SessionAttribute Set<String> userAgentCodeSet) {
		//queryCondition.setAgentCodeSet(userAgentCodeSet);
//		Set<String> gameNoSet = Sets.newHashSet();
//		if(StringUtils.isEmpty(queryCondition.getGameNo())){
//			for(SysGame game : gameService.findNonInstantGames()){
//				gameNoSet.add(game.getGameNo());
//			}
//		}else{
//			gameNoSet.add(queryCondition.getGameNo());
//		}
//		queryCondition.setGameNoSet(gameNoSet);
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.getAgentCodeSet().addAll(userAgentCodeSet);
		}else{
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(queryCondition.getOrgCode()));
		}
		Page<QueryPeriodSalesResult> page = periodSalesReportService.queryPeriodSalesByCondition(queryCondition, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest,page);
	}
}
