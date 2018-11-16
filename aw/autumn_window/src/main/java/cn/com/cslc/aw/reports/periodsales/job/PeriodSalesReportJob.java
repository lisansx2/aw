package cn.com.cslc.aw.reports.periodsales.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.cslc.aw.reports.periodsales.service.PeriodSalesReportService;

public class PeriodSalesReportJob implements Job {
    @Autowired
    private PeriodSalesReportService periodSalesReportService;
    private static final Logger LOG = LoggerFactory.getLogger(PeriodSalesReportJob.class);
    
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	LOG.info("开始执行时段销量报表任务");
    	periodSalesReportService.generateBaseReportData();
    	LOG.info("结束执行时段销量报表任务");
    }
}
