package cn.com.cslc.aw.core.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cslc.aw.core.common.security.sso.SsoSettings;
import cn.com.cslc.aw.core.common.utils.CookiesUtil;

@Controller
public class LoginController {
	
	@Autowired
	private SsoSettings ssoSettings;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		String webSealForwardTockenValue = CookiesUtil.getValue(request, ssoSettings.getWebsealTokenName());
		if(webSealForwardTockenValue != null){
			return "redirect:" + ssoSettings.getLoginUrl();
		}
		return "login";
	}
}
