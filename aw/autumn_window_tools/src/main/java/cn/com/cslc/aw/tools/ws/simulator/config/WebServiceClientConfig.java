package cn.com.cslc.aw.tools.ws.simulator.config;

import java.io.IOException;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;

import cn.com.cslc.aw.tools.ws.simulator.client.AwClient;


@Configuration
public class WebServiceClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceClientConfig.class);

    @Value("${ump-simulator.endpoint-url}")
    private String url;
    
    @Value("${ump-simulator.host-name}")
    private String awHostname;

    @Value("${ump-simulator.trust-store}")
    private Resource trustStore;

    @Value("${ump-simulator.trust-store-password}")
    private String trustStorePassword;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cn.com.cslc.aw.tools.ws.common.model.generate");
        return marshaller;
    }

    @Bean
    public AwClient getAwClient(Jaxb2Marshaller marshaller) throws Exception {
    	AwClient client = new AwClient();
        client.setDefaultUri(this.url);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(trustStore.getInputStream(), trustStorePassword.toCharArray());

        try {
            trustStore.getInputStream().close();
        } catch (IOException e) {
        }
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(ts);

        HttpsUrlConnectionMessageSender messageSender = new HttpsUrlConnectionMessageSender();
        messageSender.setTrustManagers(trustManagerFactory.getTrustManagers());

        messageSender.setHostnameVerifier(new HostnameVerifier(){
			@Override
			public boolean verify(String hostname, SSLSession session) {

	            if (hostname.equals(awHostname)) {
	                return true;
	            }
	            LOGGER.error("AW主机名认证失败!");
	            return false;
			}
        });
        client.setMessageSender(messageSender);
        return client;
    }

}
