package cn.com.cslc.aw.reports.periodsales.job;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import cn.com.cslc.aw.core.common.quartz.AwQuartzConfiguration;
import cn.com.cslc.aw.core.common.quartz.BaseQuartzConfig;

@AwQuartzConfiguration
public class PeriodSalesReportJobQuartzConfig extends BaseQuartzConfig{
	     
	@Bean("periodSalesReportJobDetail")
    public JobDetailFactoryBean periodSalesReportJobDetail() {
        return createJobDetail(PeriodSalesReportJob.class);
    }
    
    @Bean("periodSalesReportJobDetailCronTrigger")
    public CronTriggerFactoryBean sampleJobCronTrigger(@Qualifier("periodSalesReportJobDetail") JobDetail jobDetail, @Value("${job.report.periodSales.cronExp}") String cronExpression) throws ParseException {
    	CronTriggerFactoryBean factoryBean = createCronTrigger(jobDetail, cronExpression);
    	return factoryBean;
    }
    
}
