package cn.com.cslc.aw.core.common.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.core.common.domain.SysUser;

public class SecurityUser implements UserDetails {
	
	private SysUser user;
	
	private Set<String> resourceIdentifierSet;
	
	public SecurityUser(SysUser user,Set<String> resourceIdentifierSet) {
		this.user = user;
		this.resourceIdentifierSet = resourceIdentifierSet;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authoritySet = Sets.newHashSet();
		for(String identifier : this.resourceIdentifierSet){
			authoritySet.add(new SimpleGrantedAuthority(identifier) );
		}
		
		return authoritySet;
	}
	
/*	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authoritiesList = new ArrayList<>();
		Set<SysRole> userRoles = this.user.getRoles();

		for (SysRole role : userRoles) {
			Set<SysAuthority> authoritiesSet = role.getAuthorities();
			for(SysAuthority authority : authoritiesSet){
				authoritiesList.add(new SimpleGrantedAuthority(authority.getMark()) );
			}
		}
		return authoritiesList;
	}*/

	@Override
	public String getUsername() {
		return this.user.getUserName();
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	/**
	 * 未实现
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 未实现
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 未实现
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 未实现
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
}
