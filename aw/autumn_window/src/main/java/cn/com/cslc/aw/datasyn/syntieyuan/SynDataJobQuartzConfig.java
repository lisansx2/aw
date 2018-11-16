package cn.com.cslc.aw.datasyn.syntieyuan;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import cn.com.cslc.aw.core.common.quartz.BaseQuartzConfig;

//@AwQuartzConfiguration
public class SynDataJobQuartzConfig extends BaseQuartzConfig{
	     
	@Bean("synDataJobDetail")
    public JobDetailFactoryBean synDataJobDetail() {
        return createJobDetail(SynDataJob.class);
    }
    
    @Bean("synDataJobDetailCronTrigger")
    public CronTriggerFactoryBean synDataJobCronTrigger(@Qualifier("synDataJobDetail") JobDetail jobDetail, @Value("${job.syndata.cronExp}") String cronExpression) throws ParseException {
    	CronTriggerFactoryBean factoryBean = createCronTrigger(jobDetail, cronExpression);
    	return factoryBean;
    }
}
