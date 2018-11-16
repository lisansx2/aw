package cn.com.cslc.aw.core.org.controller;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import cn.com.cslc.aw.core.common.domain.SysProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.domain.SysOrgAgent;
import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.controller.BaseController;
import cn.com.cslc.aw.core.common.domain.SysAuthority;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysOrgManage;
import cn.com.cslc.aw.core.common.exception.BaseApplicationException;
import cn.com.cslc.aw.core.org.dto.OrgAuthResult;
import cn.com.cslc.aw.core.org.dto.OrgCustomerDefinition;
import cn.com.cslc.aw.core.org.dto.OrgAgentDefinition;
import cn.com.cslc.aw.core.org.service.OrgAgentService;
import cn.com.cslc.aw.core.org.service.OrgCustomerService;
import cn.com.cslc.aw.core.org.service.OrgService;
import cn.com.cslc.aw.core.user.exception.UsernameNotFoundException;
import cn.com.cslc.aw.customer.domain.SysCustomer;
import cn.com.cslc.aw.customer.domain.SysOrgCustomer;
import cn.com.cslc.aw.customer.service.CustomerService;


@Controller
@RequestMapping("/org")
public class OrgController extends BaseController{
	
	private String indexViewName = "redirect:/org";
	
	@Autowired
	private OrgAgentService orgAgentService;
	
	@Autowired
	private OrgCustomerService orgCustomerService;
	
	@Resource
	private OrgService orgService;
	
	@Resource
	private AgentService agentService;
	
	@Resource
	private CustomerService customerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showUserIndex(){
		return "orgManage/orgManageIndex";
	}

	@Override
	protected String getIndexViewName() {
		return indexViewName;
	}
	
	@RequestMapping(value="/orgAdd",method=RequestMethod.GET)
	public String showOrgAdd(@RequestParam(value="success",defaultValue="false") boolean success, final SysOrgManage org, Model model){
		String viewPath ="";
		if(success){
			model.addAttribute("success", Boolean.TRUE);
			viewPath ="orgManage/orgAdd";
		}else{
			viewPath = "orgManage/orgAdd";
		}
		return viewPath;
	}
	
