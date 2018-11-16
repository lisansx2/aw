package cn.com.cslc.aw.reports.competitionprizecash.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.thymeleaf.util.StringUtils;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.controller.JasperReportsBaseController;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.jasperreports.JRRequest;
import cn.com.cslc.aw.core.common.jasperreports.JRRequestParams;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.reports.competitionprizecash.constant.RoundConstant;
import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashCondition;
import cn.com.cslc.aw.reports.competitionprizecash.dto.RoundCondition;
import cn.com.cslc.aw.reports.competitionprizecash.service.CompetitionPrizeCashReportService;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.InstantPromotion;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionCondition;


@Controller
@RequestMapping("/reports/competitionPrizeCashReport")
public class CompetitionPrizeCashReportController extends JasperReportsBaseController{

	private String indexViewName = "redirect:/reports/competitionPrizeCashReport";
	
	
	@Autowired
	private CompetitionPrizeCashReportService competitionPrizeCashReportService;  
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private OrgService orgService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String showReportIndex(){
		return "reports/competitionPrizeCashReportIndex";
	}
	
	@ModelAttribute("allRound")
    public List<RoundCondition> populatePromotion() {
    	return competitionPrizeCashReportService.findRoundCondition();
    }
	
	
	@RequestMapping(value = "/exportFile/*", method = RequestMethod.GET)
	public String exportFile(@JRRequestParams JRRequest  jrRequest, ModelMap modelMap, @SessionAttribute SysUser currentUser,@SessionAttribute Set<String> userAgentCodeSet,@SessionAttribute Set<String> userProvinceCodeSet) throws ParseException {
		Map<String,String> requestParamsMap = jrRequest.getReportParamMap();
		QueryCompetitionPrizeCashCondition queryCondition = new QueryCompetitionPrizeCashCondition();
		queryCondition.setEndDate(requestParamsMap.get("endDate"));
		queryCondition.setStartDate(requestParamsMap.get("startDate"));
		queryCondition.setShopNo(requestParamsMap.get("shopNo"));
		//queryCondition.setAgentCodeSet(userAgentCodeSet);
		queryCondition.setUserOrg(requestParamsMap.get("userOrg"));
		queryCondition.setRoundNo(requestParamsMap.get("roundNo"));
		
		if(StringUtils.isEmpty(queryCondition.getRoundNo())){
			queryCondition.setRoundNo(RoundConstant.ROUND_HALF_UP.getCode());
			modelMap.put("belongRoundName",RoundConstant.ROUND_HALF_UP.getName());
		}else{
			modelMap.put("belongRoundName", competitionPrizeCashReportService.findRoundConditionNameByCode(queryCondition.getRoundNo()));
		}
		modelMap.put("roundNo", queryCondition.getRoundNo());
		modelMap.put("startDate", queryCondition.getStartDate()+ " 00:00:00");
		modelMap.put("endDate", queryCondition.getEndDate()+ " 23:59:59");
		
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			modelMap.put("belongOrgName", "全部");
			queryCondition.setAgentCodeSet(userAgentCodeSet);
		}else{
			modelMap.put("belongOrgName", orgService.findOrgByCode(queryCondition.getOrgCode()).getName());
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(queryCondition.getOrgCode()));
		}
		
		if(StringUtils.isEmpty(queryCondition.getShopNo())){
			modelMap.put("shopCode", "全部");
		}else{
			modelMap.put("shopCode", queryCondition.getShopNo());
		}
		modelMap.put("agentCodeSet", queryCondition.getAgentCodeSet());
		
		String shopNo = queryCondition.getShopNo();
		if(StringUtils.isEmpty(shopNo)){
			modelMap.put("shopNoStr", "%%");
		}else{
			modelMap.put("shopNoStr", "%" + shopNo + "%");
		}

		return getReportView(jrRequest, modelMap);
	}
	
	
	@Override
	protected String getIndexViewName() {
		// TODO Auto-generated method stub
		return indexViewName;
	}

}
