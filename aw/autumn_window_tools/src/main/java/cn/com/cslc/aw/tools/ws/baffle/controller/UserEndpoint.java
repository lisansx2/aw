package cn.com.cslc.aw.tools.ws.baffle.controller;

import java.util.Date;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cn.com.cslc.aw.tools.core.common.utils.DateUtils;
import cn.com.cslc.aw.tools.ws.common.model.generate.RequestHead;
import cn.com.cslc.aw.tools.ws.common.model.generate.ResponseHead;
import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserResponse;


@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://www.cslc.com.cn/aw/ws";
  
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SynchronizeUserRequest")
    @ResponsePayload
    public SynchronizeUserResponse synchronizeUser(@RequestPayload SynchronizeUserRequest request) {
    	SynchronizeUserResponse response = new SynchronizeUserResponse();
    	response.setHead(this.getSucessResponseHead(request.getHead()));
        return response;
    }
    
    private ResponseHead getSucessResponseHead(RequestHead requestHead){
  	  ResponseHead reponseHead = new ResponseHead();
  	  reponseHead.setReturnCode("0");
  	  reponseHead.setReturnMessage("成功");
  	  reponseHead.setSerialNumber(requestHead.getSerialNumber());
  	  reponseHead.setTimestamp(DateUtils.dateToXmlDate(new Date()));
  	  return reponseHead;
  }
}