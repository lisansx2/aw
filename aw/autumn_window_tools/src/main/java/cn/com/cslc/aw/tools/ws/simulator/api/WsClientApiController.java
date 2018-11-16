package cn.com.cslc.aw.tools.ws.simulator.api;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.cslc.aw.tools.ws.common.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.tools.ws.simulator.service.WsClientService;

@RestController
@RequestMapping("/api/ws")
public class WsClientApiController{
	
	@Autowired
	private WsClientService wsClientService;
	
	@RequestMapping(value = "/generateSerialNo", method = POST)
	public Map<String, String> generateSerialNo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("newSerialNo", wsClientService.generateSerialNo());
		return map;
	}
	
	@RequestMapping(value = "/autoInput", method = POST)
	public SynchronizeUserRequest autoInput() {
		return wsClientService.generateRequest();
	}
}
