package cn.com.cslc.aw.reports.shopinstantpromotion.controller;

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
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.InstantPromotion;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionCondition;
import cn.com.cslc.aw.reports.shopinstantpromotion.service.ShopInstantPromotionReportService;
import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeCondition;

@Controller
@RequestMapping("/reports/shopInstantPromotionReport")
public class ShopInstantPromotionReportController extends JasperReportsBaseController{

	private String indexViewName = "redirect:/reports/shopInstantPromotionReport";
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private ShopInstantPromotionReportService shopInstantPromotionReportService;  
	
	@ModelAttribute("allPromotion")
	    public List<InstantPromotion> populatePromotion(@SessionAttribute Set<String> userCustomerCodeSet) {
	    	return shopInstantPromotionReportService.findPromotionByCustomerCodes(userCustomerCodeSet);
	    }
	
	@RequestMapping(method=RequestMethod.GET)
	public String showReportIndex(){
		return "reports/shopInstantPromotionReportIndex";
	}
	
	
	@RequestMapping(value = "/exportFile/*", method = RequestMethod.GET)
	public String exportFile(@JRRequestParams JRRequest  jrRequest, ModelMap modelMap, @SessionAttribute SysUser currentUser,@SessionAttribute Set<String> userCustomerCodeSet,@SessionAttribute Set<String> userProvinceCodeSet) throws ParseException {
		Map<String,String> requestParamsMap = jrRequest.getReportParamMap();
		QueryShopInstantPromotionCondition queryCondition = new QueryShopInstantPromotionCondition();
		queryCondition.setEndDate(requestParamsMap.get("endDate"));
		queryCondition.setStartDate(requestParamsMap.get("startDate"));
		queryCondition.setShopNo(requestParamsMap.get("shopNo"));
		queryCondition.setCustomerCodeSet(userCustomerCodeSet);
		queryCondition.setUserOrg(requestParamsMap.get("userOrg"));
		queryCondition.setPromotionNo(requestParamsMap.get("promotionNo"));
		
		Set<String> promotionNoSet = Sets.newHashSet();
		if(StringUtils.isEmpty(queryCondition.getPromotionNo())){
			for(InstantPromotion promotion : shopInstantPromotionReportService.findPromotionByCustomerCodes(userCustomerCodeSet)){
				promotionNoSet.add(promotion.getPromotionNo());	
			}
			modelMap.put("belongPromotionName", "全部" );
		}else{
			promotionNoSet.add(queryCondition.getPromotionNo());
			modelMap.put("belongPromotionName", shopInstantPromotionReportService.findPromotionByPromotionCode(queryCondition.getPromotionNo()).getPromotionName() );
		}
		queryCondition.setPromotionNoSet(promotionNoSet);
		
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
		modelMap.put("promotionCodeSet", queryCondition.getPromotionNoSet());
		
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
