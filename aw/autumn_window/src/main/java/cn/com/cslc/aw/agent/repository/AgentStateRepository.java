package cn.com.cslc.aw.agent.repository;

import cn.com.cslc.aw.agent.domain.SysAgentState;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface AgentStateRepository extends BaseRepository<SysAgentState>{
	SysAgentState findByCode(String code);
}
