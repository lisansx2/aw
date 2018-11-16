package cn.com.cslc.aw.reports.shoppayment.controller;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.bank.dto.B_BankDto;
import cn.com.cslc.aw.bank.service.BBankService;
import cn.com.cslc.aw.core.common.controller.JasperReportsBaseController;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.jasperreports.JRRequest;
import cn.com.cslc.aw.core.common.jasperreports.JRRequestParams;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.domain.dto.B_DomainDto;
import cn.com.cslc.aw.domain.service.BDomainService;
import cn.com.cslc.aw.province.dto.B_ProvinceCenterDto;
import cn.com.cslc.aw.province.service.BProvinceCenterService;
import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionCondition;
import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentCondition;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/reports/shopPaymentReport")
public class ShopPaymentReportController extends JasperReportsBaseController{
	
	private String indexViewName = "redirect:/reports/shopPaymentReportIndex";

	@Autowired
	private BDomainService domainService;

	@Autowired
	private BBankService bankService;

	@Autowired
	private BProvinceCenterService provinceCenterService;

	@Autowired
	private AgentService agentService;

	@Autowired
	private OrgService orgService;

	@ModelAttribute("dealTypeCodeList")
	public List<B_DomainDto> populateDealTypeCodeList() {
		List<B_DomainDto> allDealTypeCodeList = domainService.queryBDomain("DEAL_TYPE_CODE");
		List<B_DomainDto> filterDealTypeCodeList = new ArrayList<B_DomainDto>();
		if(allDealTypeCodeList != null && allDealTypeCodeList.size() > 0){
			for(B_DomainDto d : allDealTypeCodeList){
				if(d.getDomKey().intValue() != 30){
					filterDealTypeCodeList.add(d);
				}
			}
		}
		return filterDealTypeCodeList;
	}

	@ModelAttribute("bankList")
	public List<B_BankDto> populateBankList(@SessionAttribute Set<String> userProvinceCodeSet) {
		return bankService.queryBBank(userProvinceCodeSet);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showReportIndex(){
		return "reports/shopPaymentReportIndex";
	}
	
	@RequestMapping(value = "/exportFile/*", method = RequestMethod.GET)
	public String exportFile(@JRRequestParams JRRequest  jrRequest, ModelMap modelMap, @SessionAttribute SysUser currentUser, @SessionAttribute Set<String> userAgentCodeSet, @SessionAttribute Set<String> userProvinceCodeSet) {
		Map<String,String> requestParamsMap = jrRequest.getReportParamMap();
		QueryShopPaymentCondition queryCondition = new QueryShopPaymentCondition();
		modelMap.put("dealTypeCode", requestParamsMap.get("dealTypeCode"));
		modelMap.put("bDate", requestParamsMap.get("bDate") + " 00:00:00");
		modelMap.put("eDate", requestParamsMap.get("eDate") + " 23:59:59");
		modelMap.put("shopNo", "%" + requestParamsMap.get("shopNo") + "%");


		//get请求bankNo中文乱码，解决办法
		//1、页面端代码encodeURI(encodeURI(bankNo))
		String encodeBankNo = requestParamsMap.get("bankNo");
		try {
			modelMap.put("bankNo", URLDecoder.decode(encodeBankNo,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if(StringUtils.isEmpty(requestParamsMap.get("orgCode"))){
			queryCondition.getAgentCodeSet().addAll(userAgentCodeSet);
		}else{
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(requestParamsMap.get("orgCode")));
		}

		if(queryCondition.getAgentCodeSet().isEmpty()){
			queryCondition.getAgentCodeSet().add("#");// # 等效agent_no is null
		}
		modelMap.put("agentCodeSet", queryCondition.getAgentCodeSet());

		Set<String>  bankNoSet =  Sets.newHashSet();
		// 银行全选时，查询所有银行
		if(StringUtils.isEmpty(requestParamsMap.get("bankNo"))){
			List<B_BankDto> tmpBankList = bankService.queryBBank(userProvinceCodeSet);
			if(tmpBankList != null && tmpBankList.size() > 0){
				for(B_BankDto tmpBank : tmpBankList){
					bankNoSet.add(tmpBank.getBankNo());
				}
			}
		}else{
			try{
				bankNoSet.add(URLDecoder.decode(encodeBankNo,"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		modelMap.put("bankNoSet", bankNoSet);

		Set<BigDecimal>  dealTypeCodeSet =  Sets.newHashSet();
		//交易类型全选时，查询所有交易类型
		// 银行对账不能与其它交易类型一起查询
		if(StringUtils.isEmpty(requestParamsMap.get("dealTypeCode"))){
			List<B_DomainDto> allDealTypeCodeList = domainService.queryBDomain("DEAL_TYPE_CODE");
			StringBuffer tmpDealTypeCodes = new StringBuffer();
			if(allDealTypeCodeList != null && allDealTypeCodeList.size() > 0){
				for(B_DomainDto d : allDealTypeCodeList){
					dealTypeCodeSet.add(d.getDomKey());
				}
			}
		}else{
			dealTypeCodeSet.add(BigDecimal.valueOf(Long.parseLong(requestParamsMap.get("dealTypeCode"))));
		}
		modelMap.put("dealTypeCodeSet", dealTypeCodeSet);


		if(StringUtils.isEmpty(requestParamsMap.get("orgCode"))){
			modelMap.put("belongOrgName", "全部");
		}else{
			modelMap.put("belongOrgName", orgService.findOrgByCode(requestParamsMap.get("orgCode")).getName());
		}

		if(StringUtils.isEmpty(requestParamsMap.get("bankNo"))){
			modelMap.put("bankName", "全部");
		}else{
			modelMap.put("bankName", "");
			List<B_BankDto> tmpBankList = null;

			try{
				tmpBankList = bankService.queryBBank(userProvinceCodeSet,URLDecoder.decode(encodeBankNo,"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(tmpBankList.size()>0){
				modelMap.put("bankName", tmpBankList.get(0).getBankName());
			}
		}

		if(StringUtils.isEmpty(requestParamsMap.get("dealTypeCode"))){
			modelMap.put("dealTypeName", "全部");
		}else{
			modelMap.put("dealTypeName", "");
			B_DomainDto domain = domainService.queryBDomain("DEAL_TYPE_CODE", BigDecimal.valueOf(Long.parseLong(requestParamsMap.get("dealTypeCode"))));
			if(domain != null){
				modelMap.put("dealTypeName", domain.getDomValue());
			}
		}

		return getReportView(jrRequest, modelMap);
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;
	}
}
