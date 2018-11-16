package cn.com.cslc.aw.core.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cslc.aw.core.common.domain.SysWsMessage;
import cn.com.cslc.aw.core.common.utils.DateUtils;
import cn.com.cslc.aw.core.common.utils.JaxbUtil;
import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.core.ws.repository.WsMessageRepository;

@Transactional(readOnly = true)
@Service
public class WsMessageService{
	
	@Autowired
	private WsMessageRepository wsMessageRepository;
	
	@Transactional
	public void saveMsg(SysWsMessage reqMsg){
		wsMessageRepository.save(reqMsg);
	}
	
	/**
	 * 查询该重试请求的应答消息
	 * @param serialNo
	 * @return 
	 */
	public  String getRetryResMsg(String serialNumber,String source){
		SysWsMessage wsMessage = wsMessageRepository.findBySerialNumberAndSource(serialNumber, source);
		return wsMessage.getResponseMessage(); 
	}
	
	/**
	 * 是否为重试请求
	 * @param serialNumber
	 * @param source
	 * @return
	 */
	public  boolean isRetryReq(String serialNumber,String source){
		SysWsMessage wsMessage= wsMessageRepository.findBySerialNumberAndSource(serialNumber, source);
		if(wsMessage == null){
			return false;
		}else{
			return true;
		}
	}
}
