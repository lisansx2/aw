package cn.com.cslc.aw.core.common.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cslc.aw.core.authority.service.AuthorityService;
import cn.com.cslc.aw.core.common.constant.SysUserStateConstant;
import cn.com.cslc.aw.core.common.domain.SysCity;
import cn.com.cslc.aw.core.common.domain.SysCredentialType;
import cn.com.cslc.aw.core.common.domain.SysOrgType;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysRole;
import cn.com.cslc.aw.core.common.domain.SysUserState;
import cn.com.cslc.aw.core.common.dto.BootstrapTreeView;
import cn.com.cslc.aw.core.common.repository.SysCityRepository;
import cn.com.cslc.aw.core.common.repository.SysCredentialTypeRepository;
import cn.com.cslc.aw.core.common.repository.SysOrgTypeRepository;
import cn.com.cslc.aw.core.common.repository.SysProvinceRepository;
import cn.com.cslc.aw.core.common.repository.SysUserStateRepository;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.core.role.repository.RoleRepository;


@Transactional(readOnly = true)
@Service
public class BaseService {
	@Autowired
	private SysCredentialTypeRepository sysCredentialTypeRepository;
	
	@Autowired
	private SysProvinceRepository sysProvinceRepository;
	
	@Autowired
	private SysCityRepository sysCityRepository;
	
	@Autowired
	private SysOrgTypeRepository sysOrgTypeRepository;
	
	@Autowired
	private SysUserStateRepository sysUserStateRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private AuthorityService authorityService;
	

    public List<SysCredentialType> getAllSysCredentialType() {
        return sysCredentialTypeRepository.findAll();
    }
    
    public SysCredentialType getSysCredentialTypeByCode(String code){
    	return sysCredentialTypeRepository.findByCode(code);
    }
    
    public List<SysProvince> getAllSysProvince() {
        return sysProvinceRepository.findAll();
    }
    
    public SysProvince getSysProvinceByNo(String provinceNo){
    	return sysProvinceRepository.findByProvinceNo(provinceNo);
    }
    
    public List<SysCity> getCityListByProvinceId(Long provinceId){
    	return sysCityRepository.findByProvinceId(provinceId);
    }

    public SysCity getSysCityByNo(String cityNo){
    	return sysCityRepository.findByCityNo(cityNo);
    }
    
	public List<SysUserState> getAllUserState() {
		return sysUserStateRepository.findAll();
	}
	
	public List<SysOrgType> getAllSysOrgType() {
		return sysOrgTypeRepository.findAll();
	}
	
/*	public SysUserState getDefaultUserState(){
		//
		SysUserStateConstant createUserState=SysUserStateConstant.Created;
		return sysUserStateRepository.findByCode(createUserState.getCode());
	}*/
	
	public SysUserState getUserState(SysUserStateConstant userState){
		return sysUserStateRepository.findByCode(userState.getCode());
	}
	
	public List<SysRole> getAllSysRole(){
		return roleRepository.findAll();
	}
	
    

    public List<BootstrapTreeView> getAllOrgTree(){
    	return orgService.findAllOrgTree();
    }

	public SysCredentialTypeRepository getSysCredentialTypeRepository() {
		return sysCredentialTypeRepository;
	}

	public void setSysCredentialTypeRepository(SysCredentialTypeRepository sysCredentialTypeRepository) {
		this.sysCredentialTypeRepository = sysCredentialTypeRepository;
	}

	public SysProvinceRepository getSysProvinceRepository() {
		return sysProvinceRepository;
	}

	public void setSysProvinceRepository(SysProvinceRepository sysProvinceRepository) {
		this.sysProvinceRepository = sysProvinceRepository;
	}

	public SysCityRepository getSysCityRepository() {
		return sysCityRepository;
	}

	public void setSysCityRepository(SysCityRepository sysCityRepository) {
		this.sysCityRepository = sysCityRepository;
	}

	public SysUserStateRepository getSysUserStateRepository() {
		return sysUserStateRepository;
	}

	public void setSysUserStateRepository(SysUserStateRepository sysUserStateRepository) {
		this.sysUserStateRepository = sysUserStateRepository;
	}

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}



	public OrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	
	public boolean isAdmin(Long userId){
		return authorityService.isAdmin(userId);
	}



}
