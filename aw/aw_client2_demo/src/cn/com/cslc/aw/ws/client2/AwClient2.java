package cn.com.cslc.aw.ws.client2;

import java.io.IOException;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import cn.com.cslc.aw.ws.client2.model.generate.RequestHead;
import cn.com.cslc.aw.ws.client2.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.ws.client2.model.generate.SynchronizeUserResponse;

public class AwClient2 extends WebServiceGatewaySupport {

	public SynchronizeUserResponse send(SynchronizeUserRequest request) {
		SynchronizeUserResponse response = (SynchronizeUserResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request);
		return response;
	}

}