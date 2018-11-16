package cn.com.cslc.aw.reports.shopcommission.api;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceCondition;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceResult;
import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionCondition;
import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionResult;
import cn.com.cslc.aw.reports.shopcommission.service.ShopCommissionReportService;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/reports/shopCommissionReport")
public class ShopCommissionReportApiController extends BaseApiController{
	
	@Autowired
	private ShopCommissionReportService shopCommissionReportService;
	
	@Autowired
	private AgentService agentService;

	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value = "/search", method = POST)
	public DataTablesResponse<List<QueryShopCommissionResult>> queryPeriodSales(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryShopCommissionCondition queryCondition, @SessionAttribute Set<String> userAgentCodeSet, @SessionAttribute Set<String> userProvinceCodeSet) {

		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.getAgentCodeSet().addAll(userAgentCodeSet);
		}else{
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(queryCondition.getOrgCode()));
		}

		// 1、根据sys_org查sys_province;
		BigDecimal provinceCenterId = null;
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.getProvinceIdSet().addAll(userProvinceCodeSet);
		}else{
			Set<SysProvince> provinceSet = orgService.findProvinceByOrgCode(queryCondition.getOrgCode());
			for(SysProvince province : provinceSet){
				if(province!=null){
					queryCondition.getProvinceIdSet().add(province.getProvinceNo());
				}
			}
		}

		queryCondition.setTechSystemIdSet(new HashSet<String>(Arrays.asList(queryCondition.getTechSystemId().split(","))));

		Page<QueryShopCommissionResult> page = shopCommissionReportService.queryByCondition(queryCondition, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest,page);
	}
}
