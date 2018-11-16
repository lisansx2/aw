package cn.com.cslc.aw.core.ws.config;

import java.util.List;
import java.util.Properties;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserRequest;
import cn.com.cslc.aw.core.ws.model.generate.SynchronizeUserResponse;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "aw")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AwPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://www.cslc.com.cn/aw/ws");
		wsdl11Definition.setSchema(usersSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema usersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsds/aw.xsd"));
	}

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(SynchronizeUserRequest.class, SynchronizeUserResponse.class);
		return jaxb2Marshaller;
	}
	
	@Bean
    public SimplePasswordValidationCallbackHandler securityCallbackHandler(){
        SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
        Properties users = new Properties();
        users.setProperty("aw_ws_client", "Passw0rd");
        callbackHandler.setUsers(users);
        
        return callbackHandler;
    }

	@Bean
    public Wss4jSecurityInterceptor securityInterceptor(){
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setValidationActions("UsernameToken");
        securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());
        return securityInterceptor;
    }
	
	@Bean
    public PayloadValidatingInterceptor validatingInterceptor(){
		PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
		validatingInterceptor.setXsdSchema(usersSchema());
		validatingInterceptor.setValidateRequest(true);
		validatingInterceptor.setValidateResponse(true);
        return validatingInterceptor;
    }

	
	@Bean
    public SoapEnvelopeLoggingInterceptor loggingInterceptor(){
		SoapEnvelopeLoggingInterceptor loggingInterceptor = new SoapEnvelopeLoggingInterceptor();
		loggingInterceptor.setLogRequest(true);
		loggingInterceptor.setLogResponse(true);
        return loggingInterceptor;
    }
	
    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
    	//enable username and password authentication
        //interceptors.add(securityInterceptor());
        interceptors.add(validatingInterceptor());
        interceptors.add(loggingInterceptor());
    }
}