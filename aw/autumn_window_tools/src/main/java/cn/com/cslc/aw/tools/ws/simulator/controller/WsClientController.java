package cn.com.cslc.aw.tools.ws.simulator.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.cslc.aw.tools.core.common.controller.BaseController;
import cn.com.cslc.aw.tools.core.common.exception.BaseApplicationException;
import cn.com.cslc.aw.tools.core.common.utils.DateUtils;
import cn.com.cslc.aw.tools.ws.common.model.generate.ResponseHead;
import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserResponse;
import cn.com.cslc.aw.tools.ws.simulator.service.WsClientService;


@Controller
@RequestMapping("/ump")
public class WsClientController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(WsClientController.class);
	
	@Autowired
	private WsClientService wsClientService;

	@RequestMapping(value="/simulator",method=RequestMethod.GET)
	public String showSimulator(SynchronizeUserRequest request, SynchronizeUserResponse response, Model model){
		return getIndexViewName();
	}
	
	@RequestMapping(value="/simulator",method=RequestMethod.POST)
	public String sendMsg(final SynchronizeUserRequest request, Model model){
		try{
			if(request.getCityNo().equals("")){
				request.setCityNo(null);
			}
			if(request.getAgentNo().equals("")){
				request.setAgentNo(null);
			}
			model.addAttribute(wsClientService.send(request));
			model.addAttribute("success", Boolean.TRUE);
			return getIndexViewName();
		}catch(BaseApplicationException e){
			SynchronizeUserResponse response = new SynchronizeUserResponse();
			ResponseHead responseHead = new ResponseHead();
			response.setHead(responseHead);
			model.addAttribute(response);
			e.setModel(model);
			throw e;
		}
	}

	@Override
	protected String getIndexViewName() {
		return "ump/simulator";
	}
	
}
