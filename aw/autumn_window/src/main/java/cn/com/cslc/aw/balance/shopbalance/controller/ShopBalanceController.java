package cn.com.cslc.aw.balance.shopbalance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.balance.agentbalance.controller.AgentBalanceController;
import cn.com.cslc.aw.balance.common.dto.AccountState;
import cn.com.cslc.aw.balance.common.service.BalanceBaseService;
import cn.com.cslc.aw.core.common.controller.BaseController;


@Controller
@RequestMapping("/balance/shopBalance")
public class ShopBalanceController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(AgentBalanceController.class);
	
	private String indexViewName = "redirect:/balance/shopBalanceIndex";
	
	@Autowired
	private BalanceBaseService balanceBaseService;
    
	@ModelAttribute("allAccountState")
    public List<AccountState> populateAccountState() {
        return balanceBaseService.getAllAccountState();
    }
	
/*	@ModelAttribute("belongAgentList")
    public List<SysAgent> populateBelongAgent(@SessionAttribute List<SysAgent> userAgentList) {
        return userAgentList;
    }*/
    
	@RequestMapping(method=RequestMethod.GET)
	public String showShopBalanceIndex(){
		return "balance/shopBalanceIndex";
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;  
	}
}
