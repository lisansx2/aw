package cn.com.cslc.aw.core.common.security.sso;

public interface ParseUserIdentifierFromTokenService {
	String getUserIdentifier(String tokenStr);
}
