package cn.com.cslc.aw.reports.shopinstantpromotion.api;

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

import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.customer.service.CustomerService;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionCondition;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionResult;
import cn.com.cslc.aw.reports.shopinstantpromotion.service.ShopInstantPromotionReportService;


@RestController
@RequestMapping("/api/reports/shopInstantPromotionReport")
public class ShopInstantPromotionReportApiController extends BaseApiController{
	
	@Autowired
	private ShopInstantPromotionReportService shopInstantPromotionReportService;
	
	@Autowired
	private CustomerService customerService;
	

		
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public DataTablesResponse<List<QueryShopInstantPromotionResult>> queryPeriodSales(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryShopInstantPromotionCondition queryCondition, @SessionAttribute Set<String> userCustomerCodeSet, @SessionAttribute Set<String> userProvinceCodeSet) {

		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.getCustomerCodeSet().addAll(userCustomerCodeSet);
		}else{
			queryCondition.getCustomerCodeSet().addAll(customerService.findCustomerCodesByOrgCode(queryCondition.getOrgCode()));
		}
		
		/*Set<String> gameNoSet = Sets.newHashSet();
		
		if(StringUtils.isEmpty(queryCondition.getPromotionNo())){
			for(SysGameProjection game : gameService.findInstantGameByProvinceNos(userProvinceCodeSet)){
				gameNoSet.add(game.getGameNo());
			}
		}else{
			gameNoSet.add(queryCondition.getPromotionNo());
		}
		queryCondition.setGameNoSet(gameNoSet);
		*/
		
		Page<QueryShopInstantPromotionResult> page = shopInstantPromotionReportService.queryByCondition(queryCondition, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest,page);
	}
}
