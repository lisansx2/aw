package cn.com.cslc.aw.core.common.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.user.service.UserService;

@Controller
public class CommonController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
	
	private String indexViewName = "redirect:/";
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping(value="/user/modifyPwd",method=RequestMethod.POST)
	public String modifyPwd(@SessionAttribute("currentUser") SysUser user, String oldPwd, String newPwd, String confirmNewPwd, RedirectAttributes model){
		userService.modifyPwd(user.getId(), oldPwd, newPwd, confirmNewPwd);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "修改用户密码成功！");
		return getIndexViewName();
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;  
	}
}
