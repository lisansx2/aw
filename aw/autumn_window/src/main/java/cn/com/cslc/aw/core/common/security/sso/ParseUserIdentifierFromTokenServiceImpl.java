package cn.com.cslc.aw.core.common.security.sso;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cslc.ump.ws.ldap.credential.Attribute;
import com.cslc.ump.ws.ldap.credential.AttributeValue;

@Component
public class ParseUserIdentifierFromTokenServiceImpl implements ParseUserIdentifierFromTokenService {

	@Autowired
	private RemoteTokenVerifyServiceImpl remoteTokenVerifyServiceImpl;

	private static Logger LOG = LoggerFactory.getLogger(ParseUserIdentifierFromTokenServiceImpl.class);

	@Override
	public String getUserIdentifier(String tokenStr) {
		try {
			List<Attribute> list = remoteTokenVerifyServiceImpl.verifySSOuserInfoByToken(tokenStr);
			Integer userId = null;
			String certificationNo = null;
			Integer provinceCenterId = null;

			for (int i = 0; i < list.size(); i++) {
				Attribute attr = list.get(i);
				List<AttributeValue> attrValueList = attr.getValue();
				if (attrValueList != null && attrValueList.size() > 0) {
					// 目录树上可能有多个值，但业务上不可能存在多个的情况，取第一个即可
					AttributeValue attrValue = attrValueList.get(0);
					Object attrValueData = attrValue.getData();
					if (attrValueData != null) {
						if (attr.getName().equalsIgnoreCase("UID")) {
							String userIdStr = attrValueData.toString();
							// 截掉前4位（业务管理员UID=50+省中心编号+业务管理员userId），其实可以直接从employee_id中获取
							userId = Integer.parseInt(userIdStr.substring(4, userIdStr.length()));
							LOG.info("从ssoToken中解析的userId = " + userId);
						}
						if (attr.getName().equalsIgnoreCase("CREDENTIALS_NO")) {
							certificationNo = attrValueData.toString();
						}
						if (attr.getName().equalsIgnoreCase("PROVINCE_CENTER_ID")) {
							provinceCenterId = Integer.parseInt(attrValueData.toString());
						}
					}
				}
			}

			if (userId == null || certificationNo == null || provinceCenterId == null) {
				throw new RuntimeException("返回SSO用户信息非法[userId=" + userId + ",provinceCenterId=" + provinceCenterId
						+ ",certificationNo=" + certificationNo + "]");
			}
			return userId.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
