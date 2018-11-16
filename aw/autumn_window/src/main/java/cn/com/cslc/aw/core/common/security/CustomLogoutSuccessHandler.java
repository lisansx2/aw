package cn.com.cslc.aw.core.common.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import cn.com.cslc.aw.core.common.security.sso.SsoSettings;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	@Value("${logout-url}")
	private String logoutSuccessUrl;
	
	@Autowired
	private SsoSettings ssoSettings;
	
	private static Logger LOG = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(authentication != null){
			SecurityUser user = (SecurityUser) authentication.getPrincipal();

			LOG.info("用户成功退出（用户名：" + user.getUsername() + "）");
			
			if(authentication instanceof PreAuthenticatedAuthenticationToken){//用过Ukey登录的用户
				super.setDefaultTargetUrl(ssoSettings.getLogoutUrl());
			}else{
				super.setDefaultTargetUrl(logoutSuccessUrl);
			}
		}

		super.onLogoutSuccess(request, response, authentication);
	}
	
	@PostConstruct
	private void init(){
		super.setDefaultTargetUrl(logoutSuccessUrl);
	}
}
