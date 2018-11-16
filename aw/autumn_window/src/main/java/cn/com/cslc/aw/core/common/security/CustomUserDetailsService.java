package cn.com.cslc.aw.core.common.security;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.com.cslc.aw.core.common.domain.SysAuthority;
import cn.com.cslc.aw.core.common.domain.SysRole;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.role.service.RoleService;
import cn.com.cslc.aw.core.user.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	private static Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Assert.notNull(userName,"userName不能为空");
		SysUser user = userService.findEnableUserByUserName(userName);
		
		if (user == null) {
			LOG.info("用户： " + userName + "未找到");
			throw new UsernameNotFoundException("用户（username= " + userName + "）未找到");
		}
		
		Set<Long> roleIdSet = new HashSet<Long>();
		for(SysAuthority authority : user.getSysAuthorities()){
			SysRole role = authority.getSysRole();
			roleIdSet.add(role.getId());
		}

		Set<String> resourceIdentifierList = roleService.findResourceIdentifierListByRoleIds(roleIdSet);
		return new SecurityUser(user,resourceIdentifierList);
	}
	
}
