package cn.com.cslc.aw.core.common.security.sso;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cslc.ump.ws.ldap.credential.Attribute;
import com.cslc.ump.ws.ldap.credential.Credential;
import com.cslc.ump.ws.ldap.credential.CredentialResponse;
import com.cslc.ump.ws.ldap.credential.CredentialVerifyService;

@Component
public class RemoteTokenVerifyServiceImpl {

	private static final int MAX_ACTIVE = 50;
	private static final long MAX_WAITE = 100L;
	
	private ObjectPool<CredentialVerifyService> objectPool;
	
	private static Logger LOG = LoggerFactory.getLogger(RemoteTokenVerifyServiceImpl.class);
	
	@Autowired
	private CredentialServiceObjectFactory credentialServiceObjectFactory;
		
	@PostConstruct
	private void init(){
		objectPool = new GenericObjectPool<CredentialVerifyService>(credentialServiceObjectFactory, MAX_ACTIVE, GenericObjectPool.WHEN_EXHAUSTED_BLOCK,
				MAX_WAITE);
	}
	
	/**
	 * <p>单点登录，首次请求验证用户合法性</p>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 * @return List<Attribute>
	 * @see
	 */
	public List<Attribute> verifySSOuserInfoByToken(String ssoToken) throws Exception {
		if(objectPool == null) {
			LOG.info("=====获取对象池失败，重新创建对象池=====");
			objectPool = new GenericObjectPool<CredentialVerifyService>(credentialServiceObjectFactory, MAX_ACTIVE,
					GenericObjectPool.WHEN_EXHAUSTED_BLOCK, MAX_WAITE);
		}
		LOG.info("SSOTOKEN++++++ : " + ssoToken);
		long startTime = System.currentTimeMillis();
		CredentialVerifyService client = null;
		try {
			client = objectPool.borrowObject();
			long endTime = System.currentTimeMillis();
			LOG.info("BORROW CLIENT TIME=====\\=====\\====\\===:" + (endTime - startTime) + ":client:" + client);
			CredentialResponse cr = client.verify(ssoToken);
			long verifyTime = System.currentTimeMillis();
			LOG.info("OPERATE VERIFY TIME===//====//===//===:" + (verifyTime - endTime) + ":结果" + cr);
			Credential credential = cr.getResponse();
			LOG.info("credential ===%%====%%===%%===: " + credential);
			if(credential == null) {
				String errorMsg = "LDAP Webservice 内部错误，返回结果为NULL，请检查LDAP Webservice 服务器是否正常！";
				LOG.error(errorMsg);
				throw new Exception(errorMsg);
			}
			List<Attribute> list = credential.getUserData().getAttribute();
			LOG.info("RETURN DATA ======= " + list);
			return list;
		} catch(Exception e) {
			LOG.error(e.getMessage());
			throw e;
		} finally {
			if(client != null) {
				objectPool.returnObject(client);
			}
		}
	}
}
