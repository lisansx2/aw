package cn.com.cslc.aw.core.common.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.security.sso.ParseUserIdentifierFromTokenService;
import cn.com.cslc.aw.core.common.utils.CookiesUtil;
import cn.com.cslc.aw.core.user.service.UserService;

public class CustomPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter{
	
	private static Logger LOG = LoggerFactory.getLogger(CustomPreAuthenticatedProcessingFilter.class);
	
	private static final  String WEBSEAL_TOCKEN_KEY = "LtpaToken";
	
	private ParseUserIdentifierFromTokenService parseUserIdentifierFromTokenService;

	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		//从webseal转发的请求中获取token，并将token转换为username
		String webSealForwardTockenValue = CookiesUtil.getValue(request, WEBSEAL_TOCKEN_KEY);
		
		if(webSealForwardTockenValue != null){
			try{
				LOG.info("用户使用Ukey登录...（token="  + webSealForwardTockenValue + "）");
				String userId = parseUserIdentifierFromTokenService.getUserIdentifier(webSealForwardTockenValue);
				SysUser user = userService.findByUmpUserId(Long.parseLong(userId));
				if(user != null){
					return user.getUserName();
				}else{
					throw new UsernameNotFoundException("用户（userId=" + userId + "，token=" + webSealForwardTockenValue + "）未找到！");
				}
			}catch (Exception e){
				String msg = "";
				if(e instanceof UsernameNotFoundException ){
					msg = "无法找到Ukey登录的用户" + e.getMessage();
				}else{
					msg ="回调WebSeal失败！原因：" + e.getMessage();
				}
				LOG.error(msg);
				//throw new BaseSystemException(e);
			}
		}
		
		return null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		//不需要
		return "N/A";
	}

	public ParseUserIdentifierFromTokenService getParseUserIdentifierFromTokenService() {
		return parseUserIdentifierFromTokenService;
	}

	public void setParseUserIdentifierFromTokenService(
			ParseUserIdentifierFromTokenService parseUserIdentifierFromTokenService) {
		this.parseUserIdentifierFromTokenService = parseUserIdentifierFromTokenService;
	}
	
	
}
