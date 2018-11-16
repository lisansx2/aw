import java.io.IOException;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.cslc.aw.ws.client2.AwClient2;
import cn.com.cslc.aw.ws.client2.DateUtils;
import cn.com.cslc.aw.ws.client2.model.generate.RequestHead;
import cn.com.cslc.aw.ws.client2.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.ws.client2.model.generate.SynchronizeUserResponse;


public class AwClient2Test {

	public static void main(String[] args) throws IOException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"aw-ws-client-beans.xml");
		AwClient2 awClient2 = (AwClient2) applicationContext
				.getBean("awClient2");
		
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
        
		SynchronizeUserResponse response = awClient2.send(request);
		
		System.out.println("响应消息返回码：" + response.getHead().getReturnCode());
	}
}
