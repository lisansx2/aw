package cn.com.cslc.aw.core.user.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.core.appointment.service.UserOrgService;
import cn.com.cslc.aw.core.authority.service.AuthorityService;
import cn.com.cslc.aw.core.common.controller.BaseController;
import cn.com.cslc.aw.core.common.domain.SysAuthority;
import cn.com.cslc.aw.core.common.domain.SysCredentialType;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysRole;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.domain.SysUserState;
import cn.com.cslc.aw.core.common.dto.BootstrapTreeView;
import cn.com.cslc.aw.core.common.exception.BaseApplicationException;
import cn.com.cslc.aw.core.user.dto.AssignUserRoleResult;
import cn.com.cslc.aw.core.user.dto.UserRoleDefinition;
import cn.com.cslc.aw.core.user.exception.UsernameNotFoundException;
import cn.com.cslc.aw.core.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	private String indexViewName = "redirect:/user";

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private UserOrgService userOrgService;
	

    @ModelAttribute("allCredentialType")
    public List<SysCredentialType> populateCredentialType() {
        return baseService.getAllSysCredentialType();
    }
    
    @ModelAttribute("allProvince")
    public List<SysProvince> populateProvince() {
        return baseService.getAllSysProvince();
    }
    
    @ModelAttribute("allUserState")
    public List<SysUserState> populateUserState() {
        return baseService.getAllUserState();
    }

	@RequestMapping(method=RequestMethod.GET)
	public String showUserIndex(@SessionAttribute List<BootstrapTreeView> userOrgBootstrapTreeView, Model model){
		return "user/userIndex";
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.GET)
	public String showAddUser(@RequestParam(value="success",defaultValue="false") boolean success, final SysUser user, Model model){
		String viewPath ="";
		if(success){
			model.addAttribute("success", Boolean.TRUE);
			viewPath ="user/addUser";
		}else{
			viewPath = "user/addUser";
		}
		return viewPath;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(@Valid SysUser user, Errors errors, RedirectAttributes model){
		if(errors.hasErrors()){
			return "user/addUser";
		}
		SysUser existUser = userService.findByUserName(user.getUserName());
		if(existUser != null){
			throw new UsernameNotFoundException("此用户名已存在！");
		}
		
		if(userService.findByUmpUserId(user.getUmpUserId()) != null){
			throw new BaseApplicationException("用户唯一标识（UMP）已存在！");
		}
		
		if(user.getSysCity().getId() == -1){
			user.setSysCity(null);
		}
/*		if(user.getSysChannelBrand().getId() == -1){
			user.setSysChannelBrand(null);
		}*/
		userService.addUser(user);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "新增用户操作成功！");
		return getIndexViewName();
	}
	
	
	@RequestMapping(value="/modifyUser",method=RequestMethod.GET)
	public String showModifyUser(@RequestParam(value="userId",required=false) Long userId,@RequestParam(value="success",defaultValue="false") boolean success, final SysUser user, Model model){
		String viewPath = "user/modifyUser";
		if(success){
			model.addAttribute("success", Boolean.TRUE);
		}else{
			SysUser updateUser = userService.findByUserId(userId);
			model.addAttribute(updateUser);
			if(updateUser.getSysProvince() != null){
				model.addAttribute("allProvinceCity",baseService.getCityListByProvinceId(updateUser.getSysProvince().getId()));
			}
		}
		return viewPath;
	}
	
	@RequestMapping(value="/modifyUser",method=RequestMethod.POST)
	public String modifyUser(@Valid SysUser user, Errors errors, RedirectAttributes model){
		if(errors.hasErrors()){
			return "user/modifyUser";
		}
		
		if(user.getSysCity().getId() == -1){
			user.setSysCity(null);
		}
		
/*		if(user.getSysChannelBrand().getId() == -1){
			user.setSysChannelBrand(null);
		}*/
		
		
		userService.modifyUser(user);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "修改用户操作成功！");
		return getIndexViewName();
	}

	@RequestMapping(value="/enableUser",method=RequestMethod.POST)
	public String enableUser(@RequestParam("ids") String ids,RedirectAttributes model){
		String[] idsStrArr = ids.split(",");
		Long[] idsLongArr = new Long[idsStrArr.length];
		for(int i = 0; i < idsStrArr.length; i ++){
			idsLongArr[i] = Long.parseLong(idsStrArr[i]);
		}

		userService.enableUserByIds(idsLongArr);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "启用用户操作成功！");
		return getIndexViewName();
	}
	
	@RequestMapping(value="/disableUser",method=RequestMethod.POST)
	public String disableUser(@RequestParam("ids") String ids,RedirectAttributes model){
		String[] idsStrArr = ids.split(",");
		Long[] idsLongArr = new Long[idsStrArr.length];
		for(int i = 0; i < idsStrArr.length; i ++){
			idsLongArr[i] = Long.parseLong(idsStrArr[i]);
		}

		userService.disableUserByIds(idsLongArr);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "停用用户操作成功！");
		return getIndexViewName();
	}
	
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	public String deleteUser(@RequestParam("ids") String ids,RedirectAttributes model){
		String[] idsStrArr = ids.split(",");
		Long[] idsLongArr = new Long[idsStrArr.length];
		for(int i = 0; i < idsStrArr.length; i ++){
			idsLongArr[i] = Long.parseLong(idsStrArr[i]);
		}

		userService.deleteUserByIds(idsLongArr);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "删除用户操作成功！");
		return getIndexViewName();
	}
	
	@RequestMapping(value="/assignRoles",method=RequestMethod.GET)
	public String showAssignRoles(@RequestParam(value="userId",required=false) Long userId,@RequestParam(value="success",defaultValue="false") boolean success, final SysUser user,  @SessionAttribute Set<SysAuthority> userAuthoritySet, Model model){
		String viewPath = "user/assignRoles";
		
		if(success){
			model.addAttribute("success", Boolean.TRUE);
		}else{
			AssignUserRoleResult assignUserRoleResult = new AssignUserRoleResult();
			SysUser  sysUser= userService.findByUserId(userId);
			assignUserRoleResult.setSysUser(sysUser);
			
			List<UserRoleDefinition> allUserRoleDefList = Lists.newArrayList();
			
			List<SysRole> allSysRoleList = Lists.newArrayList();
			for(SysAuthority authority : userAuthoritySet){
				SysRole role = authority.getSysRole();
				if(role.getName().equals("超级管理员")){
					allSysRoleList = baseService.getAllSysRole();
					break;
				}else{
					allSysRoleList.add(role);
				}
			}
			
			for(SysRole role : allSysRoleList){
				UserRoleDefinition userRoleDefinition = new UserRoleDefinition();
				userRoleDefinition.setRoleId(role.getId());
				userRoleDefinition.setRoleName(role.getName());
				userRoleDefinition.setIsAuthority(Boolean.FALSE);
				allUserRoleDefList.add(userRoleDefinition);
			}
			
			for(SysAuthority authority : sysUser.getSysAuthorities()){
				SysRole role = authority.getSysRole();
				for(UserRoleDefinition userRoleDefinition : allUserRoleDefList){
					if(userRoleDefinition.getRoleId().equals(role.getId())){
						userRoleDefinition.setUserId(sysUser.getId());
						userRoleDefinition.setIsAuthority(Boolean.TRUE);
					}
				}
			}
			assignUserRoleResult.setUserRoleDefList(allUserRoleDefList);
			model.addAttribute(assignUserRoleResult);
		}
		return viewPath;
	}
	
	@RequestMapping(value="/assignRoles",method=RequestMethod.POST)
	public String assignRoles(@RequestParam(value="sysUser.id",required=false) Long userId, @RequestParam(value="selectedRoleList",required=false) Long[] selectedRoleIdArr,RedirectAttributes model){
		authorityService.assignRoles(userId,selectedRoleIdArr);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "分配用户角色操作成功！");
		return getIndexViewName();
	}
	
	@RequestMapping(value="/selectOrg",method=RequestMethod.GET)
	public String showSelectOrg(@RequestParam(value="userId",required=false) Long userId,@RequestParam(value="success",defaultValue="false") boolean success, final SysUser user, Model model){
		String viewPath = "user/selectOrg";
		
		if(success){
			model.addAttribute("success", Boolean.TRUE);
		}else{
			SysUser  sysUser= userService.findByUserId(userId);
			model.addAttribute(sysUser);
			model.addAttribute("userId", userId);
		}
		return viewPath;
	}
	
	@RequestMapping(value="/selectOrg",method=RequestMethod.POST)
	public String selectOrg(@RequestParam(value="id",required=false) Long userId, @RequestParam(value="userOrgInfo", required=false) String[] sysUserOrg,RedirectAttributes model){
		userOrgService.selectOrg(userId, sysUserOrg);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "选择机构操作成功！");
		return getIndexViewName();
	}
	
	@RequestMapping(value="/showNoOrgUser",method=RequestMethod.GET)
	public String showNoOrgUser(@RequestParam(value="success",defaultValue="false") boolean success, Model model){
		String viewPath ="";
		if(success){
			model.addAttribute("success", Boolean.TRUE);
			viewPath ="user/showNoOrgUser";
		}else{
			viewPath = "user/showNoOrgUser";
		}
		return viewPath;
	}
	
	@RequestMapping(value="/showNoRoleUser",method=RequestMethod.GET)
	public String showNoRoleUser(@RequestParam(value="success",defaultValue="false") boolean success, Model model){
		String viewPath ="";
		if(success){
			model.addAttribute("success", Boolean.TRUE);
			viewPath ="user/showNoRoleUser";
		}else{
			viewPath = "user/showNoRoleUser";
		}
		return viewPath;
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;  
	}
}
