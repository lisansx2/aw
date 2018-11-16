package cn.com.cslc.aw.tools.ws.simulator.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import cn.com.cslc.aw.tools.core.common.exception.BaseApplicationException;
import cn.com.cslc.aw.tools.core.common.exception.BaseSystemException;
import cn.com.cslc.aw.tools.core.common.utils.DateUtils;
import cn.com.cslc.aw.tools.core.common.utils.PropertiesUtils;
import cn.com.cslc.aw.tools.ws.common.model.generate.RequestHead;
import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserResponse;
import cn.com.cslc.aw.tools.ws.simulator.client.AwClient;
import cn.com.cslc.aw.tools.ws.simulator.config.RequestTemplate;


@Service
public class WsClientService{
	
	private static final Logger LOG = LoggerFactory.getLogger(WsClientService.class);
	
	@Autowired
	private AwClient awClient;
	
	@Autowired
	private RequestTemplate requestTemplate;
	
	public SynchronizeUserResponse send(SynchronizeUserRequest request){
		try{
			return awClient.synchronizeUser(request);
		}catch(Exception e){
			LOG.error(e.getMessage());
			throw new BaseApplicationException("访问AW Webservice发生异常，原因：" + e.getMessage());
		}
	}
	
	public String generateSerialNo(){
		String newSerialNo= "";
		Resource fileRource = new ClassPathResource("serialNo.properties");
		String fileName;
		try {
			fileName = fileRource.getFile().getAbsolutePath();
		} catch (IOException e) {
			throw new BaseSystemException(e);
		}
		Properties serialNoProps = PropertiesUtils.loadProps(fileName);
		String currentSerialNo = (String) serialNoProps.get("currentSerialNo");
		Long nextSerialNo = Long.parseLong(currentSerialNo) + 1;
		for(int i= 0; i < 10-String.valueOf(nextSerialNo).length(); i++){
			newSerialNo+= "0";
		}
		newSerialNo += String.valueOf(nextSerialNo) ;
		PropertiesUtils.updateProperty(serialNoProps, fileName, "currentSerialNo", newSerialNo);
		return newSerialNo;
	}

	public SynchronizeUserRequest generateRequest() {
		SynchronizeUserRequest synchronizeUserRequest = new SynchronizeUserRequest();
		synchronizeUserRequest.setAgentNo(requestTemplate.getAgentNo());
		synchronizeUserRequest.setCityNo(requestTemplate.getCityNo());
		synchronizeUserRequest.setCredentialNo(requestTemplate.getCredentialNo());
		synchronizeUserRequest.setCredentialTypeCode(requestTemplate.getCredentialTypeCode());
		RequestHead requestHead = new RequestHead();
		requestHead.setSerialNumber(this.generateSerialNo());
		requestHead.setTimestamp(DateUtils.dateToXmlDate(new Date()));
		synchronizeUserRequest.setHead(requestHead);
		synchronizeUserRequest.setIsDeleted(requestTemplate.getIsDeleted());
		synchronizeUserRequest.setIsEnable(requestTemplate.getIsEnable());
		synchronizeUserRequest.setMobilePhoneNumber(requestTemplate.getMobilePhoneNumber());
		synchronizeUserRequest.setOperateDate(DateUtils.dateToXmlDate(new Date()));
		synchronizeUserRequest.setProvinceNo(requestTemplate.getProvinceNo());
		synchronizeUserRequest.setUmpUserId(requestTemplate.getUmpUserId());
		synchronizeUserRequest.setUserFullName(requestTemplate.getUserFullName());
		synchronizeUserRequest.setUserName(requestTemplate.getUserName());
		return synchronizeUserRequest;
	}
}
