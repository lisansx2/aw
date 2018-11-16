package cn.com.cslc.aw.tools.ws.simulator.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserResponse;

public class AwClient extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(AwClient.class);

    public SynchronizeUserResponse synchronizeUser(SynchronizeUserRequest request) {
        SynchronizeUserResponse response = (SynchronizeUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        LOGGER.info("响应消息返回码：" + response.getHead().getReturnCode());
        return response;
    }
}
