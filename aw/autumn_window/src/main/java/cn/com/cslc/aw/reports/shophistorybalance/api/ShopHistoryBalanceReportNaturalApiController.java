package cn.com.cslc.aw.reports.shophistorybalance.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.druid.util.StringUtils;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceCondition;
import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceResult;
import cn.com.cslc.aw.reports.shophistorybalance.service.ShopHistoryBalanceReportService;

@RestController
@RequestMapping("/api/reports/shopHistoryBalanceReportNatural")
public class ShopHistoryBalanceReportNaturalApiController extends BaseApiController{
	
	@Autowired
	private ShopHistoryBalanceReportService shopHistoryBalanceReportService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value = "/search", method = POST)
	public DataTablesResponse<List<QueryShopHistoryBalanceResult>> queryPeriodSales(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryShopHistoryBalanceCondition queryCondition, @SessionAttribute Set<String> userAgentCodeSet, @SessionAttribute Set<String> userProvinceCodeSet) {
	
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.getAgentCodeSet().addAll(userAgentCodeSet);
			queryCondition.getProvinceIdSet().addAll(userProvinceCodeSet);
		}else{
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(queryCondition.getOrgCode()));
			Set<SysProvince> provinceSet = orgService.findProvinceByOrgCode(queryCondition.getOrgCode());
			for(SysProvince province : provinceSet){
				if(province!=null){
					queryCondition.getProvinceIdSet().add(province.getProvinceNo());
				}
			}
		}
		Page<QueryShopHistoryBalanceResult> page = shopHistoryBalanceReportService.queryByConditionNatural(queryCondition, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest,page);
	}
}
