package cn.com.cslc.aw.core.role.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.core.common.domain.SysRole;
import cn.com.cslc.aw.core.common.domain.SysRoleResource;
import cn.com.cslc.aw.core.role.repository.RoleRepository;
import cn.com.cslc.aw.core.role.repository.RoleResourceRepository;

@Transactional(readOnly = true)
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleResourceRepository roleResourceRepository;
	
	public List<SysRole> findByRoleIds(Long... roleIds) {
		Set<Long> roleIdSet = new HashSet<Long>(Arrays.asList(roleIds));
		return roleRepository.findAll(roleIdSet);
	}

	public SysRole findByRoleId(Long roleId) {
		return roleRepository.findOne(roleId);
	}
	
	public Set<String> findResourceIdentifierListByRoleIds(Set<Long> roleIdSet) {
		Set<String> resourceIdentifierSet = Sets.newHashSet();
		if(roleIdSet.isEmpty()){
			return resourceIdentifierSet;
		}
		
		List<SysRoleResource> roleResourceList = roleResourceRepository.findAll(new Specification<SysRoleResource>(){
			@Override
			public Predicate toPredicate(Root<SysRoleResource> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.fetch("sysResource");
				root.fetch("sysRole");
				return root.get("sysRole").get("id").in(roleIdSet);
			}
		});
		
		for(SysRoleResource roleResource : roleResourceList){
			resourceIdentifierSet.add(roleResource.getSysResource().getIdentifier());
		}
		
		return resourceIdentifierSet;
	}
}
