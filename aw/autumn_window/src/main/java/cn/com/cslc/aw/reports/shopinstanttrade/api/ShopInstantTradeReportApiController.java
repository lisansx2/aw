package cn.com.cslc.aw.reports.shopinstanttrade.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.customer.service.CustomerService;
import cn.com.cslc.aw.game.dto.SysGameProjection;
import cn.com.cslc.aw.game.service.GameService;
import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeCondition;
import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeResult;
import cn.com.cslc.aw.reports.shopinstanttrade.service.ShopInstantTradeReportService;

@RestController
@RequestMapping("/api/reports/shopInstantTradeReport")
public class ShopInstantTradeReportApiController extends BaseApiController{
	
	@Autowired
	private ShopInstantTradeReportService shopInstantTradeReportService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private GameService gameService;
		
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public DataTablesResponse<List<QueryShopInstantTradeResult>> queryPeriodSales(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryShopInstantTradeCondition queryCondition, @SessionAttribute Set<String> userCustomerCodeSet, @SessionAttribute Set<String> userProvinceCodeSet) {

		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.getCustomerCodeSet().addAll(userCustomerCodeSet);
		}else{
			queryCondition.getCustomerCodeSet().addAll(customerService.findCustomerCodesByOrgCode(queryCondition.getOrgCode()));
		}
		
		Set<String> gameNoSet = Sets.newHashSet();
		if(StringUtils.isEmpty(queryCondition.getGameNo())){
			for(SysGameProjection game : gameService.findInstantGameByByProvinceNos(userProvinceCodeSet)){
				gameNoSet.add(game.getGameNo());
			}
		}else{
			gameNoSet.add(queryCondition.getGameNo());
		}
		queryCondition.setGameNoSet(gameNoSet);
		
		Page<QueryShopInstantTradeResult> page = shopInstantTradeReportService.queryByCondition(queryCondition, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest,page);
	}
}
