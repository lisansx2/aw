package cn.com.cslc.aw.ws.client;


import java.io.File;
import java.io.FileNotFoundException;
import java.security.Security;
import java.util.Date;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ResourceUtils;

import cn.com.cslc.aw.ws.client.generate.AwPort;
import cn.com.cslc.aw.ws.client.generate.RequestHead;
import cn.com.cslc.aw.ws.client.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.ws.client.generate.SynchronizeUserResponse;

  
public class AwWsClient {  
	private static Logger logger = Logger.getLogger(AwWsClient.class);
    public static void main(String[] args) {

    	File caLibFile = null;
    	try {
    		caLibFile = ResourceUtils.getFile("classpath:certs/CaLib_client.jks");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	System.setProperty("javax.net.ssl.trustStore", caLibFile.getAbsolutePath()); 
    	System.setProperty("javax.net.ssl.trustStorePassword", "111111"); 
    	Security.addProvider(new BouncyCastleProvider());
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
                new String[] { "cxf/aw-ws-client-beans.xml" });  
        AwPort client = (AwPort) context.getBean("awClient");
        
        RequestHead requestHead = new RequestHead();
        requestHead.setSerialNumber("1111111128");
        requestHead.setTimestamp(DateUtils.dateToXmlDate(new Date()));
        
        SynchronizeUserRequest request = new SynchronizeUserRequest();
        request.setHead(requestHead);
        request.setCityNo("3701");
        request.setCredentialNo("150202198201010000");
        request.setCredentialTypeCode("10");
        request.setIsDeleted("1");
        request.setIsEnable("0");
        request.setMobilePhoneNumber("13800000000");
        request.setOperateDate(DateUtils.dateToXmlDate(new Date()));
        request.setProvinceNo("37");
        request.setUmpUserId("9999");
        request.setUserFullName("测试UMP同步用户");
        request.setUserName("admin2");
        request.setAgentNo("370001");
        
        try{
        	SynchronizeUserResponse response = client.synchronizeUser(request);
        	logger.info("响应返回码：" + response.getHead().getReturnCode());
        }catch(Exception e){
        	logger.error(e);
        }
        System.exit(0);  
    }  
  
}  