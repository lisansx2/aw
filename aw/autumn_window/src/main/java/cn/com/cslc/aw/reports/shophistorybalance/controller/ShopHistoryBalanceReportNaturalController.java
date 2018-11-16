package cn.com.cslc.aw.reports.shophistorybalance.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.controller.JasperReportsBaseController;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.jasperreports.JRRequest;
import cn.com.cslc.aw.core.common.jasperreports.JRRequestParams;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceCondition;
import cn.com.cslc.aw.reports.shophistorybalance.service.ShopHistoryBalanceReportService;


@Controller
@RequestMapping("/reports/shopHistoryBalanceReportNatural")
public class ShopHistoryBalanceReportNaturalController extends JasperReportsBaseController{
	
	private String indexViewName = "redirect:/reports/shopHistoryBalanceReportNaturalIndex";
	
	@Autowired
	private AgentService agentService;
	 
	@Autowired
	private OrgService orgService;
	
	@ModelAttribute("userAgentList")
    public List<SysAgent> populateUserAgent(@SessionAttribute List<SysAgent> userAgentList) {
        return userAgentList;
    }
	
	@RequestMapping(method=RequestMethod.GET)
	public String showReportIndex(){
		return "reports/shopHistoryBalanceReportNaturalIndex";
	}
	
	@RequestMapping(value = "/exportFile/*", method = RequestMethod.GET)
	public String exportFile(@JRRequestParams JRRequest  jrRequest, ModelMap modelMap, @SessionAttribute SysUser currentUser,@SessionAttribute Set<String> userAgentCodeSet,@SessionAttribute Set<String> userProvinceCodeSet) {
		Map<String,String> requestParamsMap = jrRequest.getReportParamMap();
		QueryShopHistoryBalanceCondition queryCondition = new QueryShopHistoryBalanceCondition();
		queryCondition.setEndDate(requestParamsMap.get("endDate"));
		queryCondition.setStartDate(requestParamsMap.get("startDate"));
		queryCondition.setShopCode(requestParamsMap.get("shopCode"));
		queryCondition.setUserOrg(requestParamsMap.get("userOrg"));
		
		modelMap.put("startDate", queryCondition.getStartDate() + " 00:00:00");
		modelMap.put("endDate", queryCondition.getEndDate() + " 23:59:59");
		
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			modelMap.put("belongOrgName", "全部");
		}else{
			modelMap.put("belongOrgName", orgService.findOrgByCode(queryCondition.getOrgCode()).getName());
		}
		
		if(StringUtils.isEmpty(queryCondition.getShopCode())){
			modelMap.put("shopCode", "全部");
		}else{
			modelMap.put("shopCode", queryCondition.getShopCode());
		}
		
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.setAgentCodeSet(userAgentCodeSet);
			queryCondition.setProvinceIdSet(userProvinceCodeSet);
		}else{
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(queryCondition.getOrgCode()));
			Set<SysProvince> provinceSet = orgService.findProvinceByOrgCode(queryCondition.getOrgCode());
			for(SysProvince province : provinceSet){
				if(province!=null){
					queryCondition.getProvinceIdSet().add(province.getProvinceNo());
				}
			}
		}
		modelMap.put("provinceIdSet",queryCondition.getProvinceIdSet());
		modelMap.put("agentNoSet", queryCondition.getAgentCodeSet());
		
		String shopCode = queryCondition.getShopCode();
		if(StringUtils.isEmpty(shopCode)){
			modelMap.put("shopNoStr", "%%");
		}else{
			modelMap.put("shopNoStr", "%" + shopCode + "%");
		}
		return getReportView(jrRequest, modelMap);
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;
	}
}
