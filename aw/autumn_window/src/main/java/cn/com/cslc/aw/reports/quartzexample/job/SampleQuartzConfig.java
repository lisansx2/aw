package cn.com.cslc.aw.reports.quartzexample.job;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import cn.com.cslc.aw.core.common.quartz.AwQuartzConfiguration;
import cn.com.cslc.aw.core.common.quartz.BaseQuartzConfig;

//@AwQuartzConfiguration
public class SampleQuartzConfig extends BaseQuartzConfig{
	
    @Value("${samplejob.frequency}")
    private long frequency;
    
	@Bean("sampleJobDetail")
    public JobDetailFactoryBean sampleJobDetail() {
        return createJobDetail(SampleJob.class);
    }

    @Bean("sampleJobSimpleTrigger")
    public SimpleTriggerFactoryBean sampleJobSimpleTrigger(@Qualifier("sampleJobDetail") JobDetail jobDetail) {
    	 SimpleTriggerFactoryBean factoryBean = createSimpleTrigger(jobDetail, frequency);
    	return factoryBean;
    }
    
	@Bean("sampleJobDetail2")
    public JobDetailFactoryBean sampleJobDetail2() {
        return createJobDetail(SampleJob2.class);
    }

    @Bean("sampleJobCronTrigger")
    public CronTriggerFactoryBean sampleJobCronTrigger(@Qualifier("sampleJobDetail2") JobDetail jobDetail, @Value("${samplejob.cronExp}") String cronExpression) throws ParseException {
    	CronTriggerFactoryBean factoryBean = createCronTrigger(jobDetail, cronExpression);
    	return factoryBean;
    }
    
}
