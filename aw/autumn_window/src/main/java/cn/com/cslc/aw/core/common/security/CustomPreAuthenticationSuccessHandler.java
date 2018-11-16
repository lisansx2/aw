package cn.com.cslc.aw.core.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cn.com.cslc.aw.core.common.session.SessionService;

@Component
public class CustomPreAuthenticationSuccessHandler implements
AuthenticationSuccessHandler {

	private static Logger LOG = LoggerFactory.getLogger(CustomPreAuthenticationSuccessHandler.class);

	@Autowired
	private SessionService sessionService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		sessionService.loginSessionProcess(request, response,authentication);
		LOG.info("用户通过Ukey方式登录（用户名：" + user.getUsername() + "）");
	}
	
}
