package cn.com.cslc.aw.balance.agentbalance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.cslc.aw.core.common.controller.BaseController;


@Controller
@RequestMapping("/balance/agentBalance")
public class AgentBalanceController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(AgentBalanceController.class);
	
	private String indexViewName = "redirect:/balance/agentBalanceIndex";
	
	@RequestMapping(method=RequestMethod.GET)
	public String showAgentBalanceIndex(){
		return "balance/agentBalanceIndex";
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;  
	}
}
