package cn.com.cslc.aw.tools.core.common.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.com.cslc.aw.tools.core.config.AppConfig;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private AppConfig appConfig;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Assert.notNull(userName,"userName不能为空");
		for(Map<String, String> userMap : appConfig.getUserDef()){
			if(userMap.get("userName").equals(userName)){
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				return new User(userName,userMap.get("passwd"),authorities);
			}
		}
		
		throw new UsernameNotFoundException("用户名" + userName +"未找到" );
	}	
}
