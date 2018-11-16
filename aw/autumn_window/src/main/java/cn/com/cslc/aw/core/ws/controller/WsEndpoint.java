package cn.com.cslc.aw.core.ws.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cn.com.cslc.aw.core.common.utils.DateUtils;
import cn.com.cslc.aw.core.ws.model.generate.ResponseHead;
import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserResponse;
import cn.com.cslc.aw.core.ws.service.SynchronizeUserService;
import cn.com.cslc.aw.reports.periodsales.service.PeriodSalesReportService;

@Endpoint
public class WsEndpoint {
	private static final String NAMESPACE_URI = "http://www.cslc.com.cn/aw/ws";
	@Autowired
	private SynchronizeUserService synchronizeUserService;

	private static final Logger LOG = LoggerFactory.getLogger(WsEndpoint.class);
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SynchronizeUserRequest")
	@ResponsePayload
	public SynchronizeUserResponse synchronizeUser(@RequestPayload SynchronizeUserRequest request) {
		SynchronizeUserResponse response = null;
		ResponseHead reponseHead = new ResponseHead();
		try {
			response = synchronizeUserService.synchronizeUser(request);
		} catch (Exception e) {
			LOG.error("同步用户信息发生异常，原因：" + e.getMessage());
			response = new SynchronizeUserResponse();
			reponseHead.setReturnCode("-1");
			reponseHead.setReturnMessage("系统未知异常");
			reponseHead.setSerialNumber(request.getHead().getSerialNumber());
			reponseHead.setTimestamp(DateUtils.dateToXmlDate(new Date()));
			response.setHead(reponseHead);
		} 
		return response;
	}
}