package cn.com.cslc.aw.core.user.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.core.common.constant.SysUserStateConstant;
import cn.com.cslc.aw.core.common.domain.SysAuthority;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.domain.SysUserOrg;
import cn.com.cslc.aw.core.common.exception.BusinessLogicException;
import cn.com.cslc.aw.core.common.service.BaseService;
import cn.com.cslc.aw.core.user.dto.QueryUserCondition;
import cn.com.cslc.aw.core.user.repository.UserRepository;

@Transactional(readOnly = true)
@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BaseService baseService;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	public SysUser findByUserName(String userName) {
		Assert.notNull(userName, "userName不允许为空");
		return userRepository.findByUserName(userName);
	}

	public SysUser findByUmpUserId(Long umpUserId) {
		return userRepository.findByUmpUserId(umpUserId);
	}

	public SysUser findByUserId(Long userId) {
		Assert.notNull(userId, "userId不允许为空");
		return userRepository.findOne(userId);
	}

	public Page<SysUser> queryUserByCondition(QueryUserCondition queryUserCondition, Pageable pageable) {
		Specification<SysUser> specification = new Specification<SysUser>() {
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				if (queryUserCondition == null) {
					return null;
				}
				/* 查询记录总数时不进行fetch，否则报错*/
				if (query.getResultType() != Long.class) {
					root.fetch("sysProvince",JoinType.LEFT);
					root.fetch("sysCity",JoinType.LEFT);
					root.fetch("sysUserState",JoinType.LEFT);
				} 

				List<Predicate> predicateList = Lists.newArrayList();

				if (!StringUtils.isEmpty(queryUserCondition.getUserName())) {
					predicateList
							.add(cb.like(root.<String>get("userName"), "%" + queryUserCondition.getUserName() + "%"));
				}

				if (!StringUtils.isEmpty(queryUserCondition.getUserFullName())) {
					predicateList.add(cb.like(root.<String>get("userFullName"),
							"%" + queryUserCondition.getUserFullName() + "%"));
				}

				if (queryUserCondition.getUserStateId() != 0) {
					predicateList
							.add(cb.equal(root.get("sysUserState").get("id"), queryUserCondition.getUserStateId()));
				}
				
				if (!StringUtils.isEmpty(queryUserCondition.getOrgCode())) {
					predicateList.add(cb.equal( root.join("sysUserOrgs",JoinType.LEFT).join("sysOrg").get("code"), queryUserCondition.getOrgCode()));
				}else{
					List<String> userOrgCodeList = queryUserCondition.getOrgCodeList();
					predicateList.add( root.join("sysUserOrgs",JoinType.LEFT).join("sysOrg").get("code").in(userOrgCodeList));
				}
				
				//过滤掉登录用户自己
				String currentUserId = queryUserCondition.getAdditionalData().get("userId");
				predicateList.add(cb.notEqual(root.get("id"), currentUserId));
				
				//查询所有已授权的用户
				predicateList.add( root.join("sysAuthorities",JoinType.LEFT).join("sysRole").get("id").isNotNull());
				
				if (predicateList.isEmpty()) {
					return null;
				} else {
					return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
				}
			}
		};
		Page<SysUser> pageUser = userRepository.findAll(specification, pageable);
		return pageUser;
	}

	@Transactional
	public SysUser addUser(SysUser user) {
		Date currentTime = new Date();
		user.setCreateDate(currentTime);
		user.setLastOperDate(currentTime);
		user.setSysUserState(baseService.getUserState(SysUserStateConstant.Created));
		return userRepository.save(user);
	}

	@Transactional
	public SysUser modifyUser(SysUser user) {
		SysUser updatedUser = userRepository.findOne(user.getId());
		
		if(!updatedUser.getUmpUserId().equals(user.getUmpUserId()) && userRepository.findByUmpUserId(user.getUmpUserId()) != null){
			throw new BusinessLogicException("用户唯一标识已存在，无法执行修改操作");
		}
		
		updatedUser.setUserFullName(user.getUserFullName());
		updatedUser.setSysCredentialType(user.getSysCredentialType());
		updatedUser.setCredentialNo(user.getCredentialNo());
		updatedUser.setSysProvince(user.getSysProvince());
		updatedUser.setSysCity(user.getSysCity());
/*		updatedUser.setSysChannelBrand(user.getSysChannelBrand());*/
		updatedUser.setUmpUserId(user.getUmpUserId());
		updatedUser.setDescription(user.getDescription());
		updatedUser.setLastOperDate(new Date());

		return userRepository.save(updatedUser);
	}
	
	public void SynchronizeUser(SysUser synUser){
		SysUser sysUser = findByUmpUserId(synUser.getUmpUserId());
		if(sysUser == null){//创建用户
			userRepository.save(synUser);
		}else{//更新用户
			sysUser.setUserName(synUser.getUserName());
			sysUser.setUserFullName(synUser.getUserFullName());
			sysUser.setMobilePhoneNumber(synUser.getMobilePhoneNumber());
			sysUser.setLastOperDate(synUser.getLastOperDate());
			sysUser.setUmpUserId(synUser.getUmpUserId());
			sysUser.setSysCredentialType(synUser.getSysCredentialType());
			sysUser.setCredentialNo(synUser.getCredentialNo());
			sysUser.setSysProvince(synUser.getSysProvince());
			sysUser.setSysCity(synUser.getSysCity());
			sysUser.setSysUserState(synUser.getSysUserState());
			userRepository.save(sysUser);
		}
	}

	/**
	 * 启用用户 1、只允许状态为新建、停用的用户执行启用操作 2、状态为启用的用户也不允许执行启用操作 3、要么全部成功，要么全部失败
	 * 
	 * @param ids
	 */
	@Transactional
	public void enableUserByIds(Long... ids) {
		List<SysUser> sysUserList = userRepository.findByIdIn(ids);
		Set<String> errorUserStateCodeSet = Sets.newHashSet();
		errorUserStateCodeSet.add(SysUserStateConstant.Enabled.getCode());
		errorUserStateCodeSet.add(SysUserStateConstant.Deleted.getCode());

		for (SysUser user : sysUserList) {
			if (errorUserStateCodeSet.contains(user.getSysUserState().getCode())) {
				throw new BusinessLogicException("用户当前状态不符合启用条件，无法执行启用操作");
			} else {
				user.setLastOperDate(new Date());
				user.setSysUserState(baseService.getUserState(SysUserStateConstant.Enabled));
			}
		}

		userRepository.save(sysUserList);
	}

	/**
	 * 停用用户 1、只允许状态为启用的用户执行停用操作 2、状态为停用的用户也不允许执行停用操作 3、要么全部成功，要么全部失败
	 * 
	 * @param ids
	 */
	@Transactional
	public void disableUserByIds(Long... ids) {
		List<SysUser> sysUserList = userRepository.findByIdIn(ids);
		Set<String> errorUserStateCodeSet = Sets.newHashSet();
		errorUserStateCodeSet.add(SysUserStateConstant.Created.getCode());
		errorUserStateCodeSet.add(SysUserStateConstant.Disable.getCode());
		errorUserStateCodeSet.add(SysUserStateConstant.Deleted.getCode());

		for (SysUser user : sysUserList) {
			if (errorUserStateCodeSet.contains(user.getSysUserState().getCode())) {
				throw new BusinessLogicException("用户当前状态不符合停用条件，无法执行停用操作");
			} else {
				user.setLastOperDate(new Date());
				user.setSysUserState(baseService.getUserState(SysUserStateConstant.Disable));
			}
		}
		userRepository.save(sysUserList);
	}

	/**
	 * 删除用户 1、只允许状态为停用的用户执行删除操作 2、状态为删除的用户也不允许执行删除操作 3、要么全部成功，要么全部失败
	 * 
	 * @param ids
	 */
	@Transactional
	public void deleteUserByIds(Long... ids) {
		List<SysUser> sysUserList = userRepository.findByIdIn(ids);
		Set<String> errorUserStateCodeSet = Sets.newHashSet();
		errorUserStateCodeSet.add(SysUserStateConstant.Created.getCode());
		errorUserStateCodeSet.add(SysUserStateConstant.Enabled.getCode());
		errorUserStateCodeSet.add(SysUserStateConstant.Deleted.getCode());

		for (SysUser user : sysUserList) {
			if (errorUserStateCodeSet.contains(user.getSysUserState().getCode())) {
				throw new BusinessLogicException("用户当前状态不符合删除条件，无法执行删除操作");
			} else {
				user.setLastOperDate(new Date());
				user.setSysUserState(baseService.getUserState(SysUserStateConstant.Deleted));
			}
		}
		userRepository.save(sysUserList);
	}
	
	public SysUser findEnableUserByUserName(String userName){
		SysUser user = userRepository.findOne(new Specification<SysUser>(){
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch("sysAuthorities").fetch("sysRole");
				root.fetch("sysUserState");
				root.fetch("sysCity",JoinType.LEFT);
				root.fetch("sysProvince",JoinType.LEFT);
				root.fetch("sysUserOrgs").fetch("sysOrg").fetch("sysOrgType",JoinType.LEFT);
				Predicate userNamePredicate = cb.equal(root.get("userName"), userName);
				Predicate userStatePredicate = cb.equal(root.get("sysUserState").get("code"), SysUserStateConstant.Enabled.getCode());
				return cb.and(userNamePredicate,userStatePredicate);
			}
		});
		return user;
	}
	
	@Transactional
	public SysUser modifyPwd(Long userId, String oldPwd, String newPwd, String confirmNewPwd) {
		SysUser updatedUser = userRepository.findOne(userId);
		String storedEncodeOldPwd = updatedUser.getPassword();
		if(!pwdEncoder.matches(oldPwd, storedEncodeOldPwd)){
			String errorMsg = "旧密码错误，无法执行修改密码操作";
			LOG.info(errorMsg);
			throw new BusinessLogicException("旧密码错误，无法执行修改密码操作");
		}
		
		if(!newPwd.equals(confirmNewPwd)){
			throw new BusinessLogicException("新密码和确认新密码不一致，无法执行修改密码操作");
		}
		
		updatedUser.setPassword(pwdEncoder.encode(newPwd));
		updatedUser.setLastOperDate(new Date());

		return userRepository.save(updatedUser);
	}
	
	
	public Page<SysUser> queryNoOrgUser(Set<String> provinceCodeSet, Pageable pageable) {
		Specification<SysUser> specification = new Specification<SysUser>() {
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				/* 查询记录总数时不进行fetch，否则报错*/
				if (query.getResultType() != Long.class) {
					root.fetch("sysProvince",JoinType.LEFT);
					root.fetch("sysCity",JoinType.LEFT);
					root.fetch("sysUserState",JoinType.LEFT);
				} 
				Join<SysUser,SysUserOrg> join = root.join("sysUserOrgs",JoinType.LEFT);
				Predicate nullPredicate = cb.isNull(join.get("id"));
				Predicate provinceCodePredicate = root.get("sysProvince").get("provinceNo").in(provinceCodeSet);				   
				return cb.and(nullPredicate,provinceCodePredicate);
			}
		};
		Page<SysUser> pageUser = userRepository.findAll(specification, pageable);
		return pageUser;
	}
	
	public Page<SysUser> queryNoRoleUser(Set<String> provinceCodeSet, Pageable pageable) {
		Specification<SysUser> specification = new Specification<SysUser>() {
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				/* 查询记录总数时不进行fetch，否则报错*/
				if (query.getResultType() != Long.class) {
					root.fetch("sysProvince",JoinType.LEFT);
					root.fetch("sysCity",JoinType.LEFT);
					root.fetch("sysUserState",JoinType.LEFT);
				} 
				Join<SysUser,SysAuthority> join = root.join("sysAuthorities",JoinType.LEFT);
				Predicate predicate = cb.isNull(join.get("id"));
				Predicate provinceCodePredicate = root.get("sysProvince").get("provinceNo").in(provinceCodeSet);	
				return cb.and(predicate,provinceCodePredicate);
			}
		};
		Page<SysUser> pageUser = userRepository.findAll(specification, pageable);
		return pageUser;
	}
}
