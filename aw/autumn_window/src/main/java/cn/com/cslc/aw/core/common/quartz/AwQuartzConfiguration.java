package cn.com.cslc.aw.core.common.quartz;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(name = "quartz.enabled")
@Configuration
//@Inherited
public @interface AwQuartzConfiguration{   
    public String name() default "";      
}  
  