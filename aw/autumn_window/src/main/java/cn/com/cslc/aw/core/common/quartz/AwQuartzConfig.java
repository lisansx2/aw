package cn.com.cslc.aw.core.common.quartz;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@AwQuartzConfiguration
public class AwQuartzConfig {

/*	@Autowired
	@Qualifier("sampleJobSimpleTrigger")
	private Trigger sampleJobSimpleTrigger;
	
	@Autowired
	@Qualifier("sampleJobCronTrigger")
	private Trigger sampleJobCronTrigger;*/
	
	
	@Autowired
	@Qualifier("periodSalesReportJobDetailCronTrigger")
	private Trigger periodSalesReportJobDetailCronTrigger;
		
	@Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
    
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in config file:
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        //factory.setTriggers(sampleJobSimpleTrigger, sampleJobCronTrigger);
        factory.setTriggers(periodSalesReportJobDetailCronTrigger);
        return factory;
    }
    
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
