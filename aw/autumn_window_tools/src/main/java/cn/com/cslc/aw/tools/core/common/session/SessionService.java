package cn.com.cslc.aw.tools.core.common.session;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.tools.core.common.dto.MenuDto;
import cn.com.cslc.aw.tools.resource.service.ResourceService;

@Component
public class SessionService{

	@Autowired
	private ResourceService resourceService;
	
	public void loginSessionProcess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		request.getSession().setAttribute("currentUser", user);
		Set<String> authoritySet = Sets.newHashSet();
		for(GrantedAuthority authority : authentication.getAuthorities()){
			authoritySet.add(authority.getAuthority());
		}
		request.getSession().setAttribute("authoritySet", authoritySet);
		
		List<MenuDto> menuList = resourceService.findMenus();
		request.getSession().setAttribute("menuList", menuList);
		
	}
}