	@RequestMapping(value="/orgAdd",method=RequestMethod.POST)
	public String addOrg(@Valid SysOrgManage org, Errors errors, RedirectAttributes model){
		if(errors.hasErrors()){
			return "orgManage/orgAdd";
		}
//		SysOrgManage existOrg = orgService.findByOrgName(org.getName());
//		if(existOrg != null){
//			throw new UsernameNotFoundException("此机构名已存在！");
//		}
//		if(orgService.findOrgByCode(org.getCode()) != null){
//			throw new BaseApplicationException("机构编号（code）重复！");
//		}
		
		orgService.addOrg(org);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "新增机构操作成功！");
		return getIndexViewName();
	}
	
	@RequestMapping(value="/orgUpdate",method=RequestMethod.GET)
	public String showOrgUpdate(@RequestParam(value="orgId",required=false) Long orgId,@RequestParam(value="success",
		defaultValue="false") boolean success, final SysOrgManage org, Model model){
		String viewPath = "orgManage/orgUpdate";
		if(success){
			model.addAttribute("success", Boolean.TRUE);
		}else{
			SysOrgManage updateOrg = orgService.findByOrgId(orgId);
			model.addAttribute("sysOrg",updateOrg);
			model.addAttribute("allSysOrgType",baseService.getAllSysOrgType());
			model.addAttribute("parentName",orgService.findByOrgId(updateOrg.getParentId()).getName());
			List<SysProvince> sysProvinces = populateProvince();
			String provinceName = null;
			for(SysProvince prov : sysProvinces){
				if(prov.getId().equals(updateOrg.getProvinceId())){
					provinceName = prov.getProvinceName();
					break;
				}
			}
			model.addAttribute("provinceName",provinceName);
		}
		return viewPath;
	}
	
	@RequestMapping(value="/orgUpdate",method=RequestMethod.POST)
	public String updateOrg(@Valid SysOrgManage org, Errors errors, RedirectAttributes model){
		if(errors.hasErrors()){
			return "orgManage/orgUpdate";
		}
		orgService.updateOrg(org);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "修改机构操作成功！");
		return getIndexViewName();
	}
	
	@RequestMapping(value="/orgAuth",method=RequestMethod.GET)
	public String showAssignRoles(@RequestParam(value="orgId",required=false) Long orgId,
			@RequestParam(value="success",defaultValue="false") boolean success, final SysOrgManage org,
			@SessionAttribute Set<SysAuthority> userAuthoritySet, Model model){
		String viewPath = "orgManage/orgAuth";
		
		if(success){
			model.addAttribute("success", Boolean.TRUE);
		}else{
			OrgAuthResult orgAuthResult = new OrgAuthResult();
			SysOrg  sysOrg= orgService.findOneById(orgId);
			orgAuthResult.setSysOrg(sysOrg);
			String provinceNo ="";
			if(sysOrg.getSysProvince()!=null) {
				provinceNo =sysOrg.getSysProvince().getProvinceNo();
			}
			
			/*-------------------OrgAgent----代理---------------------------**/
			List<OrgAgentDefinition> allOrgAgentDefList = Lists.newArrayList();
			List<SysAgent> allSysAgentList = Lists.newArrayList();
			allSysAgentList = agentService.findUnusedAgentByProvinceNo(provinceNo,orgId);
			for(SysAgent agent : allSysAgentList){
				OrgAgentDefinition orgAgentDefinition = new OrgAgentDefinition();
				orgAgentDefinition.setAgentId(agent.getId());
				orgAgentDefinition.setAgentName(agent.getName());
				orgAgentDefinition.setIsAuthority(Boolean.FALSE);
				allOrgAgentDefList.add(orgAgentDefinition);
			}
			for(SysOrgAgent sysOrgAgent : sysOrg.getSysOrgAgents()){
				SysAgent agent = sysOrgAgent.getSysAgent();
				for(OrgAgentDefinition orgAgentDefinition : allOrgAgentDefList){
					if(orgAgentDefinition.getAgentId().equals(agent.getId())){
						orgAgentDefinition.setOrgId(sysOrg.getId());
						orgAgentDefinition.setIsAuthority(Boolean.TRUE);
					}
				}
			}
			orgAuthResult.setOrgAgentDefList(allOrgAgentDefList);
			
			/*-------------------OrgCustomer----大客户---------------------------**/
			List<OrgCustomerDefinition> allOrgCustomerDefList = Lists.newArrayList();
			List<SysCustomer> allSysCustomerList = Lists.newArrayList();
			allSysCustomerList = customerService.findUnusedCustomByProvinceNo(provinceNo,orgId);
			for(SysCustomer customer : allSysCustomerList){
				OrgCustomerDefinition orgCustomerDefinition = new OrgCustomerDefinition();
				orgCustomerDefinition.setCustomerId(customer.getId());
				orgCustomerDefinition.setCustomerName(customer.getName());
				orgCustomerDefinition.setIsAuthority(Boolean.FALSE);
				allOrgCustomerDefList.add(orgCustomerDefinition);
			}
			for(SysOrgCustomer sysOrgCustomer : sysOrg.getSysOrgCustomers()){
				SysCustomer customer = sysOrgCustomer.getSysCustomer();
				for(OrgCustomerDefinition orgCustomerDefinition : allOrgCustomerDefList){
					if(orgCustomerDefinition.getCustomerId().equals(customer.getId())){
						orgCustomerDefinition.setOrgId(sysOrg.getId());
						orgCustomerDefinition.setIsAuthority(Boolean.TRUE);
					}
				}
			}
			orgAuthResult.setOrgCustomerDefList(allOrgCustomerDefList);
			model.addAttribute(orgAuthResult);
		}
		return viewPath;
	}
	
	@RequestMapping(value="/orgAuth",method=RequestMethod.POST)
	@Transactional
	public String assignRoles(@RequestParam(value="sysOrg.id",required=false) Long orgId, 
			@RequestParam(value="selectedAgentList",required=false) Long[] selectedAgentIdArr,
			@RequestParam(value="selectedCustomerList",required=false) Long[] selectedCustomerIdArr,RedirectAttributes model){
		orgAgentService.authOrg(orgId, selectedAgentIdArr);
		orgCustomerService.authOrg(orgId, selectedCustomerIdArr);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "机构授权操作成功！");
		return getIndexViewName();
	}
	
	@RequestMapping(value="/orgDel",method=RequestMethod.GET)
	@Transactional
	public String delOrg(@RequestParam(value="orgId",required=false) Long orgId){
		orgService.delOrg(orgId);
		return getIndexViewName();
		
	}

	@ModelAttribute("allProvince")
	public List<SysProvince> populateProvince() {
		return baseService.getAllSysProvince();
	}

}
