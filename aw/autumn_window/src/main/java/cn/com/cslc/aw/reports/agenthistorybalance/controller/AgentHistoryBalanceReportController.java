package cn.com.cslc.aw.reports.agenthistorybalance.controller;

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
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.jasperreports.JRRequest;
import cn.com.cslc.aw.core.common.jasperreports.JRRequestParams;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceCondition;


@Controller
@RequestMapping("/reports/agentHistoryBalanceReport")
public class AgentHistoryBalanceReportController extends JasperReportsBaseController{
	
	private String indexViewName = "redirect:/reports/agentHistoryBalanceReportIndex";
	

	@Autowired
	private AgentService agentService;
	    
	@ModelAttribute("userAgentList")
    public List<SysAgent> populateUserAgent(@SessionAttribute List<SysAgent> userAgentList) {
        return userAgentList;
    }
	
	@RequestMapping(method=RequestMethod.GET)
	public String showReportIndex(){
		return "reports/agentHistoryBalanceReportIndex";
	}
	
	@RequestMapping(value = "/exportFile/*", method = RequestMethod.GET)
	public String exportFile(@JRRequestParams JRRequest  jrRequest, ModelMap modelMap, @SessionAttribute SysUser currentUser,@SessionAttribute Set<String> userAgentCodeSet) {
		Map<String,String> requestParamsMap = jrRequest.getReportParamMap();
		QueryAgentHistoryBalanceCondition queryCondition = new QueryAgentHistoryBalanceCondition();
		queryCondition.setEndDate(requestParamsMap.get("endDate"));
		queryCondition.setStartDate(requestParamsMap.get("startDate"));
		queryCondition.setAgentCode(requestParamsMap.get("agentCode"));
		queryCondition.setAgentCodeSet(userAgentCodeSet);
		
		String agentCode =queryCondition.getAgentCode();
		if(StringUtils.isEmpty(agentCode)){
			queryCondition.setProvinceIdSet(agentService.getProvinceIdByAgentNo(userAgentCodeSet));
			Set<String> privinceIdSet = queryCondition.getProvinceIdSet();
			modelMap.put("provinceIdSet",privinceIdSet);
			modelMap.put("agentNoSet",userAgentCodeSet);
			
		}else{
			Set<String> agentCodeSet = Sets.newHashSet();
			agentCodeSet.add(agentCode);
			modelMap.put("provinceIdSet",agentService.getProvinceIdByAgentNo(agentCodeSet));
			modelMap.put("agentCodeSet",agentCodeSet);
		}
		
		modelMap.put("startDate", queryCondition.getStartDate() + " 00:00:00");
		modelMap.put("endDate", queryCondition.getEndDate() + " 23:59:59");
		
		if(StringUtils.isEmpty(queryCondition.getAgentCode())){
			modelMap.put("seletAgentName", "全部");
		}else{
			SysAgent agent = agentService.getAgentByCode(queryCondition.getAgentCode());
			modelMap.put("seletAgentName", agent.getName());
		}
		return getReportView(jrRequest, modelMap);
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;
	}
}
