package cn.com.cslc.aw.tools.core.common.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController{
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
	
	@RequestMapping("/403")
	public String err403(){
		return "/common/403";
	}
	
	@RequestMapping("/sessionExpired")
    public String sessionExpired(HttpServletRequest request,HttpServletResponse response) throws IOException {
		LOG.info("会话已过期，跳转到登陆页面...");
		boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
		if (isAjax) {
			String contentType = "application/json";
			response.setHeader("sessionstatus", "timeout");
			response.setContentType(contentType);
			response.getWriter().flush();
			return null;
		}else{
			return "redirect:/login";
		}
    }  

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(){
		return "index";
	}
	
}
