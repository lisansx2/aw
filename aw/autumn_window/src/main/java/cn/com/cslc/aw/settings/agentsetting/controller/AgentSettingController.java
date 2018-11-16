package cn.com.cslc.aw.settings.agentsetting.controller;

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

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.dto.B_AgentDto;
import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.controller.BaseController;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.service.BaseService;
import cn.com.cslc.aw.settings.agentsetting.dto.AgentSettingDefinition;
import cn.com.cslc.aw.settings.agentsetting.dto.AgentSettingResult;


@Controller
@RequestMapping("/settings/agentSetting")
public class AgentSettingController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(AgentSettingController.class);
	
	private String indexViewName = "redirect:/settings/agentSetting";

	@Autowired
	private AgentService agentService;
	
	@Autowired
	private BaseService baseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showAgentSettingIndex(Model model){
		return "settings/agentSetting/agentSettingIndex";
	}
	
	@RequestMapping(value="/setAgent",method=RequestMethod.GET)
	public String showAgentSetting(@RequestParam(value="provinceNo",required=true) String provinceNo, @RequestParam(value="success",defaultValue="false") boolean success, final SysUser user, Model model){
		String viewPath = "settings/agentSetting/setAgent";
		
		if(success){
			model.addAttribute("success", Boolean.TRUE);
		}else{
			AgentSettingResult agentSettingResult = new AgentSettingResult();
			List<AgentSettingDefinition> agentSettingDefList = Lists.newArrayList();
			List<B_AgentDto> allAgentDtoList = agentService.getAllAgentDtoByProvinceNo(provinceNo);
			for(B_AgentDto agentDto : allAgentDtoList){
				AgentSettingDefinition agentSettingDefinition = new AgentSettingDefinition();
				agentSettingDefinition.setAgentNo(agentDto.getAgentNo());
				agentSettingDefinition.setAgentName(agentDto.getAgentName());
				agentSettingDefinition.setIsSelected(Boolean.FALSE);

				agentSettingDefList.add(agentSettingDefinition);
				}
			
			for(SysAgent sysAgent : agentService.findAgentByProvinceNo(provinceNo)){
				for(AgentSettingDefinition agentSettingDefinition : agentSettingDefList){
					if(agentSettingDefinition.getAgentNo().equals(sysAgent.getCode())){
						agentSettingDefinition.setIsSelected(Boolean.TRUE);
						agentSettingDefinition.setAgentId(sysAgent.getId());
					}
				}
			}
			agentSettingResult.setAgentSettingDefList(agentSettingDefList);
			agentSettingResult.setSysProvince(baseService.getSysProvinceByNo(provinceNo));
			model.addAttribute(agentSettingResult);
		}
		return viewPath;
	}
	
	@RequestMapping(value="/setAgent",method=RequestMethod.POST)
	public String setAgent(@RequestParam(value="sysProvince.provinceNo",required=true) String provinceNo, @RequestParam(value="selectedAgentList",required=false) String[] selectedAgentNoArr,RedirectAttributes model){
		Set<String> selectedAgentNoSet = Sets.newHashSet();
		if(selectedAgentNoArr !=null){
			selectedAgentNoSet = Sets.newHashSet(selectedAgentNoArr);
		}
		
		agentService.setAgent(provinceNo, selectedAgentNoSet);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "设置代理操作成功！");
		return getIndexViewName();
	}
		
	@Override
	protected String getIndexViewName() {
		return indexViewName;  
	}
}
