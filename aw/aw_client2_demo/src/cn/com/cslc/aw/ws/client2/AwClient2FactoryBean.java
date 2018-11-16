package cn.com.cslc.aw.ws.client2;

import java.io.IOException;
import java.security.KeyStore;
import java.security.Security;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;

public class AwClient2FactoryBean implements FactoryBean<AwClient2>{
	
	private String url;
	
    private String trustStorePath;
    
    private String trustStorePassword;
    
    private String awHostname;
    
	@Override
	public AwClient2 getObject() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		AwClient2 client = new AwClient2();
        client.setDefaultUri(this.url);
        
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cn.com.cslc.aw.ws.client2.model.generate");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        KeyStore ts = KeyStore.getInstance("JKS");
        Resource trustStore = new ClassPathResource(this.getTrustStorePath());
        ts.load(trustStore.getInputStream(), this.getTrustStorePassword().toCharArray());

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
	            System.out.println("AW主机名认证失败!");
	            return false;
			}
        });
        client.setMessageSender(messageSender);
        return client;
    }

	@Override
	public Class<?> getObjectType() {
		return AwClient2.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTrustStorePath() {
		if(trustStorePath == null){
			trustStorePath = System.getProperty("javax.net.ssl.trustStore");
		}
		return trustStorePath;
	}

	public void setTrustStorePath(String trustStorePath) {
		this.trustStorePath = trustStorePath;
	}

	public String getTrustStorePassword() {
		if(trustStorePassword == null){
			trustStorePassword = System.getProperty("javax.net.ssl.trustStorePassword");
		}
		return trustStorePassword;
	}

	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public String getAwHostname() {
		return awHostname;
	}

	public void setAwHostname(String awHostname) {
		this.awHostname = awHostname;
	}
}
