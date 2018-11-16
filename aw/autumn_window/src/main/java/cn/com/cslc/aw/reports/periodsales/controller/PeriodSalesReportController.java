package cn.com.cslc.aw.reports.periodsales.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
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
import cn.com.cslc.aw.game.domain.SysGame;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.jasperreports.JRRequest;
import cn.com.cslc.aw.core.common.jasperreports.JRRequestParams;
import cn.com.cslc.aw.core.common.service.BaseService;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.game.dto.SysGameProjection;
import cn.com.cslc.aw.game.service.GameService;
import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesCondition;
import cn.com.cslc.aw.reports.periodsales.service.PeriodSalesReportService;


@Controller
@RequestMapping("/reports/periodSalesReport")
public class PeriodSalesReportController extends JasperReportsBaseController{
	
	private String indexViewName = "redirect:/reports/periodSalesReport";
	
	@Autowired
	private PeriodSalesReportService periodSalesReportService;
	
	@Autowired
	private BaseService baseService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private GameService gameService; 
	
    @ModelAttribute("allGame")
    public List<SysGameProjection> populateGame(@SessionAttribute Set<String> userProvinceCodeSet) {
    	return gameService.findDistinctNonInstantGameByProvinceNos(userProvinceCodeSet);
    }
    
	@RequestMapping(method=RequestMethod.GET)
	public String showReportIndex(){
		return "reports/periodSalesReportIndex";
	}
	
	@RequestMapping(value = "/exportFile/*", method = RequestMethod.GET)
	public String exportFile(@JRRequestParams JRRequest  jrRequest, ModelMap modelMap, @SessionAttribute SysUser currentUser,@SessionAttribute Set<String> userAgentCodeSet) throws ParseException {
		Map<String,String> requestParamsMap = jrRequest.getReportParamMap();
		QueryPeriodSalesCondition queryPeriodSalesCondition = new QueryPeriodSalesCondition();
		queryPeriodSalesCondition.setEndDate(requestParamsMap.get("endDate"));
		queryPeriodSalesCondition.setStartDate(requestParamsMap.get("startDate"));
		queryPeriodSalesCondition.setGameNo(requestParamsMap.get("gameNo"));
		queryPeriodSalesCondition.setUserOrg(requestParamsMap.get("userOrg"));
//		Set<String> gameNoSet = Sets.newHashSet();
//		if(StringUtils.isEmpty(queryPeriodSalesCondition.getGameNo())){
//			for(SysGame game : gameService.findNonInstantGames()){
//				gameNoSet.add(game.getGameNo());
//			}
//		}else{
//			gameNoSet.add(queryPeriodSalesCondition.getGameNo());
//		}
//		queryPeriodSalesCondition.setGameNoSet(gameNoSet);

		queryPeriodSalesCondition.setShopNo(requestParamsMap.get("shopNo"));
		//queryPeriodSalesCondition.setAgentCodeSet(userAgentCodeSet);
		
		//Date startDate = DateUtils.parseDate(queryPeriodSalesCondition.getStartDate() + " 00:00:00", "yyyy/MM/dd HH:mm:ss");
		modelMap.put("StartDate", queryPeriodSalesCondition.getStartDate() + " 00:00:00");
		//Date endDate = DateUtils.parseDate(queryPeriodSalesCondition.getEndDate() + " 23:59:59", "yyyy/MM/dd HH:mm:ss");
		modelMap.put("EndDate", queryPeriodSalesCondition.getEndDate() + " 23:59:59");
		
		if(StringUtils.isEmpty(queryPeriodSalesCondition.getOrgCode())){
			modelMap.put("belongOrgName", "全部");
		}else{
			modelMap.put("belongOrgName", orgService.findOrgByCode(queryPeriodSalesCondition.getOrgCode()).getName());
		}
		
		if(StringUtils.isEmpty(queryPeriodSalesCondition.getGameNo())){
			modelMap.put("gameName", "全部");
		}else{
			SysGame game = gameService.getSysGameByGameNo(queryPeriodSalesCondition.getGameNo());
			modelMap.put("gameName", game.getGameName());
		}
		if(StringUtils.isEmpty(queryPeriodSalesCondition.getGameNo())){
			queryPeriodSalesCondition.setGameNo("-1");
		}
		if(StringUtils.isEmpty(queryPeriodSalesCondition.getOrgCode())){
			queryPeriodSalesCondition.setAgentCodeSet(userAgentCodeSet);
		}else{
			queryPeriodSalesCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(queryPeriodSalesCondition.getOrgCode()));
		}
		if(StringUtils.isEmpty(queryPeriodSalesCondition.getShopNo())){
			modelMap.put("shopNo", "全部");
			modelMap.put("shopNoStr", "%%");
		}else{
			modelMap.put("shopNo", queryPeriodSalesCondition.getShopNo());
			modelMap.put("shopNoStr", "%" + queryPeriodSalesCondition.getShopNo() + "%");
		}
		
		modelMap.put("agentNoSet", queryPeriodSalesCondition.getAgentCodeSet());
	//	modelMap.put("gameNoSet", queryPeriodSalesCondition.getGameNoSet());
		modelMap.put("gameNo", queryPeriodSalesCondition.getGameNo());
		
		return getReportView(jrRequest, modelMap);
	}
	

	
	@Override
	protected String getIndexViewName() {
		return indexViewName;
	}
}
