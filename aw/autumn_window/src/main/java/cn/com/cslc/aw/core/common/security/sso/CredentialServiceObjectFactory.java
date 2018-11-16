package cn.com.cslc.aw.core.common.security.sso;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cslc.ump.ws.ldap.credential.CredentialVerifyService;

/**
 * 
 * <p>CredentialServiceObjectFactory</p>
 * 
 * <p>service</p>
 * 
 * <p>Copyright: 版权所有 (c) 2010 - 2012</p>
 * <p>Company: 中体彩</p>
 * 
 * @ClassName UmpAccessDecisionManager
 * @author zhanqiang
 * @version 1.0
 * 
 */
@Component
public class CredentialServiceObjectFactory extends BasePoolableObjectFactory<CredentialVerifyService> {
	@Autowired
	private SsoSettings ssoSettings;
	
	private final Object objMonitor = new Object();
	private static JaxWsProxyFactoryBean jaxWsProxyFactoryBean = null;

	
	@Override
	public CredentialVerifyService makeObject() throws Exception {
		return (CredentialVerifyService) this.getJaxWsProxyFactoryBean().create();
	}

	/**
	 * @return the jaxWsProxyFactoryBean
	 */
	public JaxWsProxyFactoryBean getJaxWsProxyFactoryBean() {
		if(jaxWsProxyFactoryBean == null) {
			synchronized(objMonitor) {
				if(jaxWsProxyFactoryBean == null) {
					jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
					jaxWsProxyFactoryBean.setAddress(ssoSettings.getAddress());
					jaxWsProxyFactoryBean.setUsername(ssoSettings.getUsername());
					jaxWsProxyFactoryBean.setPassword(ssoSettings.getPassword());
					jaxWsProxyFactoryBean.setServiceClass(CredentialVerifyService.class);
				}
			}
		}
		return jaxWsProxyFactoryBean;
	}

}
