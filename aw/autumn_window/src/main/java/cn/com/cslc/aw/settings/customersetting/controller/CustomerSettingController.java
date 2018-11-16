package cn.com.cslc.aw.settings.customersetting.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.core.common.controller.BaseController;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.customer.domain.SysCustomer;
import cn.com.cslc.aw.customer.dto.MD_ChannelCustomerDto;
import cn.com.cslc.aw.customer.service.CustomerService;

import cn.com.cslc.aw.settings.customersetting.dto.CustomerSettingDefinition;
import cn.com.cslc.aw.settings.customersetting.dto.CustomerSettingResult;

@Controller
@RequestMapping("/settings/customerSetting")
public class CustomerSettingController  extends BaseController{

	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerSettingController.class);
	
	private String indexViewName = "redirect:/settings/customerSetting";
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showCustomerSettingIndex(Model model){
		return "settings/customerSetting/customerSettingIndex";
	}
	
	@RequestMapping(value="/setCustomer",method=RequestMethod.GET)
	public String showCustomerSetting(@RequestParam(value="provinceNo",required=true) String provinceNo, final SysUser user, Model model){
		String viewPath = "settings/customerSetting/setCustomer";
		
		
			CustomerSettingResult customerSettingResult = new CustomerSettingResult();
			List<CustomerSettingDefinition> customerSettingDefList = Lists.newArrayList();
			List<MD_ChannelCustomerDto> allCustomerDtoList = customerService.getCustomerDtoByProvinceNo(provinceNo);
			for(MD_ChannelCustomerDto customerDto : allCustomerDtoList){
				CustomerSettingDefinition customerSettingDefinition = new CustomerSettingDefinition();
				customerSettingDefinition.setCustomerNo(customerDto.getCustomerCode());
				customerSettingDefinition.setCustomerName(customerDto.getCustomerName());
				customerSettingDefinition.setIsSelected(Boolean.FALSE);

				customerSettingDefList.add(customerSettingDefinition);
				}
			
			for(SysCustomer sysCustomer : customerService.findByProvinceNo(provinceNo)){
				for(CustomerSettingDefinition agentSettingDefinition : customerSettingDefList){
					if(agentSettingDefinition.getCustomerNo().equals(sysCustomer.getCode())){
						agentSettingDefinition.setIsSelected(Boolean.TRUE);
						agentSettingDefinition.setCustomerId(sysCustomer.getId());
					}
				}
			}
			customerSettingResult.setCustomerSettingDefList(customerSettingDefList);
			customerSettingResult.setSysProvince(baseService.getSysProvinceByNo(provinceNo));
			model.addAttribute(customerSettingResult);
			
			
		return viewPath;
	}
	
	
	@RequestMapping(value="/setCustomer",method=RequestMethod.POST)
	public String setCustomer(@RequestParam(value="sysProvince.provinceNo",required=true) String provinceNo, @RequestParam(value="selectedCustomerList",required=false) String[] selectedCustomerNoArr,RedirectAttributes model){
		Set<String> selectedCustomerNoSet = Sets.newHashSet();
		if(selectedCustomerNoArr !=null){
			selectedCustomerNoSet = Sets.newHashSet(selectedCustomerNoArr);
		}
		
		customerService.setCustomer(provinceNo, selectedCustomerNoSet);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "设置大客户操作成功！");
		return getIndexViewName();
	}
	
	
	
	
	
	
	@RequestMapping(value="/selectCustomerOrg",method=RequestMethod.GET)
	public String showCustomerOrgSetting(@RequestParam(value="provinceNo",required=true) String provinceNo, final SysUser user, Model model){
		String viewPath = "settings/customerSetting/selectCustomerOrg";
		
		
			CustomerSettingResult customerSettingResult = new CustomerSettingResult();
			List<CustomerSettingDefinition> customerSettingDefList = Lists.newArrayList();
			List<MD_ChannelCustomerDto> allCustomerDtoList = customerService.getCustomerDtoByProvinceNo(provinceNo);
			for(MD_ChannelCustomerDto customerDto : allCustomerDtoList){
				CustomerSettingDefinition customerSettingDefinition = new CustomerSettingDefinition();
				customerSettingDefinition.setCustomerNo(customerDto.getCustomerCode());
				customerSettingDefinition.setCustomerName(customerDto.getCustomerName());
				customerSettingDefinition.setIsSelected(Boolean.FALSE);

				customerSettingDefList.add(customerSettingDefinition);
				}
			/*
			for(SysCustomer sysCustomer : customerService.findByProvinceNo(provinceNo)){
				for(CustomerSettingDefinition agentSettingDefinition : customerSettingDefList){
					if(agentSettingDefinition.getCustomerNo().equals(sysCustomer.getCode())){
						agentSettingDefinition.setIsSelected(Boolean.TRUE);
						agentSettingDefinition.setCustomerId(sysCustomer.getId());
					}
				}
			}
			*/
			customerSettingResult.setCustomerSettingDefList(customerSettingDefList);
			customerSettingResult.setSysProvince(baseService.getSysProvinceByNo(provinceNo));
			model.addAttribute(customerSettingResult);
			
			
		return viewPath;
	}
	
	@RequestMapping(value="/selectCustomerOrg",method=RequestMethod.POST)
	public String setSelectedOrg(@RequestParam(value="sysProvince.provinceNo",required=true) String provinceNo, @RequestParam(value="selectedCustomerList",required=false) String[] selectedCustomerNoArr,@RequestParam(value="selectOrg",required=true)  String selectedOrg , @RequestParam(value="isSelectOrg",required=false)  String isSelectOrg ,RedirectAttributes model){
		
		String viewPath = "settings/customerSetting/selectCustomerOrg";

		CustomerSettingResult customerResult = new CustomerSettingResult();
		List<CustomerSettingDefinition> customerSettingDefList = Lists.newArrayList();
		List<MD_ChannelCustomerDto> allCustomerDtoList = customerService.getCustomerDtoByProvinceNo(provinceNo);
		for(MD_ChannelCustomerDto customerDto : allCustomerDtoList){
			CustomerSettingDefinition customerSettingDefinition = new CustomerSettingDefinition();
			customerSettingDefinition.setCustomerNo(customerDto.getCustomerCode());
			customerSettingDefinition.setCustomerName(customerDto.getCustomerName());
			customerSettingDefinition.setIsSelected(Boolean.FALSE);

			customerSettingDefList.add(customerSettingDefinition);
			}
	
		customerResult.setCustomerSettingDefList(customerSettingDefList);
		customerResult.setSysProvince(baseService.getSysProvinceByNo(provinceNo));
		model.addAttribute("customerSettingResult", customerResult);
	


		return viewPath;
	}
		
	
	@Override
	protected String getIndexViewName() {
		// TODO Auto-generated method stub
		return indexViewName;
	}

}
