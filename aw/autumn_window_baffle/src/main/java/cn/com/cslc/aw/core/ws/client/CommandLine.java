package cn.com.cslc.aw.core.ws.client;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;

import cn.com.cslc.aw.core.ws.model.generate.RequestHead;
import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserResponse;
import cn.com.cslc.aw.core.ws.utils.DateUtils;

/**
 * java方式启动需要把@Component打开
 * @author tianlu
 *
 */
//@Component
public class CommandLine implements CommandLineRunner{

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Override
    public void run(String... strings) throws Exception {

        String URI = "http://localhost:9080/aw_baffle/ws";
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        RequestHead requestHead = new RequestHead();
        requestHead.setSerialNumber("1111111111");
        requestHead.setTimestamp(DateUtils.dateToXmlDate(new Date()));
        
        SynchronizeUserRequest request = new SynchronizeUserRequest();
        request.setHead(requestHead);
        //request.setAgentNo("");
        request.setCityNo("000000");
        request.setCredentialNo("150202198201010000");
        request.setCredentialTypeCode("11");
        request.setIsDeleted("0");
        request.setIsEnable("0");
        request.setMobilePhoneNumber("11111111111");
        request.setOperateDate(DateUtils.dateToXmlDate(new Date()));
        request.setProvinceNo("11");
        request.setUmpUserId("1234");
        request.setUserFullName("123");
        request.setUserName("123");

        Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
        wss4jSecurityInterceptor.setSecurementActions("UsernameToken");
        wss4jSecurityInterceptor.setSecurementUsername("aw_ws_client");
        wss4jSecurityInterceptor.setSecurementPassword("Passw0rd");
        ClientInterceptor[] interceptors = new ClientInterceptor[] {wss4jSecurityInterceptor};
        webServiceTemplate.setInterceptors(interceptors);
        
        SynchronizeUserResponse response = (SynchronizeUserResponse) webServiceTemplate.marshalSendAndReceive(URI, request);
        System.out.println("SynchronizeUserResponse: " + response.getHead().getReturnCode());

    }
}