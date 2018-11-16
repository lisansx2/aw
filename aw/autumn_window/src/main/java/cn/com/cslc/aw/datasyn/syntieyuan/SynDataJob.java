package cn.com.cslc.aw.datasyn.syntieyuan;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.cslc.aw.agent.service.AgentService;

public class SynDataJob implements Job {
    
	@Autowired
    private AgentService agentService;
    
    private static final Logger LOG = LoggerFactory.getLogger(SynDataJob.class);
    
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	LOG.info("开始执行同步行业渠道代理任务");
    	agentService.synAgentInfo();
    	LOG.info("结束执行同步行业渠道代理任务");
    }
}
