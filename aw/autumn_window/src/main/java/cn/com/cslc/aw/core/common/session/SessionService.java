package cn.com.cslc.aw.core.common.session;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.core.appointment.service.UserOrgService;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysUserOrg;
import cn.com.cslc.aw.core.common.dto.MenuDto;
import cn.com.cslc.aw.core.common.security.SecurityUser;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.core.resource.service.ResourceService;
import cn.com.cslc.aw.customer.domain.SysCustomer;

@Component
public class SessionService{

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private OrgService orgService;
	
	@Autowired
	private UserOrgService userOrgService;
		
	public void loginSessionProcess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		request.getSession().setAttribute("currentUser", user.getUser());
		if(!user.getUser().getSysUserOrgs().isEmpty()){
			List<SysOrg> orgList = userOrgService.getOrgsByUserId(user.getUser().getId());
			request.getSession().setAttribute("userOrgList", orgList);
			
			List<String> userOrgCodeList = Lists.newArrayList();
			for(SysUserOrg userOrg : user.getUser().getSysUserOrgs()){
				userOrgCodeList.add(userOrg.getSysOrg().getCode());
			}
			//机构排序
			Collections.sort(userOrgCodeList);

			request.getSession().setAttribute("userOrgBootstrapTreeView", orgService.findOrgTreeByCodes(userOrgCodeList.toArray(new String[userOrgCodeList.size()])));
			
			//设置代理
			List<SysAgent> userAgentList = orgService.findOrgAgent(orgList);
			request.getSession().setAttribute("userAgentList", userAgentList);
			
			Set<String> agentCodeSet = Sets.newHashSet();
			for(SysAgent agent : userAgentList){
				agentCodeSet.add(agent.getCode());
			}
			request.getSession().setAttribute("userAgentCodeSet", agentCodeSet);
			
			//设置省
			List<SysProvince> userProvinceList = orgService.findOrgProvince(orgList);
			Set<String> provinceCodeSet = Sets.newHashSet();
			for(SysProvince province : userProvinceList){
				provinceCodeSet.add(province.getProvinceNo());
			}
			request.getSession().setAttribute("userProvinceCodeSet", provinceCodeSet);
			
			//设置大客户
			List<SysCustomer> userCustomerList = orgService.findOrgCustomer(orgList);
			request.getSession().setAttribute("userCustomerList", userCustomerList);
			
			Set<String> customerCodeSet = Sets.newHashSet();
			for(SysCustomer customer : userCustomerList){
				customerCodeSet.add(customer.getCode());
			}
			request.getSession().setAttribute("userCustomerCodeSet", customerCodeSet);

		}
		
		Set<String> authoritySet = Sets.newHashSet();
		for(GrantedAuthority authority : authentication.getAuthorities()){
			authoritySet.add(authority.getAuthority());
		}
		request.getSession().setAttribute("authoritySet", authoritySet);
		List<MenuDto> menuList = resourceService.findMenus(user.getUser());
		request.getSession().setAttribute("menuList", menuList);
		
		request.getSession().setAttribute("userAuthoritySet", user.getUser().getSysAuthorities());
		
	}

}
