package cn.com.cslc.aw.core.common.api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.core.common.domain.SysCity;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysOrgType;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.domain.SysUserOrg;
import cn.com.cslc.aw.core.common.dto.BootstrapTreeView;
import cn.com.cslc.aw.core.common.dto.CityDto;
import cn.com.cslc.aw.core.common.service.BaseService;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.core.user.service.UserService;

@RestController
@RequestMapping("/api/common")
public class CommonApiController {

	@Autowired
	private BaseService baseService;
	
	@Autowired
	private UserService UserService;
		
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value="/city/getProvinceCityList/{provinceId}",method = RequestMethod.GET, produces="application/json")
	public List<CityDto> getCityListByProviceId(@PathVariable Long  provinceId) {
		List<CityDto> cityList = Lists.newArrayList();
		List<SysCity> sysCityList = baseService.getCityListByProvinceId(provinceId);
		for(SysCity sysCity : sysCityList){
			CityDto city = new CityDto();
			city.setCityId(sysCity.getId());
			city.setCityNo(sysCity.getCityNo());
			city.setCityName(sysCity.getCityName());
			city.setProvinceName(sysCity.getSysProvince().getProvinceName());
			cityList.add(city);
		}
		return cityList;
	}

	
	@RequestMapping(value="/org/getOrgTree",method = RequestMethod.GET, produces="application/json")
	public List<BootstrapTreeView>  getOrgTreeByOrgId(@SessionAttribute List<BootstrapTreeView> userOrgBootstrapTreeView) {
		return userOrgBootstrapTreeView;
	}
	
	@RequestMapping(value="/org/getOrgCode2Tree",method = RequestMethod.GET, produces="application/json")
	public List<BootstrapTreeView>  getOrgTreeByOrgId() {
		List<String> userOrgCodeList = Lists.newArrayList();
		userOrgCodeList.add("001");
		return orgService.findOrgTreeByCodes(userOrgCodeList.toArray(new String[userOrgCodeList.size()]));
	}
	
	@RequestMapping(value="/org/getOrgTree/superMarket",method = RequestMethod.GET, produces="application/json")
	public List<BootstrapTreeView>  getSuperMarketOrgTree(@SessionAttribute SysUser currentUser) {
		List<String> userSuperMarketOrgCodeList = Lists.newArrayList();
		if(baseService.isAdmin(currentUser.getId())){
			for(SysOrg org : orgService.findAllSuperMarketOrgs()){
				userSuperMarketOrgCodeList.add(org.getCode());
			}
		}else{
			for(SysUserOrg userOrg : currentUser.getSysUserOrgs()){
				SysOrgType orgType = userOrg.getSysOrg().getSysOrgType();
				if(orgType != null && "20".equals(orgType.getCode())){
					userSuperMarketOrgCodeList.add(userOrg.getSysOrg().getCode());
				}
			}
		}

		//机构排序
		Collections.sort(userSuperMarketOrgCodeList);
		
		List<BootstrapTreeView> superMarketTreeList = orgService.findOrgTreeByCodes(userSuperMarketOrgCodeList.toArray(new String[userSuperMarketOrgCodeList.size()]));
		return superMarketTreeList;
	}
	
	@RequestMapping(value="/org/getOrgTree/{userId}",method = RequestMethod.GET, produces="application/json")
	public List<BootstrapTreeView>  getOrgTreeByOrgId(@PathVariable Long userId, @SessionAttribute List<BootstrapTreeView> userOrgBootstrapTreeView) throws Exception {
		List<BootstrapTreeView> userOrgTreeList = Lists.newArrayList();
		List<String> userOrgCodeList = Lists.newArrayList();
		userOrgCodeList.add("999");
		userOrgBootstrapTreeView = orgService.findOrgTreeByCodes(userOrgCodeList.toArray(new String[userOrgCodeList.size()]));
		for(BootstrapTreeView bootstrapTreeView : userOrgBootstrapTreeView){
			userOrgTreeList.add(bootstrapTreeView.deepClone());
		}
		
		SysUser user = UserService.findByUserId(userId);
		if(!user.getSysUserOrgs().isEmpty()){
			for(SysUserOrg userOrg : user.getSysUserOrgs()){	
				//遍历userOrgBootstrapTreeView，设置节点选择标志
				String orgCode = userOrg.getSysOrg().getCode();
				for(BootstrapTreeView orgTree : userOrgTreeList){
					orgService.recursiveTreeAndSetSelectedOrg(orgTree, orgCode);
				}
			}
		}
		return userOrgTreeList;
	}
	
}
