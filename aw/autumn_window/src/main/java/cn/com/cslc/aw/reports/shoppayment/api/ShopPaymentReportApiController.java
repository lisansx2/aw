package cn.com.cslc.aw.reports.shoppayment.api;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.bank.dto.B_BankDto;
import cn.com.cslc.aw.bank.service.BBankService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.domain.dto.B_DomainDto;
import cn.com.cslc.aw.domain.service.BDomainService;
import cn.com.cslc.aw.province.dto.B_ProvinceCenterDto;
import cn.com.cslc.aw.province.service.BProvinceCenterService;
import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentCondition;
import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentResult;
import cn.com.cslc.aw.reports.shoppayment.service.ShopPaymentReportService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/reports/shopPaymentReport")
public class ShopPaymentReportApiController extends BaseApiController{
	
	@Autowired
	private ShopPaymentReportService shopPaymentReportService;

	@Autowired
	private AgentService agentService;

	@Autowired
	private BProvinceCenterService provinceCenterService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private BBankService bankService;

	@Autowired
	private BDomainService domainService;
	
	@RequestMapping(value = "/search", method = POST)
	public DataTablesResponse<List<QueryShopPaymentResult>> queryPeriodSales(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryShopPaymentCondition queryCondition, @SessionAttribute SysUser currentUser, @SessionAttribute Set<String> userAgentCodeSet, @SessionAttribute Set<String> userProvinceCodeSet) {

		// 根据sys_org查sys_agent
		StringBuffer agentList = new StringBuffer();
		String tmpAgentList = null;
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.getAgentCodeSet().addAll(userAgentCodeSet);
		}else{
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(queryCondition.getOrgCode()));
		}

		Page<QueryShopPaymentResult> page = shopPaymentReportService.queryByCondition(queryCondition, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest,page);
	}
}
