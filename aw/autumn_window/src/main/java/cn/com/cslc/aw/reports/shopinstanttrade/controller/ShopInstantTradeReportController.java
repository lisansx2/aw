package cn.com.cslc.aw.reports.shopinstanttrade.controller;

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

import cn.com.cslc.aw.core.common.controller.JasperReportsBaseController;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.jasperreports.JRRequest;
import cn.com.cslc.aw.core.common.jasperreports.JRRequestParams;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.customer.service.CustomerService;
import cn.com.cslc.aw.game.dto.SysGameProjection;
import cn.com.cslc.aw.game.service.GameService;
import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeCondition;


@Controller
@RequestMapping("/reports/shopInstantTradeReport")
public class ShopInstantTradeReportController extends JasperReportsBaseController{
	
	private String indexViewName = "redirect:/reports/shopInstantTradeReport";
		
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private GameService gameService; 
	
	@Autowired
	private OrgService orgService;
		
    @ModelAttribute("allInstantGame")
    public List<SysGameProjection> populateGame(@SessionAttribute Set<String> userProvinceCodeSet) {
    	return gameService.findInstantGameByByProvinceNos(userProvinceCodeSet);
    }
    
	@RequestMapping(method=RequestMethod.GET)
	public String showReportIndex(){
		return "reports/shopInstantTradeReportIndex";
	}
	
	@RequestMapping(value = "/exportFile/*", method = RequestMethod.GET)
	public String exportFile(@JRRequestParams JRRequest  jrRequest, ModelMap modelMap, @SessionAttribute SysUser currentUser,@SessionAttribute Set<String> userCustomerCodeSet,@SessionAttribute Set<String> userProvinceCodeSet) throws ParseException {
		Map<String,String> requestParamsMap = jrRequest.getReportParamMap();
		QueryShopInstantTradeCondition queryCondition = new QueryShopInstantTradeCondition();
		queryCondition.setEndDate(requestParamsMap.get("endDate"));
		queryCondition.setStartDate(requestParamsMap.get("startDate"));
		queryCondition.setShopNo(requestParamsMap.get("shopNo"));
		queryCondition.setGameNo(requestParamsMap.get("gameNo"));
		queryCondition.setUserOrg(requestParamsMap.get("userOrg"));
		queryCondition.setCustomerCodeSet(userCustomerCodeSet);
		
		Set<String> gameNoSet = Sets.newHashSet();
		if(StringUtils.isEmpty(queryCondition.getGameNo())){
			for(SysGameProjection game : gameService.findInstantGameByByProvinceNos(userProvinceCodeSet)){
				gameNoSet.add(game.getGameNo());
			}
			modelMap.put("gameName", "全部");
		}else{
			gameNoSet.add(queryCondition.getGameNo());
			modelMap.put("gameName", gameService.getSysGameByGameNo(queryCondition.getGameNo()).getGameName());
		}
		queryCondition.setGameNoSet(gameNoSet);
		
		modelMap.put("startDate", queryCondition.getStartDate() );
		modelMap.put("endDate", queryCondition.getEndDate());
		
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			modelMap.put("belongOrgName", "全部");
		}else{
			modelMap.put("belongOrgName", orgService.findOrgByCode(queryCondition.getOrgCode()).getName());
		}
		
		if(StringUtils.isEmpty(queryCondition.getShopNo())){
			modelMap.put("shopCode", "全部");
		}else{
			modelMap.put("shopCode", queryCondition.getShopNo());
		}
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.setCustomerCodeSet(userCustomerCodeSet);
		}else{
			queryCondition.getCustomerCodeSet().addAll(customerService.findCustomerCodesByOrgCode(queryCondition.getOrgCode()));
		}
		modelMap.put("customerCodeSet", queryCondition.getCustomerCodeSet());
		modelMap.put("gameCodeSet", queryCondition.getGameNoSet());
		
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
		return indexViewName;
	}
}
