package cn.com.cslc.aw.reports.shopcommission.controller;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.controller.JasperReportsBaseController;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.jasperreports.JRRequest;
import cn.com.cslc.aw.core.common.jasperreports.JRRequestParams;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.province.dto.B_ProvinceCenterDto;
import cn.com.cslc.aw.province.service.BProvinceCenterService;
import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionCondition;
import cn.com.cslc.aw.saleperiod.dto.C_SalePeriodDto;
import cn.com.cslc.aw.saleperiod.service.CSalePeriodService;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.util.*;


@Controller
@RequestMapping("/reports/shopCommissionReport")
public class ShopCommissionReportController extends JasperReportsBaseController{
	
	private String indexViewName = "redirect:/reports/shopCommissionReportIndex";
	

	@Autowired
	private AgentService agentService;

	@Autowired
	private CSalePeriodService cSalePeriodService;

	@Autowired
	private OrgService orgService;

	@ModelAttribute("cSalePeriodList")
	public List<C_SalePeriodDto> populateCSalePeriod(@SessionAttribute Set<String> userProvinceCodeSet) {
		return cSalePeriodService.querySalePeriodFromC_Sale_Period(userProvinceCodeSet);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showReportIndex(){
		return "reports/shopCommissionReportIndex";
	}
	
	@RequestMapping(value = "/exportFile/*", method = RequestMethod.GET)
	public String exportFile(@JRRequestParams JRRequest  jrRequest, ModelMap modelMap, @SessionAttribute SysUser currentUser,@SessionAttribute Set<String> userAgentCodeSet, @SessionAttribute Set<String> userProvinceCodeSet) {
		Map<String,String> requestParamsMap = jrRequest.getReportParamMap();
		QueryShopCommissionCondition queryCondition = new QueryShopCommissionCondition();
		modelMap.put("beginSalePeriodNO", requestParamsMap.get("beginSalePeriodNO"));
		modelMap.put("endSalePeriodNO", requestParamsMap.get("endSalePeriodNO"));
		modelMap.put("shopNo", "%" + requestParamsMap.get("shopNo") + "%");
		modelMap.put("techSystemIdSet", new HashSet<String>(Arrays.asList(requestParamsMap.get("techSystemId").split(","))));

		if(StringUtils.isEmpty(requestParamsMap.get("orgCode"))){
			queryCondition.getAgentCodeSet().addAll(userAgentCodeSet);
		}else{
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(requestParamsMap.get("orgCode")));
		}
		if(queryCondition.getAgentCodeSet().isEmpty()){
			queryCondition.getAgentCodeSet().add("#");//机构未授权代理时，传jsaper时，赋值#，等效于ag.agent_no is null
		}
		modelMap.put("agentCodeSet", queryCondition.getAgentCodeSet());



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
		modelMap.put("provinceIdSet", queryCondition.getProvinceIdSet());

		if(StringUtils.isEmpty(requestParamsMap.get("orgCode"))){
			modelMap.put("belongOrgName", "全部");
		}else{
			modelMap.put("belongOrgName", orgService.findOrgByCode(requestParamsMap.get("orgCode")).getName());
		}

		if("-40,0,10,20,30,40,50,51,21,53".equals(requestParamsMap.get("techSystemId"))){
			modelMap.put("techSystemName", "全部");
		}else if("40".equals(requestParamsMap.get("techSystemId"))){
			modelMap.put("techSystemName", "竞彩");
		}else if("-40,0,10,20,30,50,51,21,53".equals(requestParamsMap.get("techSystemId"))){
			modelMap.put("techSystemName", "不含竞彩");
		}
		return getReportView(jrRequest, modelMap);
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;
	}
}
