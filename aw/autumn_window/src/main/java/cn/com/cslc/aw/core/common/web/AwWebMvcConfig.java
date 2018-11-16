package cn.com.cslc.aw.core.common.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import cn.com.cslc.aw.core.common.interceptor.InterceptorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cn.com.cslc.aw.core.common.constant.JRConstant;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParamsArgumentResolver;
import cn.com.cslc.aw.core.common.jasperreports.JRRequestParamsArgumentResolver;
import cn.com.cslc.aw.core.config.AwSettings;


@Configuration
@Component
public class AwWebMvcConfig extends WebMvcConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(AwWebMvcConfig.class);
	@Autowired
	private  AwSettings awSettings;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(dataTablesRequestParamsArgumentResolver());
		argumentResolvers.add(jrRequestParamsArgumentResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		//注册自定义拦截器，添加拦截路径和排除拦截路径
		registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/api/**", "/reports/**", "/org/**", "/user/**", "/balance/**", "/settings/**", "/maintain/**");

	}

	@Bean
	public DataTablesRequestParamsArgumentResolver dataTablesRequestParamsArgumentResolver() {
		return new DataTablesRequestParamsArgumentResolver();
	}
	
	@Bean
	public JRRequestParamsArgumentResolver jrRequestParamsArgumentResolver() {
		return new JRRequestParamsArgumentResolver();
	}
	
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix(JRConstant.VIEW_PREFIX);
		resolver.setSuffix(JRConstant.VIEW_SUFFIX);
		resolver.setReportDataKey(JRConstant.REPORT_DATA_KEY);
		resolver.setViewNames(JRConstant.VIEW_NAMES);
		resolver.setViewClass(JasperReportsMultiFormatView.class);
		resolver.setOrder(0);
		return resolver;
	}
	
	 @Bean
	  public ObjectMapper ObjectMapper(){
	   ObjectMapper objectMapper=new ObjectMapper();
	   // 忽略json字符串中不识别的属性
	   objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	   // 忽略无法转换的对象 “No serializer found for class com.xxx.xxx”
	   objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
	   //objectMapper.setSerializationInclusion(Include.NON_EMPTY);  
	   objectMapper.setSerializationInclusion(Include.NON_NULL);  
	   DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	   objectMapper.setDateFormat(df);
	   return objectMapper;
	  }
	 
	 @Bean
	 public ServletContextInitializer initializer() {
	     return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.setAttribute("appVersion", awSettings.getAppVersion());
			}
	     };
	 }
}
