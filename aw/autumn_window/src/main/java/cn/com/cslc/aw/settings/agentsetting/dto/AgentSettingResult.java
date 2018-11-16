package cn.com.cslc.aw.settings.agentsetting.dto;
import java.util.List;

import cn.com.cslc.aw.core.common.domain.SysProvince;

public class AgentSettingResult{

	private List<AgentSettingDefinition> agentDefList;
	
	private SysProvince sysProvince;

	public List<AgentSettingDefinition> getAgentSettingDefList() {
		return agentDefList;
	}

	public void setAgentSettingDefList(List<AgentSettingDefinition> agentDefList) {
		this.agentDefList = agentDefList;
	}

	public List<AgentSettingDefinition> getAgentDefList() {
		return agentDefList;
	}

	public void setAgentDefList(List<AgentSettingDefinition> agentDefList) {
		this.agentDefList = agentDefList;
	}

	public SysProvince getSysProvince() {
		return sysProvince;
	}

	public void setSysProvince(SysProvince sysProvince) {
		this.sysProvince = sysProvince;
	}
		
}
