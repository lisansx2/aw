package cn.com.cslc.aw.core.user.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.newDataTablesResponseFor;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.core.common.domain.SysAuthority;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.domain.SysUserOrg;
import cn.com.cslc.aw.core.user.dto.QueryUserCondition;
import cn.com.cslc.aw.core.user.dto.QueryUserResult;
import cn.com.cslc.aw.core.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends BaseApiController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/search", method = POST)
	public DataTablesResponse<List<QueryUserResult>> queryUsers(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryUserCondition queryUserCondition, @SessionAttribute SysUser currentUser, @SessionAttribute List<SysOrg> userOrgList) {
		queryUserCondition.getAdditionalData().put("userId", currentUser.getId().toString());
		
		for(SysOrg org : userOrgList){
			queryUserCondition.getOrgCodeList().add(org.getCode());
		}
	    return this.searchByCondition(dataTablesRequest, queryUserCondition) ;
	}
	
	private DataTablesResponse<List<QueryUserResult>> searchByCondition(DataTablesRequest dataTablesRequest,
			QueryUserCondition queryUserCondition) {
		
		/**
		 * 处理多对一排序
		 */
		for(Entry<Integer,Map<String,String>> orderEntry : dataTablesRequest.getOrderMap().entrySet()){
			Map<String,String> orderMap = orderEntry.getValue();
			if(orderMap.get("orderColumn").equals("region")){
				orderMap.put("orderColumn", "sysProvince.provinceNo");
			}
			if(orderMap.get("orderColumn").equals("sysUserState")){
				orderMap.put("orderColumn", "sysUserState.code");
			}
		}
		
		Page<SysUser> page = userService.queryUserByCondition(queryUserCondition, dataTablesRequest.getPageRequest());
		
		return generateUserDTResponse(dataTablesRequest, page);
	}
	
/*	@RequestMapping(value = "/deleteUser", method = POST)
	public void delUsers(@RequestParam("ids") String ids) {
		String[] idsStrArr = ids.split(",");
		Long[] idsLongArr = new Long[idsStrArr.length];
		for(int i = 0; i < idsStrArr.length; i ++){
			idsLongArr[i] = Long.parseLong(idsStrArr[i]);
		}
		userService.delUserByIds(idsLongArr);
	}*/
	
	private DataTablesResponse<List<QueryUserResult>> generateUserDTResponse(DataTablesRequest dataTablesRequest, Page<SysUser> page) {
		DataTablesResponse<List<QueryUserResult>> response = newDataTablesResponseFor(dataTablesRequest);
		List<QueryUserResult> queryUserResultList = Lists.newArrayList();
		for (SysUser user : page.getContent()) {
			QueryUserResult queryUserResult = new QueryUserResult();
			queryUserResult.setId(user.getId());
			queryUserResult.setUserName(user.getUserName());
			queryUserResult.setUserFullName(user.getUserFullName());
			if(user.getSysProvince() == null){
				queryUserResult.setRegion("全国");
			}else if(user.getSysCity() == null){
				queryUserResult.setRegion(user.getSysProvince().getProvinceName());
			}else{
				queryUserResult.setRegion(user.getSysProvince().getProvinceName() + user.getSysCity().getCityName());
			}
			queryUserResult.setMobilePhoneNo(user.getMobilePhoneNumber());
			
			String orgName = "";
			if(!user.getSysUserOrgs().isEmpty()){
				Iterator<SysUserOrg> userOrgIter = user.getSysUserOrgs().iterator();
				while(userOrgIter.hasNext()){
					orgName += userOrgIter.next().getSysOrg().getName();
					if(userOrgIter.hasNext()){
						orgName += "，";
					}
				}
			}
			queryUserResult.setOrgName(orgName);
			
			String roleName = "";
			if(!user.getSysAuthorities().isEmpty()){
				Iterator<SysAuthority> userRoleIter = user.getSysAuthorities().iterator();
				while(userRoleIter.hasNext()){
					roleName += userRoleIter.next().getSysRole().getName();
					if(userRoleIter.hasNext()){
						roleName += "，";
					}
				}
			}
			queryUserResult.setRoleName(roleName);
			
			queryUserResult.setLastOperDate(user.getLastOperDate());
			queryUserResult.setSysUserState(user.getSysUserState().getName());
			
			queryUserResultList.add(queryUserResult);
		}
		response.setData(queryUserResultList);
		response.setRecordsTotal(page.getTotalElements());
		response.setRecordsFiltered(page.getTotalElements());
		return response;
	}
	
	
	@RequestMapping(value = "/getNoOrgUser", method = POST)
	public DataTablesResponse<List<QueryUserResult>> queryNoOrgUsers(@DataTablesRequestParams DataTablesRequest dataTablesRequest, @SessionAttribute SysUser currentUser,@SessionAttribute Set<String> userProvinceCodeSet) {
		Page<SysUser> page = userService.queryNoOrgUser(userProvinceCodeSet, dataTablesRequest.getPageRequest());
		return generateUserDTResponse(dataTablesRequest, page);
	}
	
	@RequestMapping(value = "/getNoRoleUser", method = POST)
	public DataTablesResponse<List<QueryUserResult>> queryNoRoleUsers(@DataTablesRequestParams DataTablesRequest dataTablesRequest, @SessionAttribute SysUser currentUser, @SessionAttribute Set<String> userProvinceCodeSet) {
		Page<SysUser> page = userService.queryNoRoleUser(userProvinceCodeSet, dataTablesRequest.getPageRequest());
		return generateUserDTResponse(dataTablesRequest, page);
	}
}
