package cn.com.cslc.aw.core.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cn.com.cslc.aw.core.common.session.SessionService;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static Logger LOG = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);
	
	@Value("${session-expired-time}")
	private Integer sessionExpiredMinutes;
	
	@Autowired
	private SessionService sessionService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		request.getSession().setMaxInactiveInterval(sessionExpiredMinutes*60);
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		sessionService.loginSessionProcess(request, response, authentication);

		LOG.info("用户通过用户名密码方式登录（用户名：" + user.getUsername() + "）");
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
