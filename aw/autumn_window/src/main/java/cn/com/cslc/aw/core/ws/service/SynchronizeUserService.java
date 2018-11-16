package cn.com.cslc.aw.core.ws.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cslc.aw.core.common.constant.SysUserStateConstant;
import cn.com.cslc.aw.core.common.domain.SysCity;
import cn.com.cslc.aw.core.common.domain.SysCredentialType;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.domain.SysUserState;
import cn.com.cslc.aw.core.common.domain.SysWsMessage;
import cn.com.cslc.aw.core.common.exception.BusinessLogicException;
import cn.com.cslc.aw.core.common.service.BaseService;
import cn.com.cslc.aw.core.common.utils.DateUtils;
import cn.com.cslc.aw.core.common.utils.JaxbUtil;
import cn.com.cslc.aw.core.user.service.UserService;
import cn.com.cslc.aw.core.ws.config.WebServiceSettings;
import cn.com.cslc.aw.core.ws.model.generate.ResponseHead;
import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserResponse;

@Transactional(readOnly = true)
@Service
public class SynchronizeUserService {

	private static final Logger LOG = LoggerFactory.getLogger(SynchronizeUserService.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private WsMessageService wsMessageService;

	@Autowired
	private WebServiceSettings webServiceSettings;
	/**
	 * 1、如果是重试请求，则查询上一次结果返回；否则下一步 
	 * 2、保存请求消息
	 * 3、同步用户信息
	 * 4、保存应答消息
	 * 
	 * @param request
	 * @return
	 */
	@Transactional
	public SynchronizeUserResponse synchronizeUser(SynchronizeUserRequest request) {

		String serialNumber = request.getHead().getSerialNumber();
		if (wsMessageService.isRetryReq(serialNumber, webServiceSettings.getUmpSourceName())) {//处理重试请求
			LOG.info("重试请求，请求序列号：" + request.getHead().getSerialNumber());
			String resMsgXml = wsMessageService.getRetryResMsg(serialNumber, webServiceSettings.getUmpSourceName());
			LOG.info("响应消息：" + resMsgXml);
			return JaxbUtil.converyToJavaBean(resMsgXml, SynchronizeUserResponse.class);
		} else {
			//保存请求消息
			SysWsMessage wsMsg = new SysWsMessage();
			wsMsg.setSource(webServiceSettings.getUmpSourceName());
			wsMsg.setSerialNumber(request.getHead().getSerialNumber());
			String reqMsgXml = JaxbUtil.convertToXml(request);
			LOG.info("请求消息：" + reqMsgXml);
			wsMsg.setRequestMessage(reqMsgXml); 
			wsMsg.setRequestTimestamp(DateUtils.xmlDate2Date(request.getHead().getTimestamp()));
			wsMessageService.saveMsg(wsMsg);
			//同步用户操作
			SysUser sysUser = this.createSysUserByRequest(request);
			userService.SynchronizeUser(sysUser);
			SynchronizeUserResponse response = new SynchronizeUserResponse();
			Date responseTimestamp = new Date();
			ResponseHead resHead = new ResponseHead();
			resHead.setSerialNumber(request.getHead().getSerialNumber());
			resHead.setTimestamp(DateUtils.dateToXmlDate(responseTimestamp));
			resHead.setReturnCode("0");
			resHead.setReturnMessage("成功");
			response.setHead(resHead);
			//保存响应消息
			String resMsgXml = JaxbUtil.convertToXml(response);
			LOG.info("响应消息：" + resMsgXml);
			wsMsg.setResponseMessage(resMsgXml);
			wsMsg.setResponseTimestamp(responseTimestamp);
			wsMessageService.saveMsg(wsMsg);
			return response;	
		}
	}

	private SysUser createSysUserByRequest(SynchronizeUserRequest request){
		SysUser sysUser = new SysUser();
		sysUser.setUserName(request.getUserName());
		sysUser.setUserFullName(request.getUserFullName());
		sysUser.setMobilePhoneNumber(request.getMobilePhoneNumber());
		Long umpUserId = Long.parseLong(request.getUmpUserId());
		
		Date operateDate = DateUtils.xmlDate2Date(request.getOperateDate());
		if(userService.findByUmpUserId(umpUserId)== null){
			sysUser.setCreateDate(operateDate);
		}
		
		sysUser.setLastOperDate(DateUtils.xmlDate2Date(request.getOperateDate()));
		sysUser.setUmpUserId(Long.parseLong(request.getUmpUserId()));
		
		String credentialTypeCode = this.convertCredentialTypeCode2aw(request.getCredentialTypeCode());
		SysCredentialType sysCredentialType = baseService.getSysCredentialTypeByCode(credentialTypeCode);
		sysUser.setSysCredentialType(sysCredentialType);
		sysUser.setCredentialNo(request.getCredentialNo());
		
		String provinceNo = request.getProvinceNo();
		if(provinceNo != null){
			SysProvince sysProvince = baseService.getSysProvinceByNo(provinceNo);
			if(sysProvince == null){
				throw new BusinessLogicException("未找到ProvinceNo=" + provinceNo  +"对应的省");
			}
			sysUser.setSysProvince(sysProvince);
		}

		
		String cityNo = request.getCityNo();
		if(cityNo != null){
			SysCity sysCity = baseService.getSysCityByNo(cityNo);
			if(sysCity == null){
				throw new BusinessLogicException("未找到CityNo=" + cityNo  +"对应的市");
			}
			sysUser.setSysCity(sysCity);
		}
		
		String isEnable = request.getIsEnable();
		SysUserState sysUserState = null;
		
		String isDeleted = request.getIsDeleted();
		if(isDeleted.equals("0")){//未删除
			if(isEnable.equals("0")){//已停用
				sysUserState = baseService.getUserState(SysUserStateConstant.Disable);
			}else if(isEnable.equals("10")){//已启用
				sysUserState = baseService.getUserState(SysUserStateConstant.Enabled);
			}else{
				throw new BusinessLogicException("IsEnable=" + isEnable  +"错误");
			}
		}else if(isDeleted.equals("1")){//已删除
			sysUserState = baseService.getUserState(SysUserStateConstant.Deleted);
		}else{
			throw new BusinessLogicException("IisDeleted=" + isDeleted  +"错误");
		}
		sysUser.setSysUserState(sysUserState);
		return sysUser;
	}
	
	private String convertCredentialTypeCode2aw(String umpCredentialTypeCode){
		String awCredentialTypeCode = webServiceSettings.getCredentialTypeCodeMap().get(umpCredentialTypeCode);
		if(awCredentialTypeCode == null){
			awCredentialTypeCode = webServiceSettings.getAwDefaultCredentialTypeCode();
		}
		return awCredentialTypeCode;
		
	}

	public WebServiceSettings getWebServiceSettings() {
		return webServiceSettings;
	}

	public void setWebServiceSettings(WebServiceSettings webServiceSettings) {
		this.webServiceSettings = webServiceSettings;
	}
	
}
