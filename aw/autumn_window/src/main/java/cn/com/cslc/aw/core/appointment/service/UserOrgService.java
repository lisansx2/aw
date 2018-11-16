package cn.com.cslc.aw.core.appointment.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.core.appointment.repository.SysUserOrgRepository;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.domain.SysUserOrg;
import cn.com.cslc.aw.core.common.dto.BootstrapTreeView;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.core.user.service.UserService;

@Transactional(readOnly = true)
@Service
public class UserOrgService {
	
	@Autowired
	private SysUserOrgRepository sysUserOrgRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgService orgService;

	private static final Logger LOG = LoggerFactory.getLogger(UserOrgService.class);
	
	@Transactional
	public void selectOrg(Long userId, String...orgCodeArr) {
		
		sysUserOrgRepository.deleteByUserId(userId);
		
		List<SysUserOrg> sysUserOrgs = Lists.newArrayList();
		SysUser user = userService.findByUserId(userId);
		user.setLastOperDate(new Date());
		if(orgCodeArr != null){
			for(String orgCode : orgCodeArr){
				if(!orgCode.isEmpty()){
					SysUserOrg sysUserOrg = new SysUserOrg();
					sysUserOrg.setSysOrg(orgService.findOrgByCode(orgCode));
					sysUserOrg.setSysUser(user);
					sysUserOrgs.add(sysUserOrg);
				}
			}
		}
		sysUserOrgRepository.save(sysUserOrgs);
		LOG.info("用户（userId:" + userId + "）选择机构成功");
	}
	
	/**
	 * 获取用户的机构列表（包括本机构和所辖机构）
	 * @param userId
	 * @return
	 */
	public List<SysOrg> getOrgsByUserId(Long userId){
		List<SysUserOrg>  userOrgList = sysUserOrgRepository.findOrgByUserId(userId);
		List<SysOrg> sysOrgList = Lists.newArrayList();
		if(!userOrgList.isEmpty()){
			for(SysUserOrg userOrg : userOrgList){
				SysOrg rootOrg = userOrg.getSysOrg();
				this.recursionSysOrg(sysOrgList, rootOrg);
			}
		}
		return sysOrgList;
	}
	
    private void recursionSysOrg(List<SysOrg> sysOrgList, SysOrg parentOrg){
    	sysOrgList.add(parentOrg);
		for(SysOrg childOrg : parentOrg.getChildren()){
			recursionSysOrg(sysOrgList,childOrg);
		}
    }
}
