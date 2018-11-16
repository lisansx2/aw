package cn.com.cslc.aw.core.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
//获取上下文
@Component
public class SpringContextAware implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SpringContextAware.class);
    private static ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextAware.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

}