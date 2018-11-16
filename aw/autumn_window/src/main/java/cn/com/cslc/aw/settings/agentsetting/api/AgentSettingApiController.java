package cn.com.cslc.aw.settings.agentsetting.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.settings.agentsetting.dto.QueryAgentSettingResult;

@RestController
@RequestMapping("/api/settings/agentSetting")
public class AgentSettingApiController extends BaseApiController{
	
	@Autowired
	private AgentService agentService;
	
	@RequestMapping(value = "/queryAgentSetted", method = POST)
	public DataTablesResponse<List<QueryAgentSettingResult>> queryUsers(@DataTablesRequestParams DataTablesRequest dataTablesRequest, @SessionAttribute Set<String> userProvinceCodeSet) {
		Page<QueryAgentSettingResult> page = agentService.queryAgentListByProvinceCodes(userProvinceCodeSet, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest, page);
	}

}