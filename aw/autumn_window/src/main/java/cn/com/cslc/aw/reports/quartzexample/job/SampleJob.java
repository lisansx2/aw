package cn.com.cslc.aw.reports.quartzexample.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.cslc.aw.reports.quartzexample.service.SampleService;

public class SampleJob implements Job {
    @Autowired
    private SampleService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	service.hello();
    }
}
