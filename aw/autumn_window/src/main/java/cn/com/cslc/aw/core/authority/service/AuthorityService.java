package cn.com.cslc.aw.core.authority.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.core.authority.repository.AuthorityRepository;
import cn.com.cslc.aw.core.common.domain.SysAuthority;
import cn.com.cslc.aw.core.common.domain.SysRole;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.role.service.RoleService;
import cn.com.cslc.aw.core.user.service.UserService;

@Transactional(readOnly = true)
@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthorityService.class);
	/**
	 * 分配角色
	 * @param userId
	 * @param selectedRoleIdArr
	 */
	@Transactional
	public void assignRoles(Long userId, Long... selectedRoleIdArr) {
		authorityRepository.deleteByUserId(userId);
		List<SysAuthority> sysAuthorityList = Lists.newArrayList();
		SysUser user = userService.findByUserId(userId);
		user.setLastOperDate(new Date());
		if(selectedRoleIdArr != null){
			for(Long roleId : selectedRoleIdArr){
				SysAuthority sysAuthority = new SysAuthority();
				sysAuthority.setSysRole(roleService.findByRoleId(roleId));
				sysAuthority.setSysUser(user);
				sysAuthorityList.add(sysAuthority);
			}
		}
		if(!sysAuthorityList.isEmpty()){
			authorityRepository.save(sysAuthorityList);
			LOG.info("用户（userId:" + userId + "）分配角色成功");
		}
	}
	/**
	 * 获取用户的资源权限标识
	 * @param user
	 * @return
	 */
	public Set<String> findResourceIdentifier(SysUser user) {
		Set<Long> roleIdSet = new HashSet<Long>();
		for(SysAuthority authority : user.getSysAuthorities()){
			SysRole role = authority.getSysRole();
			roleIdSet.add(role.getId());
		}
		return roleService.findResourceIdentifierListByRoleIds(roleIdSet);
	}
	
	public List<SysAuthority> findSysAuthorityByUserId(Long userId){
		return authorityRepository.findSysAuthorityByUserId(userId);
	}
	
	public boolean isAdmin(Long userId){
		boolean result = false;
		List<SysAuthority> authorities = authorityRepository.findSysAuthorityByUserId(userId);
		for(SysAuthority auth : authorities){
			if(auth.getSysRole().getId() == 1){
				result = true;
			}
		}
		return result;
	}
}
